package edu.augustana;



import java.io.FileReader;
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
    private List<String> level;
    private List<String> equipments;
    private List<String> keywords;

    public Card(String code, String event, String category, String title, String packFolder, String img, String gender, String modelSex, List<String> level, List<String> equipments, List<String> keywords) {
        this.code = code;
        this.event = event;
        this.category = category;
        this.title = title;
        this.packFolder = packFolder;
        this.img = img;
        this.gender = gender;
        this.modelSex = modelSex;
        this.level = level;
        this.equipments = equipments;
        this.keywords = keywords;
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

    public List<String> getLevel() {
        return level;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return "Card{" +
                "code='" + code + '\'' +
                ", event='" + event + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", packFolder='" + packFolder + '\'' +
                ", img='" + img + '\'' +
                ", gender='" + gender + '\'' +
                ", modelSex='" + modelSex + '\'' +
                ", level='" + level + '\'' +
                ", equipments=" + equipments +
                ", keywords=" + keywords +
                '}';
    }



}
