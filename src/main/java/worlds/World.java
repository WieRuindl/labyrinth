package worlds;

import characters.enemies.Enemy;
import interfaces.Description;
import labyrinth.Cell;
import lombok.Getter;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class World implements Description {

    @Getter
    private final List<Class<? extends Enemy>> enemies;

    private Image fog;
    private Image wall;
    private Image floor;
    private Image chest;

    protected World(List<Enemy> enemies) {
        this.enemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            this.enemies.add(enemy.getClass());
        }
    }

    public void initImages() throws SlickException {
        String world = getClass().getSimpleName().toLowerCase();
        fog = new Image("images/worlds/" + world + "/fog.png");
        wall = new Image("images/worlds/" + world + "/wall.png");
        floor = new Image("images/worlds/" + world + "/floor.png");
        chest = new Image("images/worlds/" + world + "/chest.png");
    }

    public final Image getAnimation(Cell cell) {
        switch (cell) {
            case EMPTY:
                return floor;
            case WALL:
                return wall;
            case FOG:
                return fog;
            case TREASURE:
                return chest;
            default:
                throw new RuntimeException("No such cell image =(");
        }
    }

    public final Enemy createRandomEnemy() throws Exception {
        int i = (int) (Math.random() * enemies.size());
        Class<? extends Enemy> clazz = enemies.get(i);

        return clazz.newInstance();
    }

    public final Enemy createEnemy(String enemyName) throws Exception {
        Optional<Class<? extends Enemy>> first = enemies.stream().filter(enemy -> enemy.getClass().getSimpleName().equals(enemyName)).findFirst();
        if (!first.isPresent()) {
            throw new RuntimeException();
        }
        return first.get().newInstance();
    }
}
