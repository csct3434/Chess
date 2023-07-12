package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("흰색 폰은 N, NE, NW 방향으로 이동할 수 있다")
    void validWhitePawnDirectionMove() {
        Pawn whitePawn = Pawn.createWhite(new Position("d4"));

        for(Direction direction : Direction.whitePawnDirection()) {
            Assertions.assertTrue(whitePawn.verifyMovePosition(whitePawn.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("흰색 폰은 N, NE, NW 방향 외에 이동할 수 없다")
    void invalidWhitePawnDirectionMove() {
        Pawn whitePawn = Pawn.createWhite(new Position("d4"));

        for(Direction direction : Direction.everyDirection()) {
            if(!Direction.whitePawnDirection().contains(direction)) {
                Assertions.assertFalse(whitePawn.verifyMovePosition(whitePawn.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
            }
        }
    }

    @Test
    @DisplayName("검정색 폰은 S, SE, SW 방향으로 이동할 수 있다")
    void validBlackPawnDirectionMove () {
        Pawn blackPawn = Pawn.createBlack(new Position("d4"));

        for(Direction direction : Direction.blackPawnDirection()) {
            Assertions.assertTrue(blackPawn.verifyMovePosition(blackPawn.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
        }
    }

    @Test
    @DisplayName("검정색 폰은 S, SE, SW 방향 외에는 이동할 수 없다")
    void invalidBlackPawnDirectionMove() {
        Pawn blackPawn = Pawn.createBlack(new Position("d4"));

        for(Direction direction : Direction.everyDirection()) {
            if(!Direction.blackPawnDirection().contains(direction)) {
                Assertions.assertFalse(blackPawn.verifyMovePosition(blackPawn.getPosition().getMovedPosition(direction.getXDegree(), direction.getYDegree())));
            }
        }
    }

}
