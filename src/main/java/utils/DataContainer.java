package utils;

import characters.heroes.Hero;
import interfaces.ShowMode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import worlds.World;

import java.util.List;

@Component
public class DataContainer {
    @Autowired
    @Getter
    private List<Hero> heroes;

    @Autowired
    @Getter
    private List<ShowMode> showModes;

    @Autowired
    @Getter
    private List<World> worlds;
}
