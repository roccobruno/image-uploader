package com.bruno.imageuploader.service;

import com.bruno.imageuploader.dao.ImageUploadDao;
import com.bruno.imageuploader.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ImageUploadServiceImpl implements ImageUploadService{

    private final ImageUploadDao imageUploadDao;

    @Autowired
    public ImageUploadServiceImpl(ImageUploadDao imageUploadDao) {
        this.imageUploadDao = imageUploadDao;
    }

    @Override
    public void save(List<Image> results) throws IOException {

        for (Image image : results) {
            imageUploadDao.save(image);
        }

    }
}
