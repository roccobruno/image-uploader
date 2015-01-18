package com.bruno.imageuploader.dao;

public class ImagePathsBuilder {

    public static String buildPathToFileDirector(String rootDir,String fileName) {
        StringBuilder builder = new StringBuilder(rootDir);
        builder.append("/").append(fileName);
        return builder.toString();
    }

    public static String buildPathToImageDirector(String rootDir,String fileName) {
        StringBuilder builder = new StringBuilder(rootDir);
        builder.append("/").append(fileName).append("/image");
        return builder.toString();
    }
    public static String buildPathToImageMetadataFile(String rootDir,String fileName) {
        StringBuilder builder = new StringBuilder(rootDir);
        builder.append("/").append(fileName).append("/metadata.json");
        return builder.toString();
    }

    public static String buildPathToImageFile(String rootDir,String fileName) {
        StringBuilder builder = new StringBuilder(rootDir);
        builder.append("/").append(fileName).append("/image/").append(fileName);
        return builder.toString();
    }
}
