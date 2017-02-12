package com.alfdp.ezstudio.core;

/**
 * Created by maxim on 04/02/2017.
 */

public abstract class Project {
    private long id;
    private String name;
    private String date;

    public Project() {
    }

    public Project(long id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
