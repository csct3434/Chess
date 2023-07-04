package chess;

import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board;

    @BeforeEach
    public void init() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판에서의 폰 추가 및 추가된 기물의 조회가 가능해야 한다")
    public void create() throws Exception {
        for(int i=0; i < Pawn.colors.length; i++) {
            Pawn newPawn = new Pawn(Pawn.colors[i]);
            board.add(newPawn);
            assertEquals(i+1, board.size());
            assertEquals(newPawn, board.findPawn(i));
        }
    }
}