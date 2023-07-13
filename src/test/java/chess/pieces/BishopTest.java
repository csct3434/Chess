package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.PieceTestUtil.verifyMovePosition;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

    Bishop bishop = Bishop.createWhite(new Position("d4"));
    List<Direction> diagonalDirection = Direction.diagonalDirection();

    @Test
    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다")
    void diagonalDirectionMove() {
        for (Direction direction : diagonalDirection) {
            for (int distance = 1; distance < 4; distance++) {
                int xDegree = direction.getXDegree() * distance;
                int yDegree = direction.getYDegree() * distance;

                assertTrue(verifyMovePosition(bishop, xDegree, yDegree));
            }
        }
    }

    @Test
    @DisplayName("비숍은 대각선 방향 외에는 이동할 수 없다")
    void nonDiagonalDirectionMove() {
        for (Direction direction : Direction.everyDirection()) {
            if (!diagonalDirection.contains(direction)) {
                assertFalse(verifyMovePosition(bishop, direction.getXDegree(), direction.getYDegree()));
            }
        }
    }


}
