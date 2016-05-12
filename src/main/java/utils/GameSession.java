package utils;

import characters.heroes.Hero;
import utils.factories.HeroesFactory;
import interfaces.ShowMode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.factories.ShowModesFactory;
import worlds.World;
import utils.factories.WorldsFactory;

@Component
public class GameSession {

    @Getter
    @Setter
    private int enemiesNum;

    @Getter
    @Setter
    private int treasuresNum;

    @Getter
    @Setter
    private int width;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    private World world;

    @Getter
    @Setter
    private Hero hero;

    @Getter
    @Setter
    private ShowMode mode;

    @Autowired
    private GameSession(HeroesFactory heroes,
                        WorldsFactory worlds,
                        ShowModesFactory modes,

                        @Value(value = "${hero}") String hero,
                        @Value(value = "${world}") String world,
                        @Value(value = "${showMode}") String showMode,
                        @Value(value = "${labyrinth.width}") int width,
                        @Value(value = "${labyrinth.height}") int height,
                        @Value(value = "${labyrinth.enemiesNum}") int enemiesNum,
                        @Value(value = "${labyrinth.treasuresNum}") int treasuresNum) {

        this.hero = heroes.get(hero);
        this.world = worlds.get(world);
        this.mode = modes.get(showMode);
        this.width = width;
        this.height = height;
        this.enemiesNum = enemiesNum;
        this.treasuresNum = treasuresNum;
    }
}
