package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.domain.ImageMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static com.bruno.imageuploader.dao.FileSystemImageUploadDaoImpl.METADATA_FILE_NAME;
import static com.bruno.imageuploader.dao.ImagePathsBuilder.*;
import static org.junit.Assert.*;

public class FileSystemImageUploadDaoImplTest {

    private FileSystemImageUploadDaoImpl imageUploadDao;
    private String testLocation = "test";
    private byte[] imageTest;
    private Image image;

    @Before
    public void setUp() throws Exception {
        imageTest = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("image.jpg").toURI()));
        imageUploadDao = new FileSystemImageUploadDaoImpl(testLocation);
        image = new Image.ImageBuilder().withAltTag("alttag")
                .withCaption("caption").withName("test").withContent(imageTest).build();

    }

    @Test
    public void loadAllMetadatas() throws Exception {
        String fileName =saveFile();
        imageUploadDao.loadImageMetadatas();
        cleanUpTestDir(fileName);

    }

    @Test
    public void testSave() throws Exception {

        String fileName =saveFile();


        String pathToImage = buildPathToImageDirector(testLocation, fileName);
        File file = new File(pathToImage);
        assertTrue(file.exists());
        assertTrue(file.isDirectory());

        String iamgeDirPath = buildPathToImageDirector(testLocation,fileName);
        File imageDir = new File(iamgeDirPath);
        assertTrue(imageDir.exists());
        assertTrue(imageDir.isDirectory());


        String imageFilePath = buildPathToImageFile(testLocation,fileName);
        File imageFile = new File(imageFilePath);
        assertTrue(imageFile.exists());
        assertFalse(imageFile.isDirectory());



        byte[] imageSaved = Files.readAllBytes(Paths.get(imageFilePath));
        assertTrue(Arrays.equals(imageTest, imageSaved));


        String metadataFilePath = buildPathToImageMetadataFile(testLocation,fileName);
        byte[] jsonContent = Files.readAllBytes(Paths.get(metadataFilePath));

        Map map1 = new ObjectMapper().readValue(jsonContent, Map.class);
        assertEquals(map1.get("caption"),"caption");
        assertEquals(map1.get("altTag"),"alttag");
        assertEquals(map1.get("originalName"),"test");
        assertEquals(map1.get("fileName"),fileName);


        //check json with metadata





        //clean up test dir
        cleanUpTestDir(fileName);

    }

    private void cleanUpTestDir(String fileName) throws IOException {
        imageUploadDao.deleteImage(fileName);
        Files.delete(Paths.get(this.testLocation));
    }


    @Test
    public void loadImage() throws Exception {
        String fileName =saveFile();
        byte[] imageContent = imageUploadDao.loadImage(fileName);
        assertTrue(Arrays.equals(imageTest, imageContent));


        cleanUpTestDir(fileName);
    }

    @Test
    public void loadImageMetadataObject() throws Exception {

        String fileName =saveFile();
        Optional<ImageMetadata> imageSaved = imageUploadDao.loadImageMetadata(fileName);
        assertEquals(imageSaved.get().getOriginalName(),image.getMetadata().getFileName());
        assertEquals(imageSaved.get().getFileName(),fileName);
        assertEquals(imageSaved.get().getAltTag(),image.getMetadata().getAltTag());
        assertEquals(imageSaved.get().getCaption(),image.getMetadata().getCaption());


        cleanUpTestDir(fileName);

    }

    private String saveFile() throws IOException, URISyntaxException {
        String fileName =imageUploadDao.save(image);
        return fileName;
    }

    @Test
    public void countImages() throws Exception {
        String fileName =saveFile();
        long res = imageUploadDao.countImages();
        assertEquals(res,1);
        cleanUpTestDir(fileName);

    }

    @Test
    public void deleteImage() throws Exception {
        String fileName =saveFile();
        long res = imageUploadDao.countImages();
        assertEquals(res,1);

        imageUploadDao.deleteImage(fileName);
        res = imageUploadDao.countImages();
        assertEquals(res,0);

    }
}