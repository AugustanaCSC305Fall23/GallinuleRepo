package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FavoriteSet {

    public static final String FAVORITES_FILENAME = "favorites.json";
    private Set<String> favoriteIDs = new HashSet<>();

    public void addFavorite(Card card) {
        favoriteIDs.add(card.getCode());
    }

    public void removeFavorite(Card card) {
        favoriteIDs.remove(card.getCode());
    }

    public boolean contains(Card card) {
        return favoriteIDs.contains(card.getCode());
    }


    public Set<String> getFavoriteIDs(){
        return favoriteIDs;
    }

    public static FavoriteSet loadFromFile() throws IOException {
        FileReader reader = new FileReader(new File(FAVORITES_FILENAME));
        Gson gson = new Gson();
        return gson.fromJson(reader,FavoriteSet.class);
    }

    public void saveToFile() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String favoritesJSON = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(new File(FAVORITES_FILENAME)));
        writer.println(favoritesJSON);
        writer.close();
    }
}
