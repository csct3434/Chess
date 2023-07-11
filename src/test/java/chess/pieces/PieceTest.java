package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("모든 기물별로 흰색 말과 검은색 말이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(new Position("a1")), Piece.createBlackPawn(new Position("a1")), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(new Position("a1")), Piece.createBlackKnight(new Position("a1")), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(new Position("a1")), Piece.createBlackRook(new Position("a1")), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(new Position("a1")), Piece.createBlackBishop(new Position("a1")), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(new Position("a1")), Piece.createBlackQueen(new Position("a1")), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(new Position("a1")), Piece.createBlackKing(new Position("a1")), Type.KING);

        // Blank 기물 생성 검증
        Piece blank = Piece.createBlank(new Position("a1"));
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("검은색 말과 흰색 말은 구분되어야 한다")
    public void colorTest() {
        Piece whitePawn = Piece.createWhitePawn(new Position("a1"));
        Piece blackPawn = Piece.createBlackPawn(new Position("a1"));

        assertTrue(whitePawn.isWhite());
        assertFalse(whitePawn.isBlack());

        assertFalse(blackPawn.isWhite());
        assertTrue(blackPawn.isBlack());
    }

    @Test
    @DisplayName("기물의 종류와 색상에 따라 고유한 식별자를 가진다")
    public void getRepresentationPerPiece() throws Exception {
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
