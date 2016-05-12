package utils.factories;

import characters.heroes.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HeroesFactory extends Factory<Hero> {
    @Autowired
    private HeroesFactory(Map<String, Hero> heroes) {
       super(heroes);
    }
}
