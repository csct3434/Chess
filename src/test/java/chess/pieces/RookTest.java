package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {
    Rook rook;

    @BeforeEach
    void init() {
        rook = Rook.createBlack(new Position("d4"));
    }

    @Test
    @DisplayName("룩은 직선 방향으로 이동할 수 있다")
    void linearDirectionMove() {
        for(Direction direction : Direction.linearDirection()) {
            assertTrue(rook.verifyMovePosition(rook.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("룩은 직선 방향 외에는 이동할 수 없다")
    void diagonalDirectionMove() {
        for(Direction direction : Direction.everyDirection()) {
            if(!Direction.linearDirection().contains(direction)) {
                assertFalse(rook.verifyMovePosition(rook.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
            }
        }
    }
}