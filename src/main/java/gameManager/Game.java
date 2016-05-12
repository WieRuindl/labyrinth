package gameManager;

import characters.enemies.Enemy;
import characters.heroes.Hero;
import labyrinth.Cell;
import labyrinth.Direction;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Treasure;
import worlds.World;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Game extends BasicGameState {

    @Autowired
    private GameManager gameManager;

    private boolean exitFlag = false;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setForceExit(false);

        World world = gameManager.getWorld();
        Hero hero = gameManager.getHero();
        List<Enemy> enemies = gameManager.getEnemies();

        world.initImages();
        hero.initAnimation();
        for (Enemy enemy : enemies) {
            enemy.initAnimation();
        }
    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_LEFT) {
            gameManager.movePlayer(Direction.WEST);
        } else if (key == Input.KEY_RIGHT) {
            gameManager.movePlayer(Direction.EAST);
        } else if (key == Input.KEY_UP) {
            gameManager.movePlayer(Direction.SOUTH);
        } else if (key == Input.KEY_DOWN) {
            gameManager.movePlayer(Direction.NORTH);
        } else if (key == Input.KEY_C) {
            gameManager.uniqueAction();
        } else if (key == Input.KEY_ESCAPE) {
            exitFlag = true;
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Cell[][] cells = gameManager.getCells();

        drawWorld(g, cells);
        drawPlayer(g);
        drawEnemies(g, cells);
        drawTreasures(g, cells);
    }

    private void drawPlayer(Graphics g) {
        Hero hero = gameManager.getHero();
        Animation animation = hero.getAnimation();
        g.drawAnimation(animation, animation.getWidth() * hero.getLocation().x, animation.getHeight() * hero.getLocation().y);
    }

    private void drawTreasures(Graphics g, Cell[][] cells) {
        for (Treasure treasure : gameManager.getTreasures()) {
            Cell cell = cells[treasure.getLocation().y][treasure.getLocation().x];
            if (cell == Cell.FOG) {
                continue;
            }
            boolean present = gameManager.getEnemies().stream().filter(enemy -> enemy.getLocation().equals(treasure.getLocation())).findFirst().isPresent();
            if (present) {
                continue;
            }

            Image image = gameManager.getWorld().getAnimation(Cell.TREASURE);
            g.drawImage(image, image.getWidth() * treasure.getLocation().x, image.getHeight() * treasure.getLocation().y);
        }
    }

    private void drawEnemies(Graphics g, Cell[][] cells) {
        for (Enemy enemy : gameManager.getEnemies()) {
            int y = enemy.getLocation().y;
            int x = enemy.getLocation().x;
            Cell cell = cells[y][x];
            if (cell == Cell.FOG) {
                continue;
            }
            Animation animation = enemy.getAnimation();
            g.drawAnimation(animation, animation.getWidth() * x, animation.getHeight() * y);
        }
    }

    private void drawWorld(Graphics g, Cell[][] cells) {
        int height = cells.length;
        int width = cells[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = cells[y][x];
                Image image = gameManager.getWorld().getAnimation(cell);
                g.drawImage(image, image.getWidth() * x, image.getHeight() * y);
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        updatePlayer(delta);
        updateEnemies(delta);
        checkIfExitRequired(gc);
    }

    private void checkIfExitRequired(GameContainer gc) {
        if(exitFlag) {
            gc.exit();
        }
    }

    private void updateEnemies(int delta) {
        List<Enemy> enemies = gameManager.getEnemies();
        for (Enemy enemy : enemies) {
            enemy.getAnimation().update(delta);
            gameManager.moveEnemy(enemy, delta);
        }
    }

    private void updatePlayer(int delta) {
        Hero hero = gameManager.getHero();
        hero.getAnimation().update(delta);
    }

    public int getID() {
        return 0;
    }
}
