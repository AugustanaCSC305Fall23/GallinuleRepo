package edu.augustana;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class PrintGymFile {


    public void printFile(AnchorPane AnchorPane) {


        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);
            PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A5, PageOrientation.LANDSCAPE, 0, 0, 0, 0);
            if (showDialog) {
                boolean success = job.printPage(pageLayout, AnchorPane);

                if (success) {
                    job.endJob();
                }
            }
        } else {

            System.out.println("There is no printer connected");
        }
    }

}
