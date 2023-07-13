package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.PieceTestUtil.verifyMovePosition;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    Rook rook;
    List<Direction> linearDirections = Direction.linearDirection();

    @BeforeEach
    void init() {
        rook = Rook.createBlack(new Position("d4"));
    }

    @Test
    @DisplayName("룩은 직선 방향으로 이동할 수 있다")
    void linearDirectionMove() {
        for (Direction direction : linearDirections) {
            assertTrue(verifyMovePosition(rook, direction.getXDegree(), direction.getYDegree()));
        }
    }

    @Test
    @DisplayName("룩은 직선 방향 외에는 이동할 수 없다")
    void diagonalDirectionMove() {
        for (Direction direction : Direction.everyDirection()) {
            if (!linearDirections.contains(direction)) {
                assertFalse(verifyMovePosition(rook, direction.getXDegree(), direction.getYDegree()));
            }
        }
    }
}