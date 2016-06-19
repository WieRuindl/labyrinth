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

    public void setScore(int score) {
        String text = score + ((score == 1) ? " treasure" : " treasures");
        scoreLabel.setText(text);
    }

    @FXML
    private void handleOk() {
        stage.close();
    }

    public String getName() {
        return nameField.getText();
    }
}
