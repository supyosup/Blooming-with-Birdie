package com.example.bloomingwithbirdie;

import java.io.Serializable;

public class Badge implements Serializable {
    private String filePath;
    private String name;

    public Badge(String name, String filePath) {
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
