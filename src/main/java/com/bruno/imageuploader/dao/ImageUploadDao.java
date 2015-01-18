package com.bruno.imageuploader.dao;

import com.bruno.imageuploader.domain.Image;

import java.io.IOException;

public interface ImageUploadDao {
    String save(Image image) throws IOException;
}
