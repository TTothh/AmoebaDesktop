package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.openjfx.App;
import org.openjfx.model.VsComputerModel;

import java.io.IOException;

public class PlayScreenVSComputerController {

	public GridPane board;

	VsComputerModel vsComputerModel = new VsComputerModel();
	@FXML
	private void switchToStartScreen() throws IOException {
		App.setRoot("startScreen");
	}

	public void fieldClickHandler(MouseEvent e) throws IOException {
		Label source = (Label) e.getSource();

		if (!source.getText().equals("")) {
			return;
		}

		vsComputerModel.setClickedX((int) (source.getLayoutX() + 1) / 40);
		vsComputerModel.setClickedY((int) source.getLayoutY() / 40);

		synchronized (this) {
			vsComputerModel.placeAITile();
			vsComputerModel.setboardTile();
			Label l = (Label) vsComputerModel.getNode(board);
			l.setText("O");
		}

		source.setText((vsComputerModel.isPlayer1()) ? "X" : "O");

		if (vsComputerModel.checkWin()) {
			App.setRoot("winnerScreen");
		}
		vsComputerModel.switchPlayer();

		//System.out.println(model.getClickedY() + " : " + model.getClickedX());
	}
}
