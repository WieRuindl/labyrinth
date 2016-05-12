package characters.heroes;

import characters.Character;
import characters.enemies.Enemy;
import interfaces.Description;
import labyrinth.Cell;
import labyrinth.Direction;
import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.util.List;
import java.util.Map;

public abstract class Hero extends Character implements Description {

    private Animation animationIfDead;

    @Getter
    private boolean alive = true;

    @Getter
    @Setter
    protected int treasures = 0;

    @Override
    public void initAnimation() throws SlickException {
        animation = loadAnimation(getSource()+"/alive");
        animationIfDead = loadAnimation(getSource()+"/dead");
    }

    public void die() {
        alive = false;
        animation = animationIfDead;
    }

    public final void move(Direction direction) {
        Map<Direction, Cell> nearestCells = getNearestCells(cells, location);
        if (nearestCells.get(direction) != Cell.WALL) {
            location.translate(direction.getDx(), direction.getDy());
        }
    }

    protected final String getSource() {
        return "images/player/" + getClass().getSimpleName().toLowerCase();
    }

    public abstract void uniqueAction(Cell[][] cells, List<Enemy> enemies);

    public int getAnimationDuration() {
        return 1000;
    }
}
