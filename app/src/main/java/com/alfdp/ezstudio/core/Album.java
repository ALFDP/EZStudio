package com.alfdp.ezstudio.core;

/**
 * Created by maxim on 04/02/2017.
 */

public class Album extends Project {
    private String compositor;
    private String release;

    public Album(){

    }

    public Album(long id, String name, String date, String compositor, String release) {
        super(id, name, date);
        this.compositor = compositor;
        this.release = release;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

}
