package gui;

import gameManager.Action;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.newdawn.slick.Input;

public class ControlsController {
    @FXML
    private Label moveUpLabel;
    @FXML
    private Label moveDownLabel;
    @FXML
    private Label moveLeftLabel;
    @FXML
    private Label moveRightLabel;
    @FXML
    private Label specialActionLabel;

    @FXML
    private void initialize() {
        moveUpLabel.setText("" + Input.getKeyName(Action.MOVE_SOUTH.getKey()));
        moveDownLabel.setText("" + Input.getKeyName(Action.MOVE_NORTH.getKey()));
        moveLeftLabel.setText("" + Input.getKeyName(Action.MOVE_WEST.getKey()));
        moveRightLabel.setText("" + Input.getKeyName(Action.MOVE_EAST.getKey()));
        specialActionLabel.setText("" + Input.getKeyName(Action.SPECIAL_ACTION.getKey()));
    }
}
