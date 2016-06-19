package gameManager;

import lombok.Getter;
import org.newdawn.slick.Input;

public enum Action {
    MOVE_NORTH(Input.KEY_DOWN),
    MOVE_SOUTH(Input.KEY_UP),
    MOVE_WEST(Input.KEY_LEFT),
    MOVE_EAST(Input.KEY_RIGHT),
    SPECIAL_ACTION(Input.KEY_C);

    @Getter
    private final int key;

    Action(int key) {
        this.key = key;
    }
}
