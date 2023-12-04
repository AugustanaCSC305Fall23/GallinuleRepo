package edu.augustana;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CardView extends StackPane {
    ImageView imageView;

    public CardView(ImageView imageView) {
        this.imageView = imageView;
        imageView.setPreserveRatio(true);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        setPadding(new Insets(5));
        getChildren().add(imageView);
    }

    public void setFitWidth(int value) {
        this.imageView.setFitWidth(value);
    }

    public void setFitHeight(int value) {
        this.imageView.setFitHeight(value);
    }

    public void setSpacingBetweenCards(double spacing) {
        setMargin(this, new Insets(0, 0, spacing, spacing));
    }
}
