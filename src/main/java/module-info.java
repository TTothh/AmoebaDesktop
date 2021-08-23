module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

	opens org.openjfx to javafx.fxml;
    exports org.openjfx;
	exports org.openjfx.model;
	opens org.openjfx.model to javafx.fxml;
	exports org.openjfx.controller;
	opens org.openjfx.controller to javafx.fxml;
}