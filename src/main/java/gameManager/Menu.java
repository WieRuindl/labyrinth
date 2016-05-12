package gameManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Menu extends StateBasedGame {

    private static final String NAME = "Labyrinth";
    private static final int PLAY = 0;

    @Autowired
    public Menu(Game game) throws SlickException {
        super(NAME);
        this.addState(game);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(PLAY).init(gameContainer, this);
        this.enterState(PLAY);
    }
}
