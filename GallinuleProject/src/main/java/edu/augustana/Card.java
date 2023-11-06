package edu.augustana;



import java.io.File;
import java.io.IOException;
import java.util.List;

public class Card {
    private String code;
    private String event;
    private String category;
    private String title;
    private String packFolder;
    private String img;
    private String gender;
    private String modelSex;
    private String level;
    private String equipments;
    private String keywords;

    public Card(String[] csvRowData) {
        this.code = csvRowData[0];
        this.event = csvRowData[1];
        this.category = csvRowData[2];
        this.title = csvRowData[3];
        this.packFolder = csvRowData[4];
        this.img = csvRowData[5];
        this.gender = csvRowData[6];
        this.modelSex = csvRowData[7];
        this.level = csvRowData[8];
        this.equipments = csvRowData[9];
        this.keywords = csvRowData[10];
    }

    public String getCode() {
        return code;
    }

    public String getEvent() {
        return event;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getPackFolder() {
        return packFolder;
    }

    public String getImg() {
        return img;
    }

    public String getGender() {
        return gender;
    }

    public String getModelSex() {
        return modelSex;
    }

    public String getLevel() {
        return level;
    }

    public String getEquipments() {
        return equipments;
    }

    public String getKeywords() {
        return keywords;
    }


}
