package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    Bishop bishop;

    @BeforeEach
    void init() {
        bishop = Bishop.createWhite(new Position("d4"));
    }

    @Test
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다")
    void diagonalDirectionMove() {
        for(Direction direction : Direction.diagonalDirection()) {
            assertTrue(bishop.verifyMovePosition(bishop.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("비숍은 대각선 방향 외에는 이동할 수 없다")
    void notDiagonalDirectionMove() {
        for(Direction direction : Direction.everyDirection()) {
            if(!Direction.diagonalDirection().contains(direction)) {
                assertFalse(bishop.verifyMovePosition(bishop.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
            }
        }
    }
}
