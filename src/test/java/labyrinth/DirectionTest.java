package labyrinth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void testRevert() throws Exception {
        assertEquals(Direction.EAST, Direction.WEST.revert());
        assertEquals(Direction.WEST, Direction.EAST.revert());
        assertEquals(Direction.NORTH, Direction.SOUTH.revert());
        assertEquals(Direction.SOUTH, Direction.NORTH.revert());
    }

    @Test
    public void testDirections() throws Exception {
        assertEquals(4, Direction.values().length);
    }
}