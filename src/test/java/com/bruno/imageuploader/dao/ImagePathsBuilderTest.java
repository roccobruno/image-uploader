package com.bruno.imageuploader.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImagePathsBuilderTest {

    @Test
    public void testBuildPathToFileDirector() throws Exception {

        assertEquals("test/filename",ImagePathsBuilder.buildPathToFileDirector("test","filename"));
    }

    @Test
    public void testBuildPathToImageDirector() throws Exception {
        assertEquals("test/filename/image",ImagePathsBuilder.buildPathToImageDirector("test","filename"));
    }

    @Test
    public void testBuildPathToImageMetadataFile() throws Exception {
        assertEquals("test/filename/metadata.json",ImagePathsBuilder.buildPathToImageMetadataFile("test","filename"));
    }

    @Test
    public void testBuildPathToImageFile() throws Exception {
        assertEquals("test/filename/image/filename",ImagePathsBuilder.buildPathToImageFile("test","filename"));
    }
}