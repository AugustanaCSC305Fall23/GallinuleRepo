package edu.augustana;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class CardView extends StackPane {
    ImageView imageView;

    private static final double ZOOM_FACTOR = 1.07;

    public CardView(ImageView imageView) {
        this.imageView = imageView;
        imageView.setPreserveRatio(true);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        setPadding(new Insets(5));
        getChildren().add(imageView);

        // Enable zooming/magnifying when the user scrolls
        setOnScroll(this::handleScroll);
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

    private void handleScroll(ScrollEvent event) {
        double deltaY = event.getDeltaY();

        if (deltaY < 0) {
            // Zoom out
            zoomOut();
        } else {
            // Zoom in
            zoomIn();
        }

        event.consume();
    }

    private void zoomIn() {
        imageView.setFitWidth(imageView.getFitWidth() * ZOOM_FACTOR);
        imageView.setFitHeight(imageView.getFitHeight() * ZOOM_FACTOR);
    }

    private void zoomOut() {
        imageView.setFitWidth(imageView.getFitWidth() / ZOOM_FACTOR);
        imageView.setFitHeight(imageView.getFitHeight() / ZOOM_FACTOR);
    }


}
