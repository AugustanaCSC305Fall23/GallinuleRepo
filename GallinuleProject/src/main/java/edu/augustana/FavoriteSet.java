package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a set of favorite cards and provides methods to manage favorites.
 */
public class FavoriteSet {

    /**
     * The filename for storing favorites.
     */
    public static final String FAVORITES_FILENAME = "favorites.json";

    /**
     * Set to store favorite card IDs.
     */
    private final Set<String> favoriteIDs = new HashSet<>();

    /**
     * Adds a card to the set of favorites.
     *
     * @param card The card to be added to favorites.
     */
    public void addFavorite(Card card) {
        favoriteIDs.add(card.getCode());
    }

    /**
     * Removes a card from the set of favorites.
     *
     * @param card The card to be removed from favorites.
     */
    public void removeFavorite(Card card) {
        favoriteIDs.remove(card.getCode());
    }

    /**
     * Checks if a card is in the set of favorites.
     *
     * @param card The card to be checked.
     * @return True if the card is a favorite, false otherwise.
     */
    public boolean contains(Card card) {
        return favoriteIDs.contains(card.getCode());
    }


    /**
     * Gets the set of favorite card IDs.
     *
     * @return The set of favorite card IDs.
     */
    public Set<String> getFavoriteIDs(){
        return favoriteIDs;
    }

    /**
     * Loads the favorite set from the file.
     *
     * @return The loaded FavoriteSet.
     * @throws IOException If an error occurs while reading from the file.
     */
    public static FavoriteSet loadFromFile() throws IOException {
        FileReader reader = new FileReader(new File(FAVORITES_FILENAME));
        Gson gson = new Gson();
        return gson.fromJson(reader,FavoriteSet.class);
    }

    /**
     * Saves the favorite set to the file.
     *
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String favoritesJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(new File(FAVORITES_FILENAME)));
        writer.println(favoritesJSON);
        writer.close();
    }
}
