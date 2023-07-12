package chess.pieces;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    @DisplayName("모든 기물별로 흰색 말과 검은색 말이 생성되어야 한다")
    void create_piece() {
        verifyPiece(Pawn.createWhite(new Position("a1")), Pawn.createBlack(new Position("a1")), Type.PAWN);
        verifyPiece(Knight.createWhite(new Position("a1")), Knight.createBlack(new Position("a1")), Type.KNIGHT);
        verifyPiece(Rook.createWhite(new Position("a1")), Rook.createBlack(new Position("a1")), Type.ROOK);
        verifyPiece(Bishop.createWhite(new Position("a1")), Bishop.createBlack(new Position("a1")), Type.BISHOP);
        verifyPiece(Queen.createWhite(new Position("a1")), Queen.createBlack(new Position("a1")), Type.QUEEN);
        verifyPiece(King.createWhite(new Position("a1")), King.createBlack(new Position("a1")), Type.KING);

        Piece blank = Blank.create(new Position("a1"));
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말은 구분되어야 한다")
    void colorTest() {
        Piece whitePawn = Pawn.createWhite(new Position("a1"));
        Piece blackPawn = Pawn.createBlack(new Position("a1"));

        assertTrue(whitePawn.isWhite());
        assertFalse(whitePawn.isBlack());

        assertFalse(blackPawn.isWhite());
        assertTrue(blackPawn.isBlack());
    }

    @Test
    @DisplayName("기물의 종류와 색상에 따라 고유한 식별자를 가진다")
    void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());

        assertEquals('r', Type.ROOK.getWhiteRepresentation());
        assertEquals('R', Type.ROOK.getBlackRepresentation());

        assertEquals('n', Type.KNIGHT.getWhiteRepresentation());
        assertEquals('N', Type.KNIGHT.getBlackRepresentation());

        assertEquals('b', Type.BISHOP.getWhiteRepresentation());
        assertEquals('B', Type.BISHOP.getBlackRepresentation());

        assertEquals('q', Type.QUEEN.getWhiteRepresentation());
        assertEquals('Q', Type.QUEEN.getBlackRepresentation());

        assertEquals('k', Type.KING.getWhiteRepresentation());
        assertEquals('K', Type.KING.getBlackRepresentation());

        assertEquals('.', Type.NO_PIECE.getWhiteRepresentation());
        assertEquals('.', Type.NO_PIECE.getBlackRepresentation());
    }
}
