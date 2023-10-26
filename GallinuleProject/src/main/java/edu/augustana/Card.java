package edu.augustana;

import com.opencsv.CSVReader;

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
    private String level;
    private String equipments;
    private String keywords;

    public Card(String code, String event, String category, String title, String packFolder, String img, String gender, String modelSex, String level, String equipments, String keywords) {
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

    public String getLevel() {
        return level;
    }

    public String getEquipments() {
        return equipments;
    }

    public String getKeywords() {
        return keywords;
    }

    public static List<Card> readCardsFromCSV(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/DEMO1.csv"))) {
            List<String[]> lines = reader.readAll();
            lines.remove(0);

            return lines.stream()
                    .map(data -> new Card(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10]))
                    .toList();
        }
    }
}
