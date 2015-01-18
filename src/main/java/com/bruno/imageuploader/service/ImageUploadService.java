package com.bruno.imageuploader.service;

import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.domain.ImageMetadata;

import java.io.IOException;
import java.util.List;

public interface ImageUploadService {
    void save(List<Image> results) throws IOException;

    byte[] loadImage(String imageId) throws IOException;

    List<ImageMetadata> loadImageMetadata() throws IOException;

    long countImages();

    void deleteImage(String imageId);
}
