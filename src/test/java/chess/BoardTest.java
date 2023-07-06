package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertEquals(8, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.PAWN));
        assertEquals(8, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.PAWN));

        assertEquals(2, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.ROOK));
        assertEquals(2, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.ROOK));

        assertEquals(2, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.KNIGHT));
        assertEquals(2, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.KNIGHT));

        assertEquals(2, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.BISHOP));
        assertEquals(2, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.BISHOP));

        assertEquals(1, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.QUEEN));
        assertEquals(1, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.QUEEN));

        assertEquals(1, board.countPiecesOf(Piece.Color.WHITE, Piece.Type.KING));
        assertEquals(1, board.countPiecesOf(Piece.Color.BLACK, Piece.Type.KING));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회할 수 있어야 한다")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("임의의 기물을 체스판의 특정 위치로 추가할 수 있어야 한다")
    public void move() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }
}