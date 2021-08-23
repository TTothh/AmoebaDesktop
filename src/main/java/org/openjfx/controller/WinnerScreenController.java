package org.openjfx.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import org.openjfx.model.VsPlayerModel;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnerScreenController implements Initializable {

	public Label winner;

	@FXML
	protected void initialize() {
		VsPlayerModel vsPlayerModel = new VsPlayerModel();
		winner.setText((vsPlayerModel.isPlayer1()) ? "Player 1" : "Player 2");
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		initialize();
	}
}
