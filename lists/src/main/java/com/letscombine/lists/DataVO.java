package com.letscombine.lists;
import android.graphics.drawable.Drawable;

public class DataVO {

    private Drawable imgSrc;
    private String name;
    private String content;
    private String date;


    public Drawable getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(Drawable imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}