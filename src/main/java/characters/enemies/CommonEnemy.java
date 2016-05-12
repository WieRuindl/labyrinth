package characters.enemies;

import labyrinth.Cell;
import labyrinth.Direction;

import java.awt.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CommonEnemy extends Enemy {

    public Direction makeDecision(Cell[][] cells, Point location, Point playerLocation) {

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
}
