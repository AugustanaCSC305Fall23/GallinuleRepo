package edu.augustana;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrintGymFileTest {
    @Test
    public void testPrintFile() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy AnchorPane with some content (for testing purposes)
        AnchorPane anchorPane = new AnchorPane(new Rectangle(100, 100));

        // Execute the printFile method and check for success
        assertDoesNotThrow(() -> printGymFile.printFile(anchorPane));
    }

    @Test
    public void testCreateScaledContent() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy AnchorPane with some content (for testing purposes)
        AnchorPane anchorPane = new AnchorPane(new Rectangle(100, 100));

        // Create a dummy PageLayout (for testing purposes)
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

        // Execute the createScaledContent method and check for success
        AnchorPane scaledContent = printGymFile.createScaledContent(anchorPane, pageLayout);
        assertNotNull(scaledContent);
    }

    @Test
    public void testCalculateScaleFactor() {
        // Create an instance of PrintGymFile
        PrintGymFile printGymFile = new PrintGymFile();

        // Create a dummy AnchorPane with some content (for testing purposes)
        AnchorPane anchorPane = new AnchorPane(new Rectangle(100, 100));

        // Create a dummy PageLayout (for testing purposes)
        PageLayout pageLayout = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

        // Execute the calculateScaleFactor method and check for success
        double scaleFactor = printGymFile.calculateScaleFactor(anchorPane, pageLayout);
        assertTrue(scaleFactor > 0);
    }
}
