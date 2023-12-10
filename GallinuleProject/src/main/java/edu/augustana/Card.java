package edu.augustana;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Card implements Serializable {

    /**
     * The default size for card images.
     */
    public static final int CARD_SIZE = 300;
    private final String code;
    private final String event;
    private final String category;
    private final String title;
    private final String packFolder;
    private final String image;
    private final String gender;
    private final String modelSex;
    private final List<String> level;
    private final List<String> equipments;
    private final List<String> keywords;

    /**
     * Constructs a Card object using data from a CSV row.
     *
     * @param csvRowData An array containing data from a CSV row.
     */
    public Card(String[] csvRowData) {
        this.code = csvRowData[0];
        this.event = csvRowData[1];
        this.category = csvRowData[2];
        this.title = csvRowData[3];
        this.packFolder = csvRowData[4].toUpperCase();
        this.image = csvRowData[5];
        this.gender = csvRowData[6];
        this.modelSex = csvRowData[7];
        this.level = Arrays.asList(csvRowData[8].split(";"));
        this.equipments = Arrays.asList(csvRowData[9].split(";"));
        this.keywords = Arrays.asList(csvRowData[10].split(";"));
    }

    /**
     * Gets the code of the card.
     *
     * @return The code of the card.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the event of the card.
     *
     * @return The event of the card.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Gets the category of the card.
     *
     * @return The category of the card.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the title of the card.
     *
     * @return The title of the card.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the pack folder of the card.
     *
     * @return The pack folder of the card.
     */
    public String getPackFolder() {
        return packFolder;
    }

    /**
     * Gets the image filename of the card.
     *
     * @return The image filename of the card.
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the gender associated with the card.
     *
     * @return The gender associated with the card.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the model sex associated with the card.
     *
     * @return The model sex associated with the card.
     */
    public String getModelSex() {
        return modelSex;
    }

    /**
     * Gets the list of levels associated with the card.
     *
     * @return The list of levels associated with the card.
     */
    public List<String> getLevels() {
        return level;
    }

    /**
     * Gets the list of equipments associated with the card.
     *
     * @return The list of equipments associated with the card.
     */
    public List<String> getEquipments() {
        return equipments;
    }

    /**
     * Gets the list of keywords associated with the card.
     *
     * @return The list of keywords associated with the card.
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * Returns a string representation of the Card object.
     *
     * @return A string representation of the Card object.
     */
    @Override
    public String toString() {
        return "Card{" + "code='" + code + '\'' + ", event='" + event + '\'' + ", category='" + category + '\'' + ", title='" + title + '\'' + ", packFolder='" + packFolder + '\'' + ", img='" + image + '\'' + ", gender='" + gender + '\'' + ", modelSex='" + modelSex + '\'' + ", level=" + level + ", equipments=" + equipments + ", keywords=" + keywords + '}';
    }

    /**
     * Creates and returns an ImageView for the card with default size.
     *
     * @return The ImageView for the card.
     */
    public ImageView createImageView() {
        String filename = "file:CardPacks/" + packFolder + "/" + getImage();
        Image img = new Image(filename);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(CARD_SIZE);
        imgView.setFitWidth(CARD_SIZE);
        return imgView;
    }


    /**
     * Creates and returns a high-resolution ImageView for the card with default size.
     *
     * @return The high-resolution ImageView for the card.
     */
    public ImageView createHighResolutionImageView() {
        String filename = "file:CardPacks/" + getPackFolder() + "/" + getImage();
        Image img = new Image(filename);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(CARD_SIZE);
        imgView.setFitWidth(CARD_SIZE);
        return imgView;
    }

    /**
     * Creates and returns a thumbnail ImageView for the card with default size.
     *
     * @return The thumbnail ImageView for the card.
     */
    public ImageView createThumbnailImageView() {
        String folderName = getPackFolder();
        String thumbnailFilename = getImage().replace(".png", ".jpg");
        String thumbnailPath = "file:CardPacks/" + folderName + "/thumbs/" + thumbnailFilename;
        Image thumbnail = new Image(thumbnailPath);
        ImageView thumbnailView = new ImageView(thumbnail);
        thumbnailView.setFitHeight(CARD_SIZE);
        thumbnailView.setFitWidth(CARD_SIZE);
        return thumbnailView;
    }


}