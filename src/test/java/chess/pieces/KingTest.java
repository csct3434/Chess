package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.PieceTestUtil.verifyMovePosition;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    King king = King.createBlack(new Position("d4"));
    List<Direction> everyDirections = Direction.everyDirection();

    @Test
    @DisplayName("킹은 모든 방향으로 이동할 수 있다")
    void everyDirectionMove() {
        for (Direction direction : everyDirections) {
            assertTrue(verifyMovePosition(king, direction.getXDegree(), direction.getYDegree()));
        }
    }

    @Test
    @DisplayName("킹은 두 칸 이상 이동할 수 없다")
    void multipleMove() {
        for (int distance = 2; distance < 4; distance++) {
            for (Direction direction : everyDirections) {
                int xDegree = direction.getXDegree() * distance;
                int yDegree = direction.getYDegree() * distance;
                assertFalse(verifyMovePosition(king, xDegree, yDegree));
            }
        }
    }

}
