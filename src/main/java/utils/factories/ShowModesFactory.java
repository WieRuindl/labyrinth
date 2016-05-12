package utils.factories;

import interfaces.ShowMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShowModesFactory extends Factory<ShowMode> {
    @Autowired
    public ShowModesFactory(Map<String, ShowMode> modes) {
        super(modes);
    }
}
