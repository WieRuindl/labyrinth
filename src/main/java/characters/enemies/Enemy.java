package characters.enemies;

import characters.Character;
import labyrinth.Cell;
import labyrinth.Direction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;

@RequiredArgsConstructor
public abstract class Enemy extends Character {

    protected Direction keepDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];

    @Getter
    @Setter
    private int delay = 0;

    public abstract double getMovementDelay();

    protected final String getSource() {
        return "images/enemy/" + getClass().getSimpleName().toLowerCase();
    }

    public final void move(Point player) {
        Direction direction = makeDecision(cells, location, player);
        location.translate(direction.getDx(), direction.getDy());
    }

    protected abstract Direction makeDecision(Cell[][] cells, Point location, Point playerLocation);
}
