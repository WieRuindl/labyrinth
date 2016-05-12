package worlds;

import characters.enemies.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Castle extends World {
    @Autowired
    public Castle(@Qualifier(value = "castle") List<Enemy> enemies) {
        super(enemies);
    }

    public String getDescription() {
        return "Old castle destroyed by time lost it's grandeur. Only the ghosts of the dead kings aimlessly roam the castle";
    }
}
