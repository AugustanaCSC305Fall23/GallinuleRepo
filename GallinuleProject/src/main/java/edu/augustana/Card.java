package edu.augustana;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Card {
    public static final int CARD_SIZE = 300;
    private final String code;
    private final String event;
    private final String category;
    private final String title;
    private final String packFolder;
    private final String img;
    private final String gender;
    private final String modelSex;
    private final List<String> level;
    private final List<String> equipments;
    private final List<String> keywords;

    public boolean containsProperty(String keyword) {
        String keywordLower = keyword.toLowerCase();
        return code.toLowerCase().contains(keywordLower) ||
                event.toLowerCase().contains(keywordLower) ||
                category.toLowerCase().contains(keywordLower) ||
                title.toLowerCase().contains(keywordLower) ||
                packFolder.toLowerCase().contains(keywordLower) ||
                img.toLowerCase().contains(keywordLower) ||
                gender.toLowerCase().contains(keywordLower) ||
                modelSex.toLowerCase().contains(keywordLower) ||
                level.stream().anyMatch(level -> level.toLowerCase().contains(keywordLower)) ||
                equipments.stream().anyMatch(equipment -> equipment.toLowerCase().contains(keywordLower)) ||
                keywords.stream().anyMatch(keywords -> keyword.toLowerCase().contains(keywordLower));
    }

    public ImageView createImageView()
    {
        String filename = "file:CardPacks/DEMO1Pack/"+getImg();
        Image img = new Image(filename);

        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(CARD_SIZE);
        imgView.setFitWidth(CARD_SIZE);
        return imgView;
    }

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

    @java.lang.Override
    public java.lang.String toString() {
        return "Card{" +
                "code='" + code + '\'' +
                ", event='" + event + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", packFolder='" + packFolder + '\'' +
                ", img='" + img + '\'' +
                ", gender='" + gender + '\'' +
                ", modelSex='" + modelSex + '\'' +
                ", level=" + level +
                ", equipments=" + equipments +
                ", keywords=" + keywords +
                '}';
    }
}
