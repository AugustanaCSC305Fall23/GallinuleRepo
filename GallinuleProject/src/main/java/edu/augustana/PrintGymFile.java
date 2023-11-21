package edu.augustana;

import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

public class PrintGymFile {


    public void printFile(FlowPane flowPane) {


        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                boolean success = job.printPage(flowPane);

                if (success) {
                    job.endJob();
                }
            }
        } else {

            System.out.println("There is no printer connected");
        }
    }

}
