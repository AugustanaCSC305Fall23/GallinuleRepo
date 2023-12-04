//package edu.augustana;
//import javafx.application.Platform;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class CardTest {
//
//    @Test
//    public void testCardConstructor() {
//        //CODE,Event,Category,Title,Pack Folder,Image,Gender,Model Sex,Level,Equipment,Keywords
//        String[] csvRowData = {"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};
//        Card card = new Card(csvRowData);
//        assertEquals("S1", card.getCode());
//        assertEquals("ALL", card.getEvent());
//        assertEquals("Shapes", card.getCategory());
//        assertEquals("Hollow", card.getTitle());
//        assertEquals("Demo1", card.getPackFolder());
//        assertEquals("1.png", card.getImg());
//        assertEquals("N", card.getGender());
//        assertEquals("F", card.getModelSex());
//        assertEquals("ALL", card.getLevel().get(0));
//        assertEquals("None", card.getEquipments().get(0));
//        assertEquals("hollow, dish, banana", card.getKeywords().get(0));
//    }
//
//    @Test
//    public void testCreateImageView() {
//        // Initialize JavaFX Toolkit on the Application Thread
//        Platform.startup(() -> {
//            String[] csvRowData = {"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};
//            Card card = new Card(csvRowData);
//
//            // Run the actual test inside the JavaFX thread
//            Platform.runLater(() -> {
//                assertNotNull(card.createHighResolutionImageView());
//            });
//        });
//    }
//
//    @Test
//    public void testToString() {
//        String[] csvRowData = {"S1", "ALL", "Shapes", "Hollow", "Demo1", "1.png", "N", "F", "ALL", "None", "hollow, dish, banana"};
//        Card card = new Card(csvRowData);
//        assertEquals("Card{code='S1', event='ALL', category='Shapes', title='Hollow', packFolder='Demo1', img='1.png', gender='N', modelSex='F', level=[ALL], equipments=[None], keywords=[hollow, dish, banana]}", card.toString());
//    }
//}
