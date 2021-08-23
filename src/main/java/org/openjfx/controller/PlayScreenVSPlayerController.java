package org.openjfx.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.openjfx.App;
import org.openjfx.model.VsPlayerModel;

public class PlayScreenVSPlayerController {

    VsPlayerModel vsPlayerModel = new VsPlayerModel();

    @FXML
    private void switchToStartScreen() throws IOException {
        App.setRoot("startScreen");
    }

    public void fieldClickHandler(MouseEvent e) throws IOException {
        Label source = (Label) e.getSource();

        if(!source.getText().equals("")) {
            return;
        }

        vsPlayerModel.setClickedX((int) (source.getLayoutX() + 1) / 40);
        vsPlayerModel.setClickedY((int) source.getLayoutY() / 40);

        source.setText((vsPlayerModel.isPlayer1()) ? "X" : "O");
        vsPlayerModel.setboardTile();



        if(vsPlayerModel.checkWin()) {
            App.setRoot("winnerScreen");
        }
        vsPlayerModel.switchPlayer();

        //System.out.println(model.getClickedY() + " : " + model.getClickedX());

    }
}