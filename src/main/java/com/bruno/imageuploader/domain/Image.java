package com.bruno.imageuploader.domain;

import java.util.Arrays;

public class Image {

    private final String caption;
    private final String altTag;
    private final byte[] file;
    private final String fileName;


    private Image(String caption, String altTag, byte[] file, String fileName) {
        this.caption = caption;
        this.altTag = altTag;
        this.file = file;
        this.fileName = fileName;
    }

    public String getCaption() {
        return caption;
    }

    public String getAltTag() {
        return altTag;
    }

    public byte[] getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public static class ImageBuilder {

        private  String caption;
        private  String altTag;
        private  byte[] file;
        private  String fileName;

        public ImageBuilder withName(String name) {
            this.fileName = name;
            return this;
        }

        public ImageBuilder withCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public ImageBuilder withAltTag(String altTag) {
            this.altTag = altTag;
            return this;
        }

        public ImageBuilder withContent(byte[] content) {
            this.file = content;
            return this;
        }

        public Image build() {
            return new Image(caption,altTag,file,fileName);
        }

    }

    @Override
    public String toString() {
        return "Image{" +
                "caption='" + caption + '\'' +
                ", altTag='" + altTag + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (altTag != null ? !altTag.equals(image.altTag) : image.altTag != null) return false;
        if (caption != null ? !caption.equals(image.caption) : image.caption != null) return false;
        if (!Arrays.equals(file, image.file)) return false;
        if (fileName != null ? !fileName.equals(image.fileName) : image.fileName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caption != null ? caption.hashCode() : 0;
        result = 31 * result + (altTag != null ? altTag.hashCode() : 0);
        result = 31 * result + (file != null ? Arrays.hashCode(file) : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
