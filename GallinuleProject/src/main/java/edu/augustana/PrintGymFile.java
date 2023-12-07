package edu.augustana;

import javafx.print.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PrintGymFile {

    public void printFile(List<VBox> vboxList) {

        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.NA_LETTER, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

                for(VBox page : vboxList) {
                    System.out.println("Vbox: " + page);
                    // Create a copy of the content to avoid modifying the original
                    VBox scaledContent = createScaledContent(page, pageLayout);

                    boolean success = job.printPage(pageLayout, scaledContent);
                    if (!success) {
                        //show alert about failing to print page X
                        System.out.println("ERROR creating printable version of page X");
                        return;
                    }
                }

                boolean spooled = job.endJob();
                if (spooled) {
                    System.out.println("Print job sent to printer queue");
                } else {
                    System.out.println("Failed to queue print job");
                }

            }
        } else {
            System.out.println("There is no printer connected");
        }
    }

    public void printFileTextOnly(AnchorPane anchorPane) {

        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

                // Create a copy of the content to avoid modifying the original
                AnchorPane scaledContent = createScaledContentTextOnly(anchorPane, pageLayout);

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
    public AnchorPane createScaledContentTextOnly(AnchorPane content, PageLayout pageLayout) {
        double scaleFactor = calculateScaleFactorTextOnly(content, pageLayout);
        AnchorPane scaledContent = new AnchorPane(content.getChildren().toArray(new javafx.scene.Node[0]));
        scaledContent.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
        return scaledContent;
    }

    // Calculate the scale factor to fit the content on A4
    public double calculateScaleFactorTextOnly(AnchorPane content, PageLayout pageLayout) {
        double scaleX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        return Math.max(scaleX, scaleY);
    }

    // Create a scaled copy of the content to fit on A4
    public VBox createScaledContent(VBox content, PageLayout pageLayout) {
        double scaleFactor = calculateScaleFactor(content, pageLayout);
        VBox scaledContent = new VBox(content.getChildren().toArray(new javafx.scene.Node[0]));
        scaledContent.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
        return scaledContent;
    }

    // Calculate the scale factor to fit the content on A4
    public double calculateScaleFactor(VBox content, PageLayout pageLayout) {
        double scaleX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        return Math.max(scaleX, scaleY);
    }


}
