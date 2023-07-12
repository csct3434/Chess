package chess.board;

import chess.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("초기화 된 체스판은 32개의 기물을 가진다")
    void create() throws Exception {
        board.initialize();
        assertEquals(32, board.countAllPieces());
    }

    @Test
    @DisplayName("빈 체스판의 0개의 기물을 가진다")
    void createEmpty() throws Exception {
        board.initializeEmpty();
        assertEquals(0, board.countAllPieces());
    }

    @Test
    @DisplayName("초기화된 체스판의 기물의 개수와 종류를 확인한다")
    void countSpecificPieces() {
        board.initialize();

        assertEquals(8, board.countPieceOf(Color.WHITE, Type.PAWN));
        assertEquals(8, board.countPieceOf(Color.BLACK, Type.PAWN));

        assertEquals(2, board.countPieceOf(Color.WHITE, Type.ROOK));
        assertEquals(2, board.countPieceOf(Color.BLACK, Type.ROOK));

        assertEquals(2, board.countPieceOf(Color.WHITE, Type.KNIGHT));
        assertEquals(2, board.countPieceOf(Color.BLACK, Type.KNIGHT));

        assertEquals(2, board.countPieceOf(Color.WHITE, Type.BISHOP));
        assertEquals(2, board.countPieceOf(Color.BLACK, Type.BISHOP));

        assertEquals(1, board.countPieceOf(Color.WHITE, Type.QUEEN));
        assertEquals(1, board.countPieceOf(Color.BLACK, Type.QUEEN));

        assertEquals(1, board.countPieceOf(Color.WHITE, Type.KING));
        assertEquals(1, board.countPieceOf(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("좌표를 사용하여 기물을 조회한다")
    void findPiece() throws Exception {
        board.initialize();

        assertEquals(Rook.createBlack(new Position("a8")), findPiece("a8"));
        assertEquals(Rook.createBlack(new Position("h8")), findPiece("h8"));
        assertEquals(Rook.createWhite(new Position("a1")), findPiece("a1"));
        assertEquals(Rook.createWhite(new Position("h1")), findPiece("h1"));
    }

    private Piece findPiece(String square) {
        return board.findPiece(new Position(square));
    }

    @Test
    @DisplayName("특정 열에 있는 폰의 개수를 색상별로 확인한다")
    void countPawnsInFile() {
        board.initialize();
        Position position = new Position("b3");
        board.addPiece(position, Pawn.createBlack(position));

        int blackPawnsCountInFileB = 2, whitePawnsCountInFileB = 1;
        assertEquals(blackPawnsCountInFileB, board.countPawnsByColorInFile(Color.BLACK, position.getXPos()));
        assertEquals(whitePawnsCountInFileB, board.countPawnsByColorInFile(Color.WHITE, position.getXPos()));
    }
}
