module cms.stephenwongc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens cms.stephenwongc195 to javafx.fxml;
    opens cms.stephenwongc195.controller to javafx.fxml;
    opens cms.stephenwongc195.model to javafx.fxml;
    exports cms.stephenwongc195.controller;
    exports cms.stephenwongc195.model;
    exports cms.stephenwongc195;
}