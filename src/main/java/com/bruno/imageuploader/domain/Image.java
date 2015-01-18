package com.bruno.imageuploader.domain;

import java.util.Arrays;

public class Image {

    private final byte[] file;

    private final ImageMetadata metadata;


    private Image(ImageMetadata metadata, byte[] file) {
        this.metadata = metadata;
        this.file = file;
    }

    public byte[] getFile() {
        return file;
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
            return new Image(new ImageMetadata(altTag,caption,fileName),file);
        }

    }

    public ImageMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "Image{" +
                "file=" + Arrays.toString(file) +
                ", metadata=" + metadata +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!Arrays.equals(file, image.file)) return false;
        if (metadata != null ? !metadata.equals(image.metadata) : image.metadata != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = file != null ? Arrays.hashCode(file) : 0;
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }
}
