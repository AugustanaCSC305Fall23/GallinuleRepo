package edu.augustana.data;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CardView extends StackPane {


    public CardView(Card card) {

        ImageView imageView = card.createImageView();
        imageView.setPreserveRatio(true);

        setMaxWidth(Card.CARD_SIZE);
        setMaxHeight(Card.CARD_SIZE);
        setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        setPadding(new Insets(5));

        getChildren().add(imageView);
    }

    public void setSpacingBetweenCards(double spacing) {
        setMargin(this, new Insets(0, 0, spacing, spacing));
    }
}
