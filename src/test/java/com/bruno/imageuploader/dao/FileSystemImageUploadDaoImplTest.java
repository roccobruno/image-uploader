package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class FileSystemImageUploadDaoImplTest {

    @Test
    public void testSave() throws Exception {


        String testLocation = "test";
        ImageUploadDao imageUploadDao = new FileSystemImageUploadDaoImpl(testLocation);

        byte[] imageTest = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("image.jpg").toURI()));

        Image image = new Image.ImageBuilder().withAltTag("alttag")
                .withCaption("caption").withName("test").withContent(imageTest).build();


        String fileName =imageUploadDao.save(image);


        String pathToImage = testLocation + "/" + fileName;
        File file = new File(pathToImage);
        assertTrue(file.exists());
        assertTrue(file.isDirectory());

        String iamgeDirPath = pathToImage + "/image";
        File imageDir = new File(iamgeDirPath);
        assertTrue(imageDir.exists());
        assertTrue(imageDir.isDirectory());


        String imageFilePath = iamgeDirPath + "/" + fileName;
        File imageFile = new File(imageFilePath);
        assertTrue(imageFile.exists());
        assertFalse(imageFile.isDirectory());



        byte[] imageSaved = Files.readAllBytes(Paths.get(imageFilePath));
        assertTrue(Arrays.equals(imageTest, imageSaved));


        String metadataFilePath = pathToImage + "/"+"metadata.json";
        byte[] jsonContent = Files.readAllBytes(Paths.get(metadataFilePath));

        Map map1 = new ObjectMapper().readValue(jsonContent, Map.class);
        assertEquals(map1.get("caption"),"caption");
        assertEquals(map1.get("altTag"),"alttag");
        assertEquals(map1.get("originalName"),"test");
        assertEquals(map1.get("fileName"),fileName);


        //check json with metadata





        //clean up test dir
        Files.delete(Paths.get(imageFilePath));
        Files.delete(Paths.get(metadataFilePath));
        Files.delete(Paths.get(iamgeDirPath));
        Files.delete(Paths.get(pathToImage));
        Files.delete(Paths.get(testLocation));

    }
}