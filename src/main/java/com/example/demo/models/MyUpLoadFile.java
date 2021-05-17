package com.example.demo.models;

import org.springframework.web.multipart.MultipartFile;

public class MyUpLoadFile {
    private MultipartFile[] fileData;

    private String description;

    public MultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile[] fileData) {
        this.fileData = fileData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
