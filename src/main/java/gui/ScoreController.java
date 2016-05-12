package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

public class ScoreController {

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField nameField;

    @Setter
    private Stage stage;

    @Setter
    private int score;


    @FXML
    private void initialize() {
        scoreLabel.setText(score+" treasures");
    }

    @FXML
    private void handleOk() {
        stage.close();
    }

    public String getName() {
        return nameField.getText();
    }
}
