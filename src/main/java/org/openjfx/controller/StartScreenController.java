package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.openjfx.App;
import org.openjfx.model.StartModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

	public Button startButton;
	public RadioButton player;
	public RadioButton ai;

	ToggleGroup tg = new ToggleGroup();

	StartModel model = new StartModel();

	@FXML
	private void startGame() throws IOException {
		if(model.isVSPlayer()) {
			App.setRoot("playScreenVSPlayer");
		} else {
			App.setRoot("playScreenVSComputer");
		}
	}

	public void switchToPlayer() {
		model.setVSPlayer(true);
	}

	public void switchToAI() {
		model.setVSPlayer(false);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		StartModel model = new StartModel();
		player.setToggleGroup(tg);
		ai.setToggleGroup(tg);
		player.setSelected(true);
		model.setVSPlayer(true);
	}
}
