package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class BlankTest {
    @Test
    @DisplayName("빈 기물은 어떤 방향으로도 이동할 수 없다")
    void test() {
        Blank blank = Blank.create(new Position("d4"));
        for(Direction direction : Direction.everyDirection()) {
            assertFalse(blank.verifyMovePosition(blank.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }
}
