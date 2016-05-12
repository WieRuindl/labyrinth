package utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "scores")
public class HighScores {

    private static int MAX_SIZE = 10;

    List<GameResult> gameResults = new LinkedList<>();

    @XmlElement(name = "session")
    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public void clear() {
        gameResults = new LinkedList<>();
    }

    public void add(GameResult result) {
        if (gameResults.size() < MAX_SIZE) {
            gameResults.add(result);
            gameResults.sort((GameResult o1, GameResult o2) -> o2.getTreasuresFound() - o1.getTreasuresFound());
        } else if (result.getTreasuresFound() > gameResults.get(MAX_SIZE - 1).getTreasuresFound()) {
            gameResults.remove(gameResults.size() - 1);
            gameResults.add(result);
            gameResults.sort((GameResult o1, GameResult o2) -> o2.getTreasuresFound() - o1.getTreasuresFound());
        }
    }
}
