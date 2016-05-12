package characters.enemies.castle;

import characters.enemies.CommonEnemy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("castle")
public class Bat extends CommonEnemy {

    public double getMovementDelay() {
        return 750;
    }

    protected int getAnimationDuration() {
        return 300;
    }
}
