package showModes;

import characters.heroes.Hero;
import interfaces.ShowMode;
import labyrinth.Cell;
import org.springframework.stereotype.Component;

@Component
public class Classic implements ShowMode {
    public Cell[][] getVisibleCells(Cell[][] cells, Hero hero) {
        return cells;
    }

    public String getDescription() {
        return "All labyrinth will be shown";
    }
}
