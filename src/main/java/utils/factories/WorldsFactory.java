package utils.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import worlds.World;

import java.util.Map;

@Component
public class WorldsFactory extends Factory<World> {
    @Autowired
    public WorldsFactory(Map<String, World> worlds) {
        super(worlds);
    }
}
