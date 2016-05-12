package utils;

public class GameResultsAdapter {
    public static GameResult getResult(GameSession session, String name) {
        GameResult.Builder builder = GameResult.newBuilder();
        builder
                .name(name)
                .width(session.getWidth())
                .height(session.getHeight())
                .enemiesNum(session.getEnemiesNum())
                .treasuresNum(session.getTreasuresNum())
                .treasuresFound(session.getHero().getTreasures())
                .hero(session.getHero().getClass().getSimpleName().toLowerCase())
                .mode(session.getMode().getClass().getSimpleName().toLowerCase())
                .world(session.getWorld().getClass().getSimpleName().toLowerCase());
        return builder.build();
    }
}
