package com.bruno.imageuploader.service;

import com.bruno.imageuploader.dao.ImageUploadDao;
import com.bruno.imageuploader.domain.Image;
import com.bruno.imageuploader.domain.ImageMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageUploadServiceImpl implements ImageUploadService{
    Logger logger = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

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

    @Override
    public byte[] loadImage(String imageId) throws IOException {
        return imageUploadDao.loadImage(imageId);
    }

    @Override
    public List<ImageMetadata> loadImageMetadata() throws IOException {
        return imageUploadDao.loadImageMetadatas().stream().
                filter(p -> p.isPresent()).
                map(p -> p.get()).
                collect(Collectors.toList());
    }

    @Override
    public long countImages() {
        return imageUploadDao.countImages();
    }

    @Override
    public void deleteImage(String imageId) {
        try {
            imageUploadDao.deleteImage(imageId);
        } catch (IOException e) {
            logger.error("error in deleting image {}",imageId);
            logger.error("error :{}",e);
        }
    }


}
