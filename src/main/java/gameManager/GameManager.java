package gameManager;

import characters.enemies.Enemy;
import characters.heroes.Hero;
import interfaces.ShowMode;
import labyrinth.Cell;
import labyrinth.Direction;
import labyrinth.LabyrinthCreator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.GameSession;
import utils.Treasure;
import worlds.World;

import java.awt.*;
import java.util.*;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@PropertySource(value = "classpath:properties/default-game-session.properties")
public class GameManager {
    private Cell[][] cells;
    private Cell[][] visibleCells;
    private boolean needCellsUpdate;

    @Getter
    private List<Point> corners;

    @Getter
    World world;
    @Getter
    ShowMode mode;
    @Getter
    Hero hero;
    @Getter
    private List<Enemy> enemies = new ArrayList<>();
    @Getter
    private List<Treasure> treasures = new ArrayList<>();

    @Autowired
    private GameManager(GameSession session) {

        LabyrinthCreator labyrinth = new LabyrinthCreator(session.getWidth(), session.getHeight());

        cells = labyrinth.getCells();
        corners = labyrinth.getCorners();

        setMode(session);
        setWorld(session);
        setHero(session);
        createEnemies(session);
        createTreasures(session);
    }

    private void setMode(GameSession session) {
        mode = session.getMode();
    }

    private void setWorld(GameSession session) {
        world = session.getWorld();
    }

    private void setHero(GameSession session) {
        hero = session.getHero();
        hero.setCells(cells);
        hero.setLocation(new Point(1, 1));
    }

    private void createEnemies(GameSession session) {
        Collections.shuffle(corners);

        int min = Math.min(corners.size(), session.getEnemiesNum());
        List<Point> enemiesLocations = corners.subList(0, min);

        enemiesLocations.stream().forEach(location -> {
            Point p = new Point(location);
            try {
                Enemy enemy = world.createRandomEnemy();
                enemy.setLocation(p);
                enemy.setCells(cells);
                enemies.add(enemy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void createTreasures(GameSession session) {
        Collections.shuffle(corners);

        int min = Math.min(corners.size(), session.getTreasuresNum());
        List<Point> treasuresLocations = corners.subList(0, min);

        treasuresLocations.stream().forEach(location -> {
            Point p = new Point(location);
            treasures.add(new Treasure(p));
        });
    }

    public Cell[][] getCells() {
        if (needCellsUpdate || visibleCells == null) {
            visibleCells = mode.getVisibleCells(cells, hero);
            needCellsUpdate = false;
        }
        return visibleCells;
    }

    public void uniqueAction() {
        if (hero.isAlive()) {
            hero.uniqueAction(cells, enemies);
            checkForEnemy();
            needCellsUpdate = true;
        }
    }

    public void movePlayer(Direction direction) {
        if (hero.isAlive()) {
            hero.move(direction);
            checkForTreasure();
            checkForEnemy();
            needCellsUpdate = true;
        }
    }

    public void moveEnemy(Enemy enemy, int delta) {
        enemy.setDelay(enemy.getDelay() + delta);
        if (enemy.getDelay() >= enemy.getMovementDelay()) {
            enemy.move(hero.getLocation());
            enemy.setDelay(0);
            if (enemy.getLocation().equals(hero.getLocation())) {
                hero.die();
            }
        }
    }

    private void checkForEnemy() {
        enemies.stream().filter(enemy -> enemy.getLocation()
                .equals(hero.getLocation()))
                .findFirst().ifPresent(enemy -> hero.die());
    }

    private void checkForTreasure() {
        Optional<Treasure> treasure = treasures.stream()
                .filter(t -> t.getLocation().equals(hero.getLocation()))
                .findFirst();

        if (treasure.isPresent()) {
            treasures.remove(treasure.get());
            hero.setTreasures(hero.getTreasures() + 1);
            addNewTreasure();
        }
    }

    private void addNewTreasure() {
        LinkedList<Point> availableCorners = new LinkedList<>(corners);
        availableCorners.remove(hero.getLocation());
        for (Treasure treasure : treasures) {
            availableCorners.remove(treasure.getLocation());
        }
        Point location = availableCorners.get((int) (Math.random() * availableCorners.size()));
        treasures.add(new Treasure(location));
    }
}
