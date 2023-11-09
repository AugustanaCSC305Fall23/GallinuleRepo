package edu.augustana;

import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;

public class Preview {

    @FXML
    private AnchorPane previewFileAnchorPane;


    @FXML
    void printPlan(){


        PrintGymFile printer = new PrintGymFile();
        printer.printFile(previewFileAnchorPane);

    }


}
