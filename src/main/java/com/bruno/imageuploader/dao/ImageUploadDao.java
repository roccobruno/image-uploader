package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.domain.ImageMetadata;

import java.io.IOException;
import java.util.List;

public interface ImageUploadDao {
    String save(Image image) throws IOException;

    java.util.Optional<ImageMetadata> loadImageMetadata(String fileName) throws IOException;

    byte[] loadImage(String fileName) throws IOException;

    List<java.util.Optional<ImageMetadata>> loadImageMetadatas() throws IOException;

    long countImages();

    void deleteImage(String fileName) throws IOException;
}
