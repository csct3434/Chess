package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PieceTest {

    @Test
    @DisplayName("모든 종류의 기물 마다 흰색 말과 검은색 말이 생성되어야 한다")
    public void create_piece() {
        // 폰 기물 생성
        verifyPiece(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

        // 나이트 기물 생성
        verifyPiece(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPiece(Piece.createBlackKnight(), Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

        // 룩 기물 생성
        verifyPiece(Piece.createWhiteRock(), Piece.WHITE_COLOR, Piece.WHITE_ROCK_REPRESENTATION);
        verifyPiece(Piece.createBlackRock(), Piece.BLACK_COLOR, Piece.BLACK_ROCK_REPRESENTATION);

        // 비숍 기물 생성
        verifyPiece(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createBlackBishop(), Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

        // 퀸 기물 생성
        verifyPiece(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createBlackQueen(), Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);

        // 킹 기물 생성
        verifyPiece(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createBlackKing(), Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}
