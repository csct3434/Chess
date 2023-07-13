package chess.pieces;

import chess.board.Board;
import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.utils.PieceTestUtil.verifyMovePosition;
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

    @Test
    @DisplayName("폰은 두 칸 이상 이동할 수 없다")
    void multipleMove() {
        for(int distance = 2; distance < 4; distance++) {
            Pawn pawn = Pawn.createWhite(new Position("d1"));
            for (Direction direction : whitePawnDirections) {
                assertFalse(verifyMovePosition(pawn, distance, distance));
            }
        }
    }

    @Test
    @DisplayName("폰은 초기 위치에서 앙 파상을 할 수 있다")
    void verifyEnPassant1() {
        int initialWhitePawnYPosition = 1;
        int initialBlackPawnYPosition = 6;

        for(int xPos = 0; xPos < Board.LENGTH; xPos++) {
            Pawn whitePawn = Pawn.createWhite(new Position(xPos, initialWhitePawnYPosition));
            Pawn blackPawn = Pawn.createBlack(new Position(xPos, initialBlackPawnYPosition));

            assertTrue(verifyMovePosition(whitePawn, 0, 2));
            assertTrue(verifyMovePosition(blackPawn, 0, -2));
        }
    }

    @Test
    @DisplayName("폰은 초기 위치를 벗어난 곳에서 앙 파상을 할 수 없다")
    void verifyEnPassant2() {
        int nonInitialWhitePawnYPosition = 3;
        int nonInitialBlackPawnYPosition = 4;

        for(int xPos = 0; xPos < Board.LENGTH; xPos++) {
            Pawn whitePawn = Pawn.createWhite(new Position(xPos, nonInitialWhitePawnYPosition));
            Pawn blackPawn = Pawn.createBlack(new Position(xPos, nonInitialBlackPawnYPosition));

            assertFalse(verifyMovePosition(whitePawn, 0, 2));
            assertFalse(verifyMovePosition(blackPawn, 0, -2));
        }
    }

}
