package com.bruno.imageuploader.domain;

public class ImageMetadata {
    private  String altTag;
    private  String caption;
    private  String fileName;
    private String originalName;

    public ImageMetadata(){

    }

    public ImageMetadata(String altTag, String caption, String fileName) {
        this.altTag = altTag;
        this.caption = caption;
        this.fileName = fileName;
    }



    public String getAltTag() {
        return altTag;
    }



    public String getCaption() {
        return caption;
    }



    public String getFileName() {
        return fileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "altTag='" + altTag + '\'' +
                ", caption='" + caption + '\'' +
                ", fileName='" + fileName + '\'' +
                ", originalName='" + originalName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageMetadata that = (ImageMetadata) o;

        if (altTag != null ? !altTag.equals(that.altTag) : that.altTag != null) return false;
        if (caption != null ? !caption.equals(that.caption) : that.caption != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (originalName != null ? !originalName.equals(that.originalName) : that.originalName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = altTag != null ? altTag.hashCode() : 0;
        result = 31 * result + (caption != null ? caption.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (originalName != null ? originalName.hashCode() : 0);
        return result;
    }
}
