package utils;

import lombok.Getter;

public class GameResult {
    @Getter
    private String name;

    @Getter
    private int enemiesNum;

    @Getter
    private int treasuresNum;

    @Getter
    private int treasuresFound;

    @Getter
    private int width;

    @Getter
    private int height;

    @Getter
    private String world;

    @Getter
    private String hero;

    @Getter
    private String mode;

    private GameResult() {
    }

    private GameResult(Builder builder) {
        enemiesNum = builder.enemiesNum;
        treasuresNum = builder.treasuresNum;
        treasuresFound = builder.treasuresFound;
        width = builder.width;
        height = builder.height;
        world = builder.world;
        hero = builder.hero;
        mode = builder.mode;
        name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int enemiesNum;
        private int treasuresNum;
        private int treasuresFound;
        private int width;
        private int height;
        private String world;
        private String hero;
        private String mode;
        private String name;

        private Builder() {
        }

        public Builder enemiesNum(int val) {
            enemiesNum = val;
            return this;
        }

        public Builder treasuresNum(int val) {
            treasuresNum = val;
            return this;
        }

        public Builder treasuresFound(int val) {
            treasuresFound = val;
            return this;
        }

        public Builder width(int val) {
            width = val;
            return this;
        }

        public Builder height(int val) {
            height = val;
            return this;
        }

        public Builder world(String val) {
            world = val;
            return this;
        }

        public Builder hero(String val) {
            hero = val;
            return this;
        }

        public Builder mode(String val) {
            mode = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public GameResult build() {
            return new GameResult(this);
        }
    }
}
