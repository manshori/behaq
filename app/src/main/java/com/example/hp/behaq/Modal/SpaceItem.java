package com.example.hp.behaq.Modal;

import java.util.Date;

/**
 * Created by hp on 05/12/2017.
 */

public class SpaceItem {
    private int id;
    private String guide;
    private String name;

    public SpaceItem(int id, String guide, String name) {
        this.id = id;
        this.guide = guide;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
