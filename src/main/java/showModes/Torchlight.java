package showModes;

import characters.heroes.Hero;
import interfaces.ShowMode;
import labyrinth.Cell;
import org.springframework.stereotype.Component;

@Component
public class Torchlight implements ShowMode {
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

        copy[y][x] = cells[y][x];

        for (int i = 1; i <= 4; i++) {
            copy[y][x + i] = cells[y][x + i];
            copy[y + 1][x + i] = cells[y + 1][x + i];
            copy[y - 1][x + i] = cells[y - 1][x + i];
            if (cells[y][x + i] == Cell.WALL) {
                break;
            }
        }

        for (int i = 1; i <= 4; i++) {
            copy[y][x - i] = cells[y][x - i];
            copy[y + 1][x - i] = cells[y + 1][x - i];
            copy[y - 1][x - i] = cells[y - 1][x - i];
            if (cells[y][x - i] == Cell.WALL) {
                break;
            }
        }

        for (int i = 1; i <= 4; i++) {
            copy[y + i][x] = cells[y + i][x];
            copy[y + i][x + 1] = cells[y + i][x + 1];
            copy[y + i][x - 1] = cells[y + i][x - 1];
            if (cells[y + i][x] == Cell.WALL) {
                break;
            }
        }

        for (int i = 1; i <= 4; i++) {
            copy[y - i][x] = cells[y - i][x];
            copy[y - i][x + 1] = cells[y - i][x + 1];
            copy[y - i][x - 1] = cells[y - i][x - 1];
            if (cells[y - i][x] == Cell.WALL) {
                break;
            }
        }
        return copy;
    }

    public String getDescription() {
        return "Torchlight will show some space around the hero";
    }
}
