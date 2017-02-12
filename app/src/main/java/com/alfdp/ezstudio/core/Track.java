package com.alfdp.ezstudio.core;

/**
 * Created by maxim on 04/02/2017.
 */

public class Track extends Project {
    private String compositor;
    private long[] links;

    public Track(String compositor, long[] links) {
        this.compositor = compositor;
        this.links = links;
    }

    public Track(long id, String name, String date, String compositor, long[] links) {
        super(id, name, date);
        this.compositor = compositor;
        this.links = links;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public long[] getLinks() {
        return links;
    }

    public void setLinks(long[] links) {
        this.links = links;
    }
}
