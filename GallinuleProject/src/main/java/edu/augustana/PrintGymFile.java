package edu.augustana;

import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PrintGymFile {

    public void printFile(AnchorPane anchorPane) {

        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

                // Create a copy of the content to avoid modifying the original
                AnchorPane scaledContent = createScaledContent(anchorPane, pageLayout);

                boolean success = job.printPage(pageLayout, scaledContent);

                if (success) {
                    job.endJob();
                }
            }
        } else {
            System.out.println("There is no printer connected");
        }
    }

    // Create a scaled copy of the content to fit on A4
    public AnchorPane createScaledContent(AnchorPane content, PageLayout pageLayout) {
        double scaleFactor = calculateScaleFactor(content, pageLayout);
        AnchorPane scaledContent = new AnchorPane(content.getChildren().toArray(new javafx.scene.Node[0]));
        scaledContent.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
        return scaledContent;
    }

    // Calculate the scale factor to fit the content on A4
    public double calculateScaleFactor(AnchorPane content, PageLayout pageLayout) {
        double scaleX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        return Math.max(scaleX, scaleY);
    }


}
