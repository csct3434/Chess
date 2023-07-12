package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    @Test
    @DisplayName("킹은 모든 방향으로 이동할 수 있다")
    void everyDirectionMove() {
        King king = King.createBlack(new Position("d4"));
        List<Direction> directions = Direction.everyDirection();

        for(Direction direction : directions) {
            assertTrue(king.verifyMovePosition(king.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("킹은 두 칸을 이동할 수 없다")
    void twoSquareMove() {
        King king = King.createBlack(new Position("d4"));
        List<Direction> directions = Direction.everyDirection();

        for(Direction direction : directions) {
            assertFalse(king.verifyMovePosition(king.getPosition().getMovedPosition(direction.getXDegree() * 2, direction.getYDegree() * 2)));
        }
    }
}
