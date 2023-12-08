package edu.augustana;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleCardView extends BorderPane implements Initializable {
    @FXML
    private ToggleButton favoriteBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Button printBtn;

    @FXML
    private ImageView cardImgView;

    @FXML
    private ListView equipmentLV;

    private Card card;

    private PrintGymFile printer = new PrintGymFile();
    @FXML
    private AnchorPane printAnchor;

    public SingleCardView() {
        // Use the parameterized constructor with a null card initially
        this(null);
    }

    public SingleCardView(Card card) {
        this.card = card;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SingleCardView.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("SingleCardView.fxml"));

        //fxmlLoader.setRoot(this);
        //fxmlLoader.setController(this);
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException("Unable to load file", exception);
//        }

    }

    public void setCard(Card card){
        this.card = card;
    }


    @FXML
    void favoriteBtnAction(ActionEvent event) {
        if (favoriteBtn.isSelected()) {
            App.getFavorites().addFavorite(card);
            favoriteBtn.setText("♥");
        } else {
            App.getFavorites().removeFavorite(card);
            favoriteBtn.setText("♡");
        }

    }

    @FXML
    private void printCard() {
        printer.printFile(printAnchor);
        new Alert(Alert.AlertType.INFORMATION, "Your Card has been sent to print successfully!").show();
    }

//    @FXML
//    void initialize() {
//        setCard(card);
//        cardImgView.setImage(card.createHighResolutionImageView().getImage());
//        equipmentLV.setItems(FXCollections.observableArrayList(card.getEquipments()));
//        if (App.getFavorites().contains(card)) {
//            favoriteBtn.setSelected(true);
//            favoriteBtn.setText("♥");
//        }
//
//    }


    @FXML
    private void handleCloseButtonAction(ActionEvent event) {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public Button getCloseBtn() {
        return closeBtn;
    }

    public BorderPane getRootBorderPane() {
        return this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCard(card);
        cardImgView.setImage(card.createHighResolutionImageView().getImage());
        equipmentLV.setItems(FXCollections.observableArrayList(card.getEquipments()));
        if (App.getFavorites().contains(card)) {
            favoriteBtn.setSelected(true);
            favoriteBtn.setText("♥");
        }
    }
}
