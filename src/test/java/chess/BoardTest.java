package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board;

    @BeforeEach
    public void init() {
        board = new Board();
    }

    @Test
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }
    
    @Test
    public void print() throws Exception {
        board.initialize();
        System.out.println(board.print());
    }
}