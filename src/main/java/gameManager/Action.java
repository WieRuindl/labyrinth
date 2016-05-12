package gameManager;

import labyrinth.Direction;
import org.newdawn.slick.Input;

public enum Action {
    MOVE_NORTH(Input.KEY_UP, Direction.NORTH),
    MOVE_SOUTH(Input.KEY_DOWN, Direction.SOUTH),
    MOVE_WEST(Input.KEY_LEFT, Direction.WEST),
    MOVE_EAST(Input.KEY_RIGHT, Direction.EAST),
    SPECIAL_ACTION(Input.KEY_C, null);

    private final int key;
    private final Direction direction;

    Action(int key, Direction direction) {
        this.key = key;
        this.direction = direction;
    }
}
