module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;
    requires com.google.gson;

    opens edu.augustana to javafx.fxml;
    exports edu.augustana;
    exports edu.augustana.ui;
    opens edu.augustana.ui to javafx.fxml;
    exports edu.augustana.filters;
    opens edu.augustana.filters to javafx.fxml;
    exports edu.augustana.data;
    opens edu.augustana.data to javafx.fxml;
}
