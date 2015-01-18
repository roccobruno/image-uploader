package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.domain.ImageMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.bruno.imageuploader.dao.ImagePathsBuilder.*;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Component
public class FileSystemImageUploadDaoImpl implements ImageUploadDao {

    Logger logger = org.slf4j.LoggerFactory.getLogger(FileSystemImageUploadDaoImpl.class);

    public static String METADATA_FILE_NAME = "metadata.json";


    private String location;

    @Autowired
    public FileSystemImageUploadDaoImpl(@Value("${upload.location}") String location) {
        this.location = location;
    }

    @Override
    public String save(Image image) throws IOException {
        if(logger.isDebugEnabled()){
            logger.debug("saved image {} in location {}",image.getMetadata().getFileName(),location);
        }

        //create dir
        String fileName = UUID.randomUUID().toString()+"-"+System.nanoTime();
        String dirLocation = buildPathToImageDirector(location, fileName);
        Path path = Paths.get(dirLocation);
        Files.createDirectories(path);
        Files.write(Paths.get(buildPathToImageFile(location,fileName)),image.getFile(), CREATE_NEW);


        saveMetadata(image,fileName);

        return fileName;
    }

    @Override
    public Optional<ImageMetadata> loadImageMetadata(String fileName)  {
        byte[] metadataContent = new byte[0];
        try {
            metadataContent = Files.readAllBytes(Paths.get(buildPathToImageMetadataFile(location, fileName)));
            return Optional.of(new ObjectMapper().readValue(metadataContent, ImageMetadata.class));
        } catch (IOException e) {
            logger.error("error in loading metadata for image {}", fileName);
        }
        return Optional.empty();
    }

    @Override
    public byte[] loadImage(String fileName)  {
        try {
            return Files.readAllBytes(Paths.get(buildPathToImageFile(location,fileName)));
        } catch (IOException e) {
            logger.error("error in loading content for image {}",fileName);
        }
        return new byte[0];
    }

    @Override
    public List<Optional<ImageMetadata>> loadImageMetadatas() throws IOException {
      return  Files.list(Paths.get(location)).
              map(path -> path.getName(path.getNameCount() - 1)).
              map(path -> loadImageMetadata(path.toString())).
              collect(Collectors.toList());

    }

    @Override
    public long countImages() {
        try {
            return Files.list(Paths.get(location)).count();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public void deleteImage(String fileName) throws IOException {
        Files.delete(Paths.get(buildPathToImageFile(location,fileName)));
        Files.delete(Paths.get(buildPathToImageMetadataFile(location,fileName)));
        Files.delete(Paths.get(buildPathToImageDirector(location,fileName)));
        Files.delete(Paths.get(buildPathToFileDirector(location,fileName)));
    }

    private void saveMetadata(Image image,String fileName) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("originalName", image.getMetadata().getFileName());
        obj.put("caption", image.getMetadata().getCaption());
        obj.put("altTag", image.getMetadata().getAltTag());
        obj.put("fileName", fileName);

        Files.write(Paths.get(buildPathToImageMetadataFile(location,fileName)),obj.toJSONString().getBytes(), CREATE_NEW);
  }


}
