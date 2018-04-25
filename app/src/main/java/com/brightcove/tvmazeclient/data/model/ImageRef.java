package com.brightcove.tvmazeclient.data.model;

/**
 * Created by Ali on 12-Apr-18.
 */

public class ImageRef {
    private String medium;
    private String original;

    public String getMedium() {
        return medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return "ImageRef{" +
                "medium='" + medium + '\'' +
                ", original='" + original + '\'' +
                '}';
    }
}
