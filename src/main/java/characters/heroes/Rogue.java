package characters.heroes;

import characters.enemies.Enemy;
import labyrinth.Cell;
import labyrinth.Direction;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class Rogue extends Hero {

    public void uniqueAction(Cell[][] cells, List<Enemy> enemies) {
        int x = location.x;
        int y = location.y;

        int nearestWalls =
                (cells[y + Direction.NORTH.getDy()][x + Direction.NORTH.getDx()] == Cell.WALL ? 1 : 0) +
                        (cells[y + Direction.SOUTH.getDy()][x + Direction.SOUTH.getDx()] == Cell.WALL ? 1 : 0) +
                        (cells[y + Direction.EAST.getDy()][x + Direction.EAST.getDx()] == Cell.WALL ? 1 : 0) +
                        (cells[y + Direction.WEST.getDy()][x + Direction.WEST.getDx()] == Cell.WALL ? 1 : 0);

        if (nearestWalls == 3) {
            if (cells[y + Direction.NORTH.getDy()][x + Direction.NORTH.getDx()] == Cell.EMPTY) {
                Point target = new Point(location.x + Direction.NORTH.getDx() * 2, location.y + Direction.NORTH.getDy() * 2);
                setLocation(target);
            }
            if (cells[y + Direction.SOUTH.getDy()][x + Direction.SOUTH.getDx()] == Cell.EMPTY) {
                Point target = new Point(location.x + Direction.SOUTH.getDx() * 2, location.y + Direction.SOUTH.getDy() * 2);
                setLocation(target);
            }
            if (cells[y + Direction.EAST.getDy()][x + Direction.EAST.getDx()] == Cell.EMPTY) {
                Point target = new Point(location.x + Direction.EAST.getDx() * 2, location.y + Direction.EAST.getDy() * 2);
                setLocation(target);
            }
            if (cells[y + Direction.WEST.getDy()][x + Direction.WEST.getDx()] == Cell.EMPTY) {
                Point target = new Point(location.x + Direction.WEST.getDx() * 2, location.y + Direction.WEST.getDy() * 2);
                setLocation(target);
            }
        }
    }

    public String getDescription() {
        return "Agile hero who can escape even from hopeless situations surrounded by enemies";
    }
}
