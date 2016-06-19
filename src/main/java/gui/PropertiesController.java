package gui;

import utils.DataContainer;
import characters.heroes.Hero;
import interfaces.ShowMode;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import worlds.World;

import java.util.ArrayList;
import java.util.List;

public class PropertiesController {
    @FXML
    private TableView<Hero> heroesTable;
    @FXML
    private TableColumn<Hero, String> heroes;
    @FXML
    private Label heroDescription;

    @FXML
    private TableView<World> worldsTable;
    @FXML
    private TableColumn<World, String> worlds;
    @FXML
    private Label worldDescription;

    @FXML
    private TableView<ShowMode> showModesTable;
    @FXML
    private TableColumn<ShowMode, String> showModes;
    @FXML
    private Label modeDescription;

    @FXML
    private TextField widthLabel;
    @FXML
    private TextField heightLabel;
    @FXML
    private TextField enemiesLabel;
    @FXML
    private TextField treasuresLabel;

    @Getter
    private Hero hero;
    @Getter
    private World world;
    @Getter
    private ShowMode mode;
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int enemies;
    @Getter
    private int treasures;

    @Setter
    private Stage stage;

    @FXML
    private void initialize() {
        heroes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        showHeroDetails(null);
        heroesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHeroDetails(newValue));

        worlds.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        showWorldDetails(null);
        worldsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showWorldDetails(newValue));

        showModes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        showModeDetails(null);
        showModesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showModeDetails(newValue));
    }

    private void showModeDetails(ShowMode mode) {
        if (mode != null) {
            modeDescription.setText(splitToMultiline(mode.getDescription()));
        } else {
            modeDescription.setText("");
        }
    }

    private void showWorldDetails(World world) {
        if (world != null) {
            worldDescription.setText(splitToMultiline(world.getDescription()));
        } else {
            worldDescription.setText("");
        }
    }

    private void showHeroDetails(Hero hero) {
        if (hero != null) {
            heroDescription.setText(splitToMultiline(hero.getDescription()));
        } else {
            heroDescription.setText("");
        }
    }

    private String splitToMultiline(String text) {
        int size = 40;

        List<String> strings = new ArrayList<>();
        String[] split = text.split(" ");

        int i = 0;
        while (i < split.length) {
            StringBuilder builder = new StringBuilder();
            while (i < split.length && builder.length() + split[i].length() + 1 <= size) {
                builder.append(split[i]).append(" ");
                i++;
            }
            strings.add(builder.toString());
        }

        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string).append('\n');
        }

        return builder.toString();
    }

    public void setDataContainer(DataContainer data) {
        ObservableList<Hero> heroes = FXCollections.observableArrayList(data.getHeroes());
        heroesTable.setItems(heroes);
        heroesTable.getSelectionModel().selectFirst();

        ObservableList<World> worlds = FXCollections.observableArrayList(data.getWorlds());
        worldsTable.setItems(worlds);
        worldsTable.getSelectionModel().selectFirst();

        ObservableList<ShowMode> modes = FXCollections.observableArrayList(data.getShowModes());
        showModesTable.setItems(modes);
        showModesTable.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleOk() {
        hero = heroesTable.getSelectionModel().getSelectedItem();
        world = worldsTable.getSelectionModel().getSelectedItem();
        mode = showModesTable.getSelectionModel().getSelectedItem();

        width = Integer.parseInt(widthLabel.getText());
        height = Integer.parseInt(heightLabel.getText());
        enemies = Integer.parseInt(enemiesLabel.getText());
        treasures = Integer.parseInt(treasuresLabel.getText());

        stage.close();
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }
}
