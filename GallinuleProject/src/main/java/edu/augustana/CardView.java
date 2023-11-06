package edu.augustana;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CardView extends StackPane {
    private final Card card;

    public CardView(Card card) {
        this.card = card;

        ImageView imageView = card.createImageView();
        imageView.setPreserveRatio(true);

        setMaxWidth(Card.CARD_SIZE);
        setMaxHeight(Card.CARD_SIZE);
        setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2; -fx-padding: 5px;");

        getChildren().add(imageView);
    }

    public Card getCard() {
        return card;
    }

    public void setSpacingBetweenCards(double spacing) {
        setMargin(this, new Insets(0, 0, spacing, spacing));
    }
}
