package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class KnightTest {

    Knight knight;
    List<Direction> knightDirections = Direction.knightDirection();
    List<Direction> everyDirections = Direction.everyDirection();

    @BeforeEach
    void init() {
        knight = Knight.createBlack(new Position("d4"));
    }

    @Test
    @DisplayName("나이트는 NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS 방향으로 이동할 수 있다")
    void validKnightDirectionMove() {
        for (Direction direction : knightDirections) {
            Assertions.assertTrue(verifyMovePosition(knight, direction.getXDegree(), direction.getYDegree()));
        }
    }

    @Test
    @DisplayName("나이트는 NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS 방향 외에는 이동할 수 있다")
    void invalidKnightDirectionMove() {
        for (Direction direction : everyDirections) {
            if (!knightDirections.contains(direction)) {
                Assertions.assertFalse(verifyMovePosition(knight, direction.getXDegree(), direction.getYDegree()));
            }
        }
    }

    private boolean verifyMovePosition(Piece piece, int xDegree, int yDegree) {
        Position targetPosition = Position.createWithDegreeOffset(piece.getPosition(), xDegree, yDegree);
        return piece.verifyMovePosition(targetPosition);
    }
}