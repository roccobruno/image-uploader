package com.bruno.imageuploader.service;

import com.bruno.imageuploader.domain.Image;

import java.io.IOException;
import java.util.List;

public interface ImageUploadService {
    void save(List<Image> results) throws IOException;
}
