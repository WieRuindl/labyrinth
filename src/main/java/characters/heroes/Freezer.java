package characters.heroes;

import characters.enemies.Enemy;
import labyrinth.Cell;
import labyrinth.Direction;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class Freezer extends Hero {
    public void uniqueAction(Cell[][] cells, List<Enemy> enemies) {
        Point north = new Point(location.x + Direction.NORTH.getDx(), location.y + Direction.NORTH.getDy());
        Point south = new Point(location.x + Direction.SOUTH.getDx(), location.y + Direction.SOUTH.getDy());
        Point west = new Point(location.x + Direction.WEST.getDx(), location.y + Direction.WEST.getDy());
        Point east = new Point(location.x + Direction.EAST.getDx(), location.y + Direction.EAST.getDy());

        enemies.stream()
                .filter(enemy ->
                        enemy.getLocation().equals(north) ||
                        enemy.getLocation().equals(south) ||
                        enemy.getLocation().equals(west) ||
                        enemy.getLocation().equals(east)
                )
                .forEach(enemy -> {
                    int delay = enemy.getDelay();
                    if (delay >= 0) {
                        enemy.setDelay(delay - 3000);
                    }
                });
    }

    public String getDescription() {
        return "Mystical hero, studied to the forbidden magic of Necronomicon, available to freeze his enemies";
    }
}
