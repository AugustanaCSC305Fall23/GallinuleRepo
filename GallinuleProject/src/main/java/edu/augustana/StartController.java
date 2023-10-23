package edu.augustana;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

public class StartController {


    public BorderPane bp;
    @FXML
    private Node mainScreen;

    @FXML
    private void switchWelcome() throws IOException {
        loadPage("Welcome");
    }

    @FXML
    private void switchBrowse() throws IOException {
        loadPage("BrowsePlan");
    }

    @FXML
    private void switchCreatePlan() throws IOException {
        loadPage("CreatePlan");
    }

    @FXML
    private void loadPage(String page) throws IOException{
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        bp.setCenter(root);

    }








}