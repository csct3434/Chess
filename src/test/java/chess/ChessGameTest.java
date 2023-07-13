package chess;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame(new Board());
        chessGame.initializeEmptyBoard();
    }

    @Test
    @DisplayName("기물을 다른 위치로 이동시킬 수 있어야 한다")
    void move() throws Exception {
        chessGame.initializeBoard();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.move(sourcePosition, targetPosition);

        assertEquals(Blank.create(new Position(sourcePosition)), chessGame.findPiece(new Position(sourcePosition)));
        assertEquals(Pawn.createWhite(new Position(targetPosition)), chessGame.findPiece(new Position(targetPosition)));
    }

    @Test
    @DisplayName("현재 게임의 점수를 계산할 수 있어야 한다")
    void calculatePoint() throws Exception {
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

    private void addPiece(String square, Piece piece) {
        chessGame.addPiece(new Position(square), piece);
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

}
