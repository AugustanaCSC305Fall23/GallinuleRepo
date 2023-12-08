package edu.augustana;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PrintGymFileTest {

    @Test
    public void testPrintFile() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy VBox with some content (for testing purposes)
        VBox vBox = new VBox(new Rectangle(100, 100));

        // Execute the printFile method and check for success
        assertDoesNotThrow(() -> printGymFile.printFile(Collections.singletonList(vBox)));
    }

    @Test
    public void testCreateScaledContent() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy VBox with some content (for testing purposes)
        VBox vBox = new VBox(new Rectangle(100, 100));

        // Create a dummy PageLayout (for testing purposes)
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

        // Execute the createScaledContent method and check for success
        VBox scaledContent = printGymFile.createScaledContent(vBox, pageLayout);
        assertNotNull(scaledContent);
    }

    @Test
    public void testCalculateScaleFactor() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy VBox with some content (for testing purposes)
        VBox vBox = new VBox(new Rectangle(100, 100));

        // Create a dummy PageLayout (for testing purposes)
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

        // Execute the calculateScaleFactor method and check for success
        double scaleFactor = printGymFile.calculateScaleFactor(vBox, pageLayout);
        assertTrue(scaleFactor > 0);
    }

    @Test
    public void testPrintFileTextOnly() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy AnchorPane with some content (for testing purposes)
        AnchorPane anchorPane = new AnchorPane(new Rectangle(100, 100));

        // Execute the printFileTextOnly method and check for success
        assertDoesNotThrow(() -> printGymFile.printFileTextOnly(anchorPane));
    }

    @Test
    public void testCreateScaledContentTextOnly() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy AnchorPane with some content (for testing purposes)
        AnchorPane anchorPane = new AnchorPane(new Rectangle(100, 100));

        // Create a dummy PageLayout (for testing purposes)
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

        // Execute the createScaledContentTextOnly method and check for success
        AnchorPane scaledContent = printGymFile.createScaledContentTextOnly(anchorPane, pageLayout);
        assertNotNull(scaledContent);
    }
}
