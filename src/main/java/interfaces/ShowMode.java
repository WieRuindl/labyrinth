package interfaces;

import characters.heroes.Hero;
import labyrinth.Cell;

public interface ShowMode extends Description {
    Cell[][] getVisibleCells(Cell[][] cells, Hero hero);
}
