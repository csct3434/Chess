package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.utils.PieceTestUtil.verifyMovePosition;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    @Test
    @DisplayName("퀸은 모든 방향으로 이동할 수 있다")
    void test() {
        Queen queen = Queen.createWhite(new Position("d4"));
        List<Direction> everyDirections = Direction.everyDirection();

        for (int distance = 1; distance < 4; distance++) {
            for (Direction direction : everyDirections) {
                int xDegree = direction.getXDegree() * distance;
                int yDegree = direction.getYDegree() * distance;
                assertTrue(verifyMovePosition(queen, xDegree, yDegree));
            }
        }
    }

}