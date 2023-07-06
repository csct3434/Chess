package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("모든 종류의 기물 마다 흰색 말과 검은색 말이 생성되어야 한다")
    public void create_piece() {
        // 폰 기물 생성
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN);
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Piece.Type.PAWN);

        // 나이트 기물 생성
        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT);
        verifyPiece(Piece.createBlackKnight(), Piece.Color.BLACK, Piece.Type.KNIGHT);

        // 룩 기물 생성
        verifyPiece(Piece.createWhiteRock(), Piece.Color.WHITE, Piece.Type.ROCK);
        verifyPiece(Piece.createBlackRock(), Piece.Color.BLACK, Piece.Type.ROCK);

        // 비숍 기물 생성
        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP);
        verifyPiece(Piece.createBlackBishop(), Piece.Color.BLACK, Piece.Type.BISHOP);

        // 퀸 기물 생성
        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN);
        verifyPiece(Piece.createBlackQueen(), Piece.Color.BLACK, Piece.Type.QUEEN);

        // 킹 기물 생성
        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING);
        verifyPiece(Piece.createBlackKing(), Piece.Color.BLACK, Piece.Type.KING);
    }

    void verifyPiece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getType());
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

        assertEquals('r', Piece.Type.ROCK.getWhiteRepresentation());
        assertEquals('R', Piece.Type.ROCK.getBlackRepresentation());

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
