package edu.augustana;

import javafx.print.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Utility class for printing Gym files.
 */
public class PrintGymFile {

    /**
     * Prints the specified list of VBoxes.
     *
     * @param vboxList The list of VBoxes to be printed.
     */
    public void printFile(List<VBox> vboxList) {

        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.NA_LETTER, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);

                for (VBox page : vboxList) {
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

    /**
     * Prints the content of the specified AnchorPane in text-only mode.
     *
     * @param anchorPane The AnchorPane to be printed.
     */
    public void printFileTextOnly(AnchorPane anchorPane) {

        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

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


    /**
     * Creates a scaled copy of the content (AnchorPane) to fit on A4 in text-only mode.
     *
     * @param content    The original content to be scaled.
     * @param pageLayout The PageLayout specifying the target size and orientation.
     * @return Scaled AnchorPane.
     */
    public AnchorPane createScaledContentTextOnly(AnchorPane content, PageLayout pageLayout) {
        double scaleFactorX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleFactorY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        double scaleFactor = Math.min(scaleFactorX, scaleFactorY);

        AnchorPane scaledContent = new AnchorPane(content.getChildren().toArray(new javafx.scene.Node[0]));
        scaledContent.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
        return scaledContent;
    }


    /**
     * Calculates the scale factor to fit the content on A4 in text-only mode.
     *
     * @param content    The original content.
     * @param pageLayout The PageLayout specifying the target size and orientation.
     * @return The calculated scale factor.
     */
    public double calculateScaleFactorTextOnly(AnchorPane content, PageLayout pageLayout) {
        double scaleX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        return Math.max(scaleX, scaleY);
    }

    /**
     * Creates a scaled copy of the content (VBox) to fit on A4.
     *
     * @param content    The original content to be scaled.
     * @param pageLayout The PageLayout specifying the target size and orientation.
     * @return Scaled VBox.
     */
    public VBox createScaledContent(VBox content, PageLayout pageLayout) {
        double scaleFactorX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleFactorY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        double scaleFactor = Math.min(scaleFactorX, scaleFactorY);

        VBox scaledContent = new VBox(content.getChildren().toArray(new javafx.scene.Node[0]));
        scaledContent.getTransforms().add(new javafx.scene.transform.Scale(scaleFactor, scaleFactor));
        return scaledContent;
    }


    /**
     * Calculates the scale factor to fit the content on A4.
     *
     * @param content    The original content.
     * @param pageLayout The PageLayout specifying the target size and orientation.
     * @return The calculated scale factor.
     */
    public double calculateScaleFactor(VBox content, PageLayout pageLayout) {
        double scaleX = pageLayout.getPrintableWidth() / content.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / content.getBoundsInParent().getHeight();

        // Use the minimum scale factor to maintain aspect ratio
        return Math.max(scaleX, scaleY);
    }


}
