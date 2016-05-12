package characters.enemies.pyramid;

import characters.enemies.Enemy;
import labyrinth.Cell;
import labyrinth.Direction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("pyramid")
public class Mummy extends Enemy {
    private Point playerLastSawLocation;

    public Direction makeDecision(Cell[][] cells, Point location, Point playerLocation) {
        Direction dir = lookForPlayer(cells, location, playerLocation);
        if (dir != null) return dir;

        if (playerLastSawLocation != null && !location.equals(playerLastSawLocation)) {
            return keepDirection;
        }

        playerLastSawLocation = null;

        Map<Direction, Cell> nearestCells = getNearestCells(cells, location);

        java.util.List<Direction> availableDirections =
                nearestCells.keySet().stream()
                        .filter(direction -> nearestCells.get(direction) != Cell.WALL)
                        .collect(Collectors.toCollection(LinkedList::new));

        if (availableDirections.size() == 1) {
            keepDirection = availableDirections.get(0);
            return keepDirection;
        }

        availableDirections.remove(keepDirection.revert());
        int i = (int) (Math.random() * availableDirections.size());
        keepDirection = availableDirections.get(i);
        return keepDirection;
    }

    private Direction lookForPlayer(Cell[][] cells, Point location, Point playerLocation) {
        Direction direction;

        direction = lookForPlayerInDirection(cells, location, playerLocation, Direction.EAST);
        if (direction == null ) direction = lookForPlayerInDirection(cells, location, playerLocation, Direction.WEST);
        if (direction == null ) direction = lookForPlayerInDirection(cells, location, playerLocation, Direction.SOUTH);
        if (direction == null ) direction = lookForPlayerInDirection(cells, location, playerLocation, Direction.NORTH);

        return direction;
    }

    private Direction lookForPlayerInDirection(Cell[][] cells, Point location, Point playerLocation, Direction direction) {
        for (int i = 1; ; i++) {
            Point target = new Point(location.x + i * direction.getDx(), location.y + i * direction.getDy());
            if (cells[target.y][target.x] == Cell.WALL) {
                break;
            }
            if (target.equals(playerLocation)) {
                playerLastSawLocation = new Point(playerLocation);
                keepDirection = direction;
                return keepDirection;
            }
        }
        return null;
    }

    public double getMovementDelay() {
        return 1000;
    }

    protected int getAnimationDuration() {
        return 1000;
    }

}
