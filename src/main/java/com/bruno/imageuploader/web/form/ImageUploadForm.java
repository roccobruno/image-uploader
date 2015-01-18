package com.bruno.imageuploader.web.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class ImageUploadForm {

    private String caption;
    private String altTag;
    private boolean defaultName;
    private MultipartFile file;

    //additional file
    private String captionForAdditionalImage;
    private String altTagForAdditionalImage;
    private boolean defaultNameForAdditionalImage;
    private MultipartFile additionalFile;


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAltTag() {
        return altTag;
    }

    public void setAltTag(String altTag) {
        this.altTag = altTag;
    }

    public boolean isDefaultName() {
        return defaultName;
    }

    public void setDefaultName(boolean defaultName) {
        this.defaultName = defaultName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public String getCaptionForAdditionalImage() {
        return captionForAdditionalImage;
    }

    public void setCaptionForAdditionalImage(String captionForAdditionalImage) {
        this.captionForAdditionalImage = captionForAdditionalImage;
    }

    public String getAltTagForAdditionalImage() {
        return altTagForAdditionalImage;
    }

    public void setAltTagForAdditionalImage(String altTagForAdditionalImage) {
        this.altTagForAdditionalImage = altTagForAdditionalImage;
    }

    public boolean isDefaultNameForAdditionalImage() {
        return defaultNameForAdditionalImage;
    }

    public void setDefaultNameForAdditionalImage(boolean defaultNameForAdditionalImage) {
        this.defaultNameForAdditionalImage = defaultNameForAdditionalImage;
    }

    public MultipartFile getAdditionalFile() {
        return additionalFile;
    }

    public void setAdditionalFile(MultipartFile additionalFile) {
        this.additionalFile = additionalFile;
    }

    @Override
    public String toString() {
        return "ImageUploadForm{" +
                "caption='" + caption + '\'' +
                ", altTag='" + altTag + '\'' +
                ", defaultName=" + defaultName +
                ", file=" + file +
                ", captionForAdditionalImage='" + captionForAdditionalImage + '\'' +
                ", altTagForAdditionalImage='" + altTagForAdditionalImage + '\'' +
                ", defaultNameForAdditionalImage=" + defaultNameForAdditionalImage +
                ", additionalFile=" + additionalFile +
                '}';
    }
}
