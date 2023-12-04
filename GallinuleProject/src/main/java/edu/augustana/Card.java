package edu.augustana;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Card implements Serializable {
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

    public Card(String[] csvRowData) {
        this.code = csvRowData[0];
        this.event = csvRowData[1];
        this.category = csvRowData[2];
        this.title = csvRowData[3];
        this.packFolder = csvRowData[4].toUpperCase();
        this.img = csvRowData[5];
        this.gender = csvRowData[6];
        this.modelSex = csvRowData[7];
        this.level = Arrays.asList(csvRowData[8].split(";"));
        this.equipments = Arrays.asList(csvRowData[9].split(";"));
        this.keywords = Arrays.asList(csvRowData[10].split(";"));
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

    public List<String> getLevels() {
        return level;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public ImageView createImageView() {
        String filename = "file:CardPacks/"+ packFolder+ "/" + getImg();
        Image img = new Image(filename);

        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(CARD_SIZE);
        imgView.setFitWidth(CARD_SIZE);
        return imgView;
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
                ", level=" + level +
                ", equipments=" + equipments +
                ", keywords=" + keywords +
                '}';
    }

    public ImageView createHighResolutionImageView() {
        String filename = "file:CardPacks/" + getPackFolder() + "Pack" + "/" + getImg();
        System.out.println(filename);
        Image img = new Image(filename);

        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(CARD_SIZE);
        imgView.setFitWidth(CARD_SIZE);
        return imgView;
    }

    public ImageView createThumbnailImageView() {
        String folderName = "StressTest";
        String thumbnailFilename = getImg().replace(".png", ".jpg");
        String thumbnailPath = "file:CardPacks/" + folderName + "/thumbs/" + thumbnailFilename;
        System.out.println("Thumbnail Path: " + thumbnailPath);



//        String folderName = getPackFolder() + "Pack";
//        String thumbnailFilename = getImg().replace(".png", ".jpg");
//        String thumbnailPath = "file:CardPacks/" + folderName + "/thumbs/" + thumbnailFilename;
//        System.out.println("Thumbnail Path: " + thumbnailPath);

        Image thumbnail = new Image(thumbnailPath);

        ImageView thumbnailView = new ImageView(thumbnail);
        thumbnailView.setFitHeight(CARD_SIZE);
        thumbnailView.setFitWidth(CARD_SIZE);
        return thumbnailView;
    }

//    public ImageView createThumbnailImageView() {
//        String folderName = "StressTest";
//        String thumbnailFilename = getImg().replace(".png", ".jpg");
//
//        try {
//            String thumbnailPath = "file:CardPacks/" + folderName + "/thumbs/" + thumbnailFilename;
//            System.out.println("Attempting to load thumbnail image: " + thumbnailPath);
//
//            Image thumbnail = new Image(thumbnailPath);
//
//            if (thumbnail.isError()) {
//                System.err.println("Error loading thumbnail image: " + thumbnailPath);
//                return null;
//            }
//
//            System.out.println("Successfully loaded thumbnail image: " + thumbnailPath);
//
//            ImageView thumbnailView = new ImageView(thumbnail);
//            thumbnailView.setFitHeight(CARD_SIZE);
//            thumbnailView.setFitWidth(CARD_SIZE);
//            return thumbnailView;
//        } catch (Exception e) {
//            System.err.println("Error loading thumbnail image: " + e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }


}
