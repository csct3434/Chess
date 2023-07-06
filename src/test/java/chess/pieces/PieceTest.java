package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("모든 기물별로 흰색 말과 검은색 말이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), Piece.Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), Piece.Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), Piece.Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), Piece.Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), Piece.Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), Piece.Type.KING);

        // Blank 기물 생성 검증
        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Piece.Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말은 구분되어야 한다")
    public void colorTest() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        assertTrue(whitePawn.isWhite());
        assertFalse(whitePawn.isBlack());

        assertFalse(blackPawn.isWhite());
        assertTrue(blackPawn.isBlack());
    }

    @Test
    @DisplayName("기물의 종류와 색상에 따라 고유한 식별자를 가진다")
    public void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());

        assertEquals('r', Piece.Type.ROOK.getWhiteRepresentation());
        assertEquals('R', Piece.Type.ROOK.getBlackRepresentation());

        assertEquals('n', Piece.Type.KNIGHT.getWhiteRepresentation());
        assertEquals('N', Piece.Type.KNIGHT.getBlackRepresentation());

        assertEquals('b', Piece.Type.BISHOP.getWhiteRepresentation());
        assertEquals('B', Piece.Type.BISHOP.getBlackRepresentation());

        assertEquals('q', Piece.Type.QUEEN.getWhiteRepresentation());
        assertEquals('Q', Piece.Type.QUEEN.getBlackRepresentation());

        assertEquals('k', Piece.Type.KING.getWhiteRepresentation());
        assertEquals('K', Piece.Type.KING.getBlackRepresentation());

        assertEquals('.', Piece.Type.NO_PIECE.getWhiteRepresentation());
        assertEquals('.', Piece.Type.NO_PIECE.getBlackRepresentation());
    }
}
