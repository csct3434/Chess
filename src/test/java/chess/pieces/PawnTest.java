package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    Pawn whitePawn = Pawn.createWhite(new Position("d4"));
    Pawn blackPawn = Pawn.createBlack(new Position("d4"));

    List<Direction> whitePawnDirections = Direction.whitePawnDirection();
    List<Direction> blackPawnDirections = Direction.blackPawnDirection();
    List<Direction> everyDirections = Direction.everyDirection();


    @Test
    @DisplayName("흰색 폰은 N, NE, NW 방향으로 이동할 수 있다")
    void validWhitePawnDirectionMove() {
        for (Direction direction : whitePawnDirections) {
            assertTrue(verifyMovePosition(whitePawn, direction.getXDegree(), direction.getYDegree()));
        }
    }

    @Test
    @DisplayName("흰색 폰은 N, NE, NW 방향 외에 이동할 수 없다")
    void invalidWhitePawnDirectionMove() {
        for (Direction direction : everyDirections) {
            if (!whitePawnDirections.contains(direction)) {
                assertFalse(verifyMovePosition(whitePawn, direction.getXDegree(), direction.getYDegree()));
            }
        }
    }

    @Test
    @DisplayName("검정색 폰은 S, SE, SW 방향으로 이동할 수 있다")
    void validBlackPawnDirectionMove() {
        for (Direction direction : blackPawnDirections) {
            assertTrue(verifyMovePosition(blackPawn, direction.getXDegree(), direction.getYDegree()));
        }
    }

    @Test
    @DisplayName("검정색 폰은 S, SE, SW 방향 외에는 이동할 수 없다")
    void invalidBlackPawnDirectionMove() {
        for (Direction direction : everyDirections) {
            if (!blackPawnDirections.contains(direction)) {
                assertFalse(verifyMovePosition(blackPawn, direction.getXDegree(), direction.getYDegree()));
            }
        }
    }

    private boolean verifyMovePosition(Piece piece, int xDegree, int yDegree) {
        Position targetPosition = Position.createWithDegreeOffset(piece.getPosition(), xDegree, yDegree);
        return piece.verifyMovePosition(targetPosition);
    }

}
