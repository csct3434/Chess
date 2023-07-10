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

        assertEquals(createBlackRook(), board.findPiece("a8"));
        assertEquals(createBlackRook(), board.findPiece("h8"));
        assertEquals(createWhiteRook(), board.findPiece("a1"));
        assertEquals(createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판의 특정 위치로 추가할 수 있어야 한다")
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 남아있는 기물에 따라 점수를 계산할 수 있어야 한다")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }


    @Test
    @DisplayName("색상별로 남아있는 기물들을 점수의 오름차순으로 정렬한다")
    public void sortPiecesByPointAscending() {
        board.initializeEmpty();

        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        List<Piece> expectedWhiteSortResult = List.of(createWhiteKing(), createWhitePawn(), createWhitePawn(), createWhiteRook());
        List<Piece> expectedBlackSortResult = List.of(createBlackKing(), createBlackPawn(), createBlackRook(), createBlackQueen());
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

        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        List<Piece> expectedWhiteSortResult = List.of(createWhiteRook(), createWhitePawn(), createWhitePawn(), createWhiteKing());
        List<Piece> expectedBlackSortResult = List.of(createBlackQueen(), createBlackRook(), createBlackPawn(), createBlackKing());
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