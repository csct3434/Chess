package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.Piece.*;
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

        assertEquals(Piece.createBlank(new Position(sourcePosition)), chessGame.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(new Position(targetPosition)), chessGame.findPiece(targetPosition));
    }

    @Test
    @DisplayName("현재 게임의 점수를 계산할 수 있어야 한다")
    void calculatePoint() throws Exception {
        addPiece("b6", createBlackPawn(new Position("b6")));
        addPiece("e6", createBlackQueen(new Position("e6")));
        addPiece("b8", createBlackKing(new Position("b8")));
        addPiece("c8", createBlackRook(new Position("c8")));

        addPiece("f2", createWhitePawn(new Position("f2")));
        addPiece("g2", createWhitePawn(new Position("g2")));
        addPiece("e1", createWhiteRook(new Position("e1")));
        addPiece("f1", createWhiteKing(new Position("f1")));

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);
    }

    private void addPiece(String square, Piece piece) {
        chessGame.addPiece(square, piece);
    }


    @Test
    @DisplayName("체스판에 있는 기물들을 점수의 오름차순으로 정렬할 수 있어야 한다")
    void sortPiecesByPointAscending() {
        addPiece("f2", createWhitePawn(new Position("f2")));
        addPiece("g2", createWhitePawn(new Position("g2")));
        addPiece("e1", createWhiteRook(new Position("e1")));
        addPiece("f1", createWhiteKing(new Position("f1")));

        addPiece("b6", createBlackPawn(new Position("b6")));
        addPiece("e6", createBlackQueen(new Position("e6")));
        addPiece("b8", createBlackKing(new Position("b8")));
        addPiece("c8", createBlackRook(new Position("c8")));

        List<Piece> expectedWhiteSortResult = List.of(
                createWhiteKing(new Position("f1")), createWhitePawn(new Position("f2")),
                createWhitePawn(new Position("g2")), createWhiteRook(new Position("e1")));

        List<Piece> expectedBlackSortResult = List.of(
                createBlackKing(new Position("b8")), createBlackPawn(new Position("b6")),
                createBlackRook(new Position("c8")), createBlackQueen(new Position("e6")));

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
        addPiece("f2", createWhitePawn(new Position("f2")));
        addPiece("g2", createWhitePawn(new Position("g2")));
        addPiece("e1", createWhiteRook(new Position("e1")));
        addPiece("f1", createWhiteKing(new Position("f1")));

        addPiece("b6", createBlackPawn(new Position("b6")));
        addPiece("e6", createBlackQueen(new Position("e6")));
        addPiece("b8", createBlackKing(new Position("b8")));
        addPiece("c8", createBlackRook(new Position("c8")));

        List<Piece> expectedWhiteSortResult = List.of(
                createWhiteRook(new Position("e1")), createWhitePawn(new Position("g2")),
                createWhitePawn(new Position("f2")), createWhiteKing(new Position("f1")));

        List<Piece> expectedBlackSortResult = List.of(
                createBlackQueen(new Position("e6")), createBlackRook(new Position("c8")),
                createBlackPawn(new Position("b6")), createBlackKing(new Position("b8")));

        List<Piece> actualWhiteSortResult = chessGame.sortPiecesByPointDescending(Color.WHITE);
        List<Piece> actualBlackSortResult = chessGame.sortPiecesByPointDescending(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedWhiteSortResult.get(i), actualWhiteSortResult.get(i));
            assertEquals(expectedBlackSortResult.get(i), actualBlackSortResult.get(i));
        }
    }

}