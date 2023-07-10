package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("초기화 된 체스판은 32개의 정렬된 기물들을 가진다")
    public void create() throws Exception {
        board.initialize();

        assertEquals(32, board.countTotalPieces());

        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("체스판에 있는 특정 기물의 개수를 계산해야 한다")
    public void countSpecificPieces() {
        board.initialize();

        assertEquals(8, board.countPiecesOf(Color.WHITE, Type.PAWN));
        assertEquals(8, board.countPiecesOf(Color.BLACK, Type.PAWN));

        assertEquals(2, board.countPiecesOf(Color.WHITE, Type.ROOK));
        assertEquals(2, board.countPiecesOf(Color.BLACK, Type.ROOK));

        assertEquals(2, board.countPiecesOf(Color.WHITE, Type.KNIGHT));
        assertEquals(2, board.countPiecesOf(Color.BLACK, Type.KNIGHT));

        assertEquals(2, board.countPiecesOf(Color.WHITE, Type.BISHOP));
        assertEquals(2, board.countPiecesOf(Color.BLACK, Type.BISHOP));

        assertEquals(1, board.countPiecesOf(Color.WHITE, Type.QUEEN));
        assertEquals(1, board.countPiecesOf(Color.BLACK, Type.QUEEN));

        assertEquals(1, board.countPiecesOf(Color.WHITE, Type.KING));
        assertEquals(1, board.countPiecesOf(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회할 수 있어야 한다")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(createBlackRook(new Position("a8")), board.findPiece("a8"));
        assertEquals(createBlackRook(new Position("h8")), board.findPiece("h8"));
        assertEquals(createWhiteRook(new Position("a1")), board.findPiece("a1"));
        assertEquals(createWhiteRook(new Position("h1")), board.findPiece("h1"));
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산할 수 있어야 한다")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", createBlackPawn(new Position("b6")));
        addPiece("e6", createBlackQueen(new Position("e6")));
        addPiece("b8", createBlackKing(new Position("b8")));
        addPiece("c8", createBlackRook(new Position("c8")));

        addPiece("f2", createWhitePawn(new Position("f2")));
        addPiece("g2", createWhitePawn(new Position("g2")));
        addPiece("e1", createWhiteRook(new Position("e1")));
        addPiece("f1", createWhiteKing(new Position("f1")));

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String square, Piece piece) {
        board.addPiece(square, piece);
    }


    @Test
    @DisplayName("색상별로 남아있는 기물들을 점수의 오름차순으로 정렬한다")
    public void sortPiecesByPointAscending() {
        board.initializeEmpty();

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

        List<Piece> actualWhiteSortResult = board.sortPiecesByPointAscending(Color.WHITE);
        List<Piece> actualBlackSortResult = board.sortPiecesByPointAscending(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedWhiteSortResult.get(i), actualWhiteSortResult.get(i));
            assertEquals(expectedBlackSortResult.get(i), actualBlackSortResult.get(i));
        }
    }

    @Test
    @DisplayName("색상별로 남아있는 기물들을 점수의 내림차순으로 정렬한다")
    public void sortPiecesByPointDescending() {
        board.initializeEmpty();

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

        List<Piece> actualWhiteSortResult = board.sortPiecesByPointDescending(Color.WHITE);
        List<Piece> actualBlackSortResult = board.sortPiecesByPointDescending(Color.BLACK);

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedWhiteSortResult.get(i), actualWhiteSortResult.get(i));
            assertEquals(expectedBlackSortResult.get(i), actualBlackSortResult.get(i));
        }
    }

    @Test
    @DisplayName("기물을 특정 위치에서 다른 위치로 이동시킬 수 있어야 한다")
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(new Position(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(new Position(targetPosition)), board.findPiece(targetPosition));
    }

}