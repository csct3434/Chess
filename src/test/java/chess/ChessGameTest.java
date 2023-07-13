package chess;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(new Board());
        chessGame.initializeEmptyBoard();
    }

    @Test
    @DisplayName("기물을 다른 위치로 이동시킬 수 있어야 한다")
    void move() {
        chessGame.initializeBoard();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.move(sourcePosition, targetPosition);

        assertEquals(Blank.create(new Position(sourcePosition)), chessGame.findPiece(new Position(sourcePosition)));
        assertEquals(Pawn.createWhite(new Position(targetPosition)), chessGame.findPiece(new Position(targetPosition)));
    }

    @Test
    @DisplayName("현재 게임의 점수를 계산할 수 있어야 한다")
    void calculatePoint() {
        addPiece("b6", Pawn.createBlack(new Position("b6")));
        addPiece("e6", Queen.createBlack(new Position("e6")));
        addPiece("b8", King.createBlack(new Position("b8")));
        addPiece("c8", Rook.createBlack(new Position("c8")));

        addPiece("f2", Pawn.createWhite(new Position("f2")));
        addPiece("g2", Pawn.createWhite(new Position("g2")));
        addPiece("e1", Rook.createWhite(new Position("e1")));
        addPiece("f1", King.createWhite(new Position("f1")));

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
    }


    @Test
    @DisplayName("체스판에 있는 기물들을 점수의 오름차순으로 정렬할 수 있어야 한다")
    void sortPiecesByPointAscending() {
        addPiece("b6", Pawn.createBlack(new Position("b6")));
        addPiece("e6", Queen.createBlack(new Position("e6")));
        addPiece("b8", King.createBlack(new Position("b8")));
        addPiece("c8", Rook.createBlack(new Position("c8")));

        addPiece("f2", Pawn.createWhite(new Position("f2")));
        addPiece("g2", Pawn.createWhite(new Position("g2")));
        addPiece("e1", Rook.createWhite(new Position("e1")));
        addPiece("f1", King.createWhite(new Position("f1")));

        List<Piece> expectedWhiteSortResult = List.of(
                King.createWhite(new Position("f1")), Pawn.createWhite(new Position("f2")),
                Pawn.createWhite(new Position("g2")), Rook.createWhite(new Position("e1")));

        List<Piece> expectedBlackSortResult = List.of(
                King.createBlack(new Position("b8")), Pawn.createBlack(new Position("b6")),
                Rook.createBlack(new Position("c8")), Queen.createBlack(new Position("e6")));

        List<Piece> actualWhiteSortResult = chessGame.sortPiecesByPointAscending(Color.WHITE);
        List<Piece> actualBlackSortResult = chessGame.sortPiecesByPointAscending(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedWhiteSortResult.get(i), actualWhiteSortResult.get(i));
            assertEquals(expectedBlackSortResult.get(i), actualBlackSortResult.get(i));
        }
    }

    @Test
    @DisplayName("체스판에 있는 기물들을 점수의 내림차순으로 정렬할 수 있어야 한다")
    void sortPiecesByPointDescending() {
        addPiece("b6", Pawn.createBlack(new Position("b6")));
        addPiece("e6", Queen.createBlack(new Position("e6")));
        addPiece("b8", King.createBlack(new Position("b8")));
        addPiece("c8", Rook.createBlack(new Position("c8")));

        addPiece("f2", Pawn.createWhite(new Position("f2")));
        addPiece("g2", Pawn.createWhite(new Position("g2")));
        addPiece("e1", Rook.createWhite(new Position("e1")));
        addPiece("f1", King.createWhite(new Position("f1")));

        List<Piece> expectedWhiteSortResult = List.of(
                Rook.createWhite(new Position("e1")), Pawn.createWhite(new Position("g2")),
                Pawn.createWhite(new Position("f2")), King.createWhite(new Position("f1")));

        List<Piece> expectedBlackSortResult = List.of(
                Queen.createBlack(new Position("e6")), Rook.createBlack(new Position("c8")),
                Pawn.createBlack(new Position("b6")), King.createBlack(new Position("b8")));

        List<Piece> actualWhiteSortResult = chessGame.sortPiecesByPointDescending(Color.WHITE);
        List<Piece> actualBlackSortResult = chessGame.sortPiecesByPointDescending(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedWhiteSortResult.get(i), actualWhiteSortResult.get(i));
            assertEquals(expectedBlackSortResult.get(i), actualBlackSortResult.get(i));
        }
    }

    @Test
    @DisplayName("기물을 동일한 위치로 이동시키면 런타임 예외가 발생한다")
    void moveToSamePosition() {
        addPiece("d4", Pawn.createBlack(new Position("d4")));
        assertThrows(RuntimeException.class, () -> chessGame.move("d4", "d4"));
    }

    @Test
    @DisplayName("이동을 마친 뒤 진행한 턴의 횟수가 1 증가해야 한다")
    void turnCountTest() {
        int beforeTurnCount = chessGame.getTurnCount();

        addPiece("d4", Pawn.createWhite(new Position("d4")));
        chessGame.move("d4", "d5");

        int afterTurnCount = chessGame.getTurnCount();

        assertEquals(beforeTurnCount + 1, afterTurnCount);
    }

    @Test
    @DisplayName("이동 위치에 같은 색 기물이 위치하면 이동할 수 없다")
    void attackSameColorPieceTest() {
        addPiece("d4", Pawn.createWhite(new Position("d4")));
        addPiece("e5", Pawn.createWhite(new Position("e5")));
        assertThrows(RuntimeException.class, () -> chessGame.move("d4", "e5"));
    }

    @Test
    @DisplayName("빈 기물을 이동시키는 것은 불가능하다")
    void moveEmptyPieceTest() {
        addPiece("a1", Blank.create(new Position("a1")));
        assertThrows(RuntimeException.class, () -> chessGame.move("a1", "a2"));
    }

    @Test
    @DisplayName("상대편 기물을 이동시키는 것은 불가능하다")
    void moveEnemyPieceTest () {
        addPiece("b2", Pawn.createWhite(new Position("b2")));
        addPiece("b7", Pawn.createBlack(new Position("b7")));

        assertThrows(RuntimeException.class, () -> chessGame.move("b7", "b5"));
        chessGame.move("b2", "b4");

        assertThrows(RuntimeException.class, () -> chessGame.move("b4", "b5"));
        chessGame.move("b7", "b5");
    }

    @Test
    @DisplayName("기물을 이동하여 킹을 잡으면 확인할 수 있다")
    void detectKingDeadTest() {
        addPiece("b2", Pawn.createWhite(new Position("b2")));
        addPiece("b7", Pawn.createBlack(new Position("b7")));
        addPiece("c5", King.createBlack(new Position("c5")));

        chessGame.move("b2", "b3");
        assertFalse(chessGame.isKingDead());

        chessGame.move("c5", "c4");
        assertFalse(chessGame.isKingDead());

        chessGame.move("b3", "c4");
        assertTrue(chessGame.isKingDead());

        assertTrue(chessGame.getWinner().equals("백"));
    }

    @Test
    @DisplayName("폰의 전진 위치에 상대편 기물이 존재하면 이동이 불가하다")
    void pawnLinearMoveTest() {
        addPiece("b2", Pawn.createWhite(new Position("b2")));
        addPiece("b3", Pawn.createBlack(new Position("b3")));

        addPiece("b5", Pawn.createWhite(new Position("b5")));
        addPiece("b7", Pawn.createBlack(new Position("b7")));

        assertThrows(RuntimeException.class, () -> chessGame.move("b2", "b3"));
        assertThrows(RuntimeException.class, () -> chessGame.move("b7", "b5"));
    }

    @Test
    @DisplayName("폰의 대각선 위치에 상대편 기물이 존재하면 이동이 가능하다")
    void pawnDiagonalMoveTest() {
        addPiece("b2", Pawn.createWhite(new Position("b2")));
        addPiece("c3", Pawn.createBlack(new Position("c3")));

        chessGame.move("b2", "c3");
        assertEquals(Pawn.createWhite(new Position("c3")), chessGame.findPiece(new Position("c3")));
    }

    @Test
    @DisplayName("룩의 이동 경로에 다른 기물이 존재하면 이동할 수 없다")
    void rookMoveConflictTest() {
        addPiece("a1", Rook.createWhite(new Position("a1")));
        addPiece("c1", Pawn.createWhite(new Position("c1")));
        addPiece("h1", Pawn.createBlack(new Position("h1")));

        assertThrows(RuntimeException.class, () -> chessGame.move("a1", "h1"));
    }

    @Test
    @DisplayName("비숍의 이동 경로에 다른 기물이 존재하면 이동할 수 없다")
    void bishopMoveConflictTest() {
        addPiece("a1", Bishop.createWhite(new Position("a1")));
        addPiece("c3", Pawn.createWhite(new Position("c3")));
        addPiece("e5", Pawn.createBlack(new Position("e5")));

        assertThrows(RuntimeException.class, () -> chessGame.move("a1", "e5"));
    }

    @Test
    @DisplayName("퀸의 이동 경로에 다른 기물이 존재하면 이동할 수 없다")
    void queenMoveConflictTest() {
        addPiece("a1", Queen.createWhite(new Position("a1")));
        addPiece("c1", Pawn.createWhite(new Position("c3")));
        addPiece("h1", Pawn.createBlack(new Position("h1")));
        addPiece("c3", Pawn.createWhite(new Position("c3")));
        addPiece("e5", Pawn.createBlack(new Position("e5")));

        assertThrows(RuntimeException.class, () -> chessGame.move("a1", "h1"));
        assertThrows(RuntimeException.class, () -> chessGame.move("a1", "e5"));
    }

    @Test
    @DisplayName("나이트는 이동 경로에 있는 다른 기물들을 뛰어넘을 수 있다")
    void knightMoveConflictTest() {
        addPiece("b1", Knight.createWhite(new Position("b1")));
        addPiece("b2", Pawn.createWhite(new Position("b2")));
        addPiece("c3", Pawn.createBlack(new Position("c3")));

        chessGame.move("b1", "c3");

        assertEquals(Knight.createWhite(new Position("c3")), chessGame.findPiece(new Position("c3")));
    }


    private void addPiece(String square, Piece piece) {
        chessGame.addPiece(new Position(square), piece);
    }
}
