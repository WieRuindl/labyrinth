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
public class Pyramid extends World {
    @Autowired
    public Pyramid(@Qualifier(value = "pyramid") List<Enemy> enemies) {
        super(enemies);
    }

    public String getDescription() {
        return "Ancient place full with secrets. Be careful - pharaohs of the past don't like somebody to steel their treasures";
    }
}
