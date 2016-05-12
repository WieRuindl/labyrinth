package gui;

import utils.GameResult;
import utils.HighScores;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HighScoresController {

    @FXML
    private TableView<GameResult> results;

    @FXML
    private TableColumn<GameResult, String> score;
    @FXML
    private TableColumn<GameResult, String> name;
    @FXML
    private TableColumn<GameResult, String> hero;
    @FXML
    private TableColumn<GameResult, String> mode;
    @FXML
    private TableColumn<GameResult, String> world;
    @FXML
    private TableColumn<GameResult, String> width;
    @FXML
    private TableColumn<GameResult, String> height;
    @FXML
    private TableColumn<GameResult, String> enemies;
    @FXML
    private TableColumn<GameResult, String> treasures;

    @FXML
    private void initialize() {
        score.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getTreasuresFound()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getName()));
        hero.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getHero()));
        mode.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getMode()));
        world.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getWorld()));
        width.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getWidth()));
        height.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getHeight()));
        enemies.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getEnemiesNum()));
        treasures.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getTreasuresNum()));
    }

    public void setHighScores(HighScores highScores) {
        ObservableList<GameResult> gameResults = FXCollections.observableArrayList(highScores.getGameResults());
        results.setItems(gameResults);
    }
}
