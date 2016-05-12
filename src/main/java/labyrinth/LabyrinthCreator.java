package labyrinth;

import lombok.Getter;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class LabyrinthCreator {

    @Getter
    private Cell[][] cells;
    @Getter
    private List<Point> corners;

    public LabyrinthCreator(int width, int height) {

        cells = new Cell[height * 2 + 1][width * 2 + 1];

        buildCarcass();
        buildWalls();
        findEmptyRooms(1, 1);
        fillEmptyRooms();

        corners = findCorners();
    }

    private void buildCarcass() {
        int height = cells.length;
        int width = cells[0].length;

        for (int y = 0; y < height; y ++) {
            for (int x = 0; x < width; x ++) {
                if ((x == 0 || y == 0 || x == width - 1 || y == height - 1)) {
                    cells[y][x] = Cell.WALL;
                } else {
                    cells[y][x] = Cell.EMPTY;
                }
            }
        }
    }

    private void buildWalls() {
        int height = cells.length;
        int width = cells[0].length;

        for (int y = 2; y < height - 2; y += 2) {
            for (int x = 2; x < width - 2; x += 2) {
                cells[y][x] = Cell.WALL;

                boolean wallWasBuild = false;
                do {
                    int i = (int) (Math.random() * 4);
                    switch (i) {
                        case 0:
                            if (cells[y + 1][x] == Cell.EMPTY) {
                                cells[y + 1][x] = Cell.WALL;
                                wallWasBuild = true;
                            }
                            break;
                        case 1:
                            if (cells[y - 1][x] == Cell.EMPTY) {
                                cells[y - 1][x] = Cell.WALL;
                                wallWasBuild = true;
                            }
                            break;
                        case 2:
                            if (cells[y][x + 1] == Cell.EMPTY) {
                                cells[y][x + 1] = Cell.WALL;
                                wallWasBuild = true;
                            }
                            break;
                        case 3:
                            if (cells[y][x - 1] == Cell.EMPTY) {
                                cells[y][x - 1] = Cell.WALL;
                                wallWasBuild = true;
                            }
                            break;
                    }
                } while (!wallWasBuild);
            }
        }
    }

    private void findEmptyRooms(int x, int y) {
        if (cells[y][x] != Cell.EMPTY) {
            return;
        }

        cells[y][x] = Cell.FOG;

        findEmptyRooms(x + 1, y);
        findEmptyRooms(x - 1, y);
        findEmptyRooms(x, y + 1);
        findEmptyRooms(x, y - 1);
    }

    private void fillEmptyRooms() {
        int height = cells.length;
        int width = cells[0].length;

        for (int y = 1; y < height - 1; y += 1) {
            for (int x = 1; x < width - 1; x += 1) {
                if (cells[y][x] == Cell.EMPTY) {
                    cells[y][x] = Cell.WALL;
                }
                if (cells[y][x] == Cell.FOG) {
                    cells[y][x] = Cell.EMPTY;
                }
            }
        }
    }

    private List<Point> findCorners() {
        int height = cells.length;
        int width = cells[0].length;

        List<Point> corners = new LinkedList<>();

        for (int y = 1; y < height - 1; y += 2) {
            for (int x = 1; x < width - 1; x += 2) {

                if (x == 1 && y == 1) {
                    continue;
                }

                int nearestWalls = (cells[y + 1][x] == Cell.WALL ? 1 : 0)
                        + (cells[y - 1][x] == Cell.WALL ? 1 : 0)
                        + (cells[y][x + 1] == Cell.WALL ? 1 : 0)
                        + (cells[y][x - 1] == Cell.WALL ? 1 : 0);

                if (nearestWalls == 3) {
                    corners.add(new Point(x, y));
                }
            }
        }

        return corners;
    }
}
