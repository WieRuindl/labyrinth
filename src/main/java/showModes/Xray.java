package showModes;

import characters.heroes.Hero;
import interfaces.ShowMode;
import labyrinth.Cell;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Xray implements ShowMode {
    public Cell[][] getVisibleCells(Cell[][] cells, Hero hero) {

        int height = cells.length;
        int width = cells[0].length;

        Cell[][] copy = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            copy[y][0] = Cell.WALL;
            copy[y][width - 1] = Cell.WALL;
        }

        for (int x = 0; x < width; x++) {
            copy[0][x] = Cell.WALL;
            copy[height - 1][x] = Cell.WALL;
        }

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                copy[y][x] = Cell.FOG;
            }
        }

        int y = hero.getLocation().y;
        int x = hero.getLocation().x;

        for (int j = y - 2; j <= y + 2; j++) {
            for (int i = x - 2; i <= x + 2; i++) {
                if (i < 0 || j < 0 || i > width - 1 || j > height - 1) {
                    continue;
                }
                copy[j][i] = cells[j][i];
            }
        }

        return copy;
    }

    @Override
    public String getDescription() {
        return "Hero is able to see through walls";
    }
}
