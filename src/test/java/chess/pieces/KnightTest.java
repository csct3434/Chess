package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class KnightTest {

    Knight knight;

    @BeforeEach
    void init() {
        knight = Knight.createBlack(new Position("d4"));
    }

    @Test
    @DisplayName("나이트는 NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS 방향으로 이동할 수 있다")
    void validKnightDirectionMove() {
        for(Direction direction : Direction.knightDirection()) {
            Assertions.assertTrue(knight.verifyMovePosition(knight.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("나이트는 NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS 방향 외에는 이동할 수 있다")
    void invalidKnightDirectionMove() {
        for(Direction direction : Direction.everyDirection()) {
            if(!Direction.knightDirection().contains(direction)) {
                Assertions.assertFalse(knight.verifyMovePosition(knight.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
            }
        }
    }

}