package characters.enemies.pyramid;

import characters.enemies.CommonEnemy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Qualifier("pyramid")
public class Scorpion extends CommonEnemy {

    public double getMovementDelay() {
        return 1000;
    }

    protected int getAnimationDuration() {
        return 1000;
    }
}
