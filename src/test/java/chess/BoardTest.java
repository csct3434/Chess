package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.pieces.Piece.*;
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
        assertEquals(32, board.countTotalPieces());
    }

    @Test
    @DisplayName("빈 체스판의 0개의 기물을 가진다")
    void createEmpty() throws Exception {
        board.initializeEmpty();
        assertEquals(0, board.countTotalPieces());
    }

    @Test
    @DisplayName("초기화된 체스판의 기물의 개수와 종류를 확인한다")
    void countSpecificPieces() {
        board.initialize();

        assertEquals(8, board.countPiecesByColorAndType(Color.WHITE, Type.PAWN));
        assertEquals(8, board.countPiecesByColorAndType(Color.BLACK, Type.PAWN));

        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.ROOK));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.ROOK));

        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.KNIGHT));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.KNIGHT));

        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.BISHOP));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.BISHOP));

        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.QUEEN));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.QUEEN));

        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.KING));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.KING));
    }

    @Test
    @DisplayName("좌표를 사용하여 기물을 조회한다")
    void findPiece() throws Exception {
        board.initialize();

        assertEquals(createBlackRook(new Position("a8")), board.findPiece("a8"));
        assertEquals(createBlackRook(new Position("h8")), board.findPiece("h8"));
        assertEquals(createWhiteRook(new Position("a1")), board.findPiece("a1"));
        assertEquals(createWhiteRook(new Position("h1")), board.findPiece("h1"));
    }

    @Test
    @DisplayName("특정 열에 있는 폰의 개수를 색상별로 확인한다")
    void countPawnsInFile() {
        board.initialize();
        Position position = new Position("b3");
        board.addPiece(position.toSquare(), createBlackPawn(position));

        int blackPawnsCountInFileB = 2, whitePawnsCountInFileB = 1;
        assertEquals(blackPawnsCountInFileB, board.countPawnsByColorInFile(Color.BLACK, position.getFileIndex()));
        assertEquals(whitePawnsCountInFileB, board.countPawnsByColorInFile(Color.WHITE, position.getFileIndex()));
    }
}