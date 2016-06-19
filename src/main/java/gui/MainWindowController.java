package gui;

import org.lwjgl.opengl.Display;
import utils.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.newdawn.slick.AppGameContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainWindowController {

    @FXML
    private Label hero;
    @FXML
    private Label world;
    @FXML
    private Label size;
    @FXML
    private Label mode;

    private ApplicationContext context;
    private HighScores highScores;

    @FXML
    private void initialize() {
        context = new ClassPathXmlApplicationContext("xml-configurations/main.xml");

        initScores();

        updateGameSessionInfo();
    }

    private void updateGameSessionInfo() {
        GameSession session = context.getBean(GameSession.class);

        hero.setText(session.getHero().getClass().getSimpleName());
        world.setText(session.getWorld().getClass().getSimpleName());
        size.setText(""+session.getWidth()+"x"+session.getHeight());
        mode.setText(session.getMode().getClass().getSimpleName());
    }

    private void initScores() {
        File scoresFile = new File("scores.xml");
        if (!scoresFile.exists()) {
            createEmptyFile(scoresFile);
            highScores = new HighScores();
        } else {
            createScoresFile(scoresFile);
        }
    }

    private void createEmptyFile(File scoresFile) {
        try {
            scoresFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createScoresFile(File scoresFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(HighScores.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            highScores = (HighScores) unmarshaller.unmarshal(scoresFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startGame() {
        try {
            AppGameContainer gameContainer = context.getBean(AppGameContainer.class);

            gameContainer.start();

            GameSession session = context.getBean(GameSession.class);

            saveScores(session);

            Display.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showControlsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();

            URL resource = getClass().getClassLoader().getResource("gui/views/controls.fxml");
            loader.setLocation(resource);
            AnchorPane page = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            stage.setScene(scene);

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveScores(GameSession session) {
        try {
            FXMLLoader loader = new FXMLLoader();

            URL resource = getClass().getClassLoader().getResource("gui/views/score.fxml");
            loader.setLocation(resource);
            AnchorPane page = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Stage");
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            stage.setScene(scene);

            ScoreController controller = loader.getController();
            controller.setStage(stage);
            controller.setScore(session.getHero().getTreasures());

            stage.showAndWait();

            String name = controller.getName();
            GameResult result = GameResultsAdapter.getResult(session, name);

            highScores.add(result);
            saveScoresToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveScoresToFile() {
        File scoresFile = new File("scores.xml");
        if (!scoresFile.exists()) {
            createEmptyFile(scoresFile);
            highScores = new HighScores();
        }
        try {
            JAXBContext context = JAXBContext.newInstance(HighScores.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(highScores, scoresFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showPropertiesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL resource = getClass().getClassLoader().getResource("gui/views/properties.fxml");
            loader.setLocation(resource);
            AnchorPane page = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Properties");
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            stage.setScene(scene);

            PropertiesController controller = loader.getController();
            controller.setStage(stage);

            controller.setDataContainer(context.getBean(DataContainer.class));
            stage.showAndWait();

            setProps(controller);
            updateGameSessionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProps(PropertiesController controller) {
        GameSession bean = context.getBean(GameSession.class);

        bean.setWidth(controller.getWidth());
        bean.setHeight(controller.getHeight());
        bean.setTreasuresNum(controller.getTreasures());
        bean.setEnemiesNum(controller.getEnemies());
        bean.setWorld(controller.getWorld());
        bean.setMode(controller.getMode());
        bean.setHero(controller.getHero());
    }

    @FXML
    private void showHighScores() {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL resource = getClass().getClassLoader().getResource("gui/views/high-scores.fxml");
            loader.setLocation(resource);
            AnchorPane page = loader.load();

            Stage stage = new Stage();
            stage.setTitle("High Scores");
            stage.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(page);
            stage.setScene(scene);

            HighScoresController controller = loader.getController();
            controller.setHighScores(highScores);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
