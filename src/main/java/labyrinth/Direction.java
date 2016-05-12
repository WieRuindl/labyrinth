package labyrinth;

import lombok.Getter;

import java.util.Arrays;

public enum Direction {
    NORTH(1, 0), WEST(0, -1), SOUTH(-1, 0), EAST(0, 1);

    @Getter
    private final int dy;
    @Getter
    private final int dx;

    Direction(int dy, int dx) {
        this.dy = dy;
        this.dx = dx;
    }

    public Direction revert() {
        return Arrays.asList(Direction.values()).stream()
                .filter(d -> d.dx == -dx && d.dy == -dy)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
