package characters;

import labyrinth.Cell;
import labyrinth.Direction;
import lombok.Getter;
import lombok.Setter;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Character {
    @Getter
    protected Animation animation;

    @Setter
    protected Cell[][] cells;

    @Setter
    protected Point location;

    //because of error in Memory ShowMode
    public Point getLocation() {
        return new Point(location);
    }

    public void initAnimation() throws SlickException {
        animation = loadAnimation(getSource());
    }

    protected Animation loadAnimation(String path) throws SlickException {
        File root;

        try {
            root = new ClassPathResource(path).getFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't open classpath resource: " + getSource());
        }

        File[] files = root.listFiles();
        if (files == null || files.length == 0) {
            throw new RuntimeException("Directory does not exist or empty: " + root.getPath());
        }
        Image[] images = new Image[files.length];
        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().endsWith(".png")) {
                throw new RuntimeException("Found not *.png file: " + files[i].getPath());
            }
            images[i] = new Image(files[i].getPath());
        }
        return new Animation(images, getAnimationDuration());
    }

    protected Map<Direction, Cell> getNearestCells(Cell[][] cells, Point location) {
        Map<Direction, Cell> nearestCells = new HashMap<>();

        nearestCells.put(Direction.NORTH, cells[location.y + Direction.NORTH.getDy()][location.x + Direction.NORTH.getDx()]);
        nearestCells.put(Direction.SOUTH, cells[location.y + Direction.SOUTH.getDy()][location.x + Direction.SOUTH.getDx()]);
        nearestCells.put(Direction.WEST, cells[location.y + Direction.WEST.getDy()][location.x + Direction.WEST.getDx()]);
        nearestCells.put(Direction.EAST, cells[location.y + Direction.EAST.getDy()][location.x + Direction.EAST.getDx()]);

        return nearestCells;
    }

    protected abstract String getSource();

    protected abstract int getAnimationDuration();
}
