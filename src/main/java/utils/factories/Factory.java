package utils.factories;

import java.util.Map;

public class Factory<T> {
    private Map<String, T> map;

    protected Factory(Map<String, T> map) {
        this.map = map;
    }

    public T get(String name) {
        return map.get(name);
    }
}
