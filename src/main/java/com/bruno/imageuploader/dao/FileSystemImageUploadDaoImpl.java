package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;
import org.apache.log4j.spi.LoggerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Component
public class FileSystemImageUploadDaoImpl implements ImageUploadDao {

    Logger logger = org.slf4j.LoggerFactory.getLogger(FileSystemImageUploadDaoImpl.class);


    private String location;

    @Autowired
    public FileSystemImageUploadDaoImpl(@Value("${upload.location}") String location) {
        this.location = location;
    }

    @Override
    public String save(Image image) throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug("saved image {} in location {}",image.getFileName(),location);
        }

        //create dir
        String fileName = UUID.randomUUID().toString()+"-"+System.nanoTime();
        String dirLocation = location + "/" + fileName+"/image";
        Path path = Paths.get(dirLocation);
        Files.createDirectories(path);
        Files.write(Paths.get(dirLocation+"/"+fileName),image.getFile(), CREATE_NEW);


        saveMetadata(image,fileName,location+"/"+fileName);

        return fileName;
    }

    private void saveMetadata(Image image,String fileName,String location) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("originalName", image.getFileName());
        obj.put("caption", image.getCaption());
        obj.put("altTag", image.getAltTag());
        obj.put("fileName", fileName);
        Files.write(Paths.get(location+"/metadata.json"),obj.toJSONString().getBytes(), CREATE_NEW);
  }
}
