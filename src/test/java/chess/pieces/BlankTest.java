package chess.pieces;

import chess.board.Board;
import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.pieces.PieceTestUtil.verifyMovePosition;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BlankTest {
    @Test
    @DisplayName("빈 기물은 체스판의 어떤 위치로도 이동할 수 없다")
    void test() {
        Blank blank = Blank.create(new Position("a1"));

        for (int xDegree = 0; xDegree < Board.LENGTH; xDegree++) {
            for (int yDegree = 0; yDegree < Board.LENGTH; yDegree++) {
                assertFalse(verifyMovePosition(blank, xDegree, yDegree));
            }
        }
    }
}
