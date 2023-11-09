package edu.augustana;

import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;

public class PrintGymFile {


    public void printFile(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob(Printer.getDefaultPrinter());

        if (job != null) {
            boolean showDialog = job.showPrintDialog(null);

            if (showDialog) {
                boolean success = job.printPage(node);

                if (success) {
                    job.endJob();
                }
            }
        }else{

            System.out.println("There is no printer connected");
        }
    }

}
