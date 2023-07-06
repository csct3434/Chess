package chess.pieces;

import chess.Board;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        verifyPieceCount(pieces);
        this.pieces = pieces;
    }

    private void verifyPieceCount(List<Piece> pieces) {
        if (pieces.size() != Board.LENGTH) {
            throw new IllegalArgumentException("Rank의 기물 개수가 8개가 아닙니다.");
        }
    }

    public static Rank createWithWhiteMajorPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Piece.createWhiteRook());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteQueen());
        pieces.add(Piece.createWhiteKing());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteRook());

        return new Rank(pieces);
    }

    public static Rank createWithBlackMajorPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Piece.createBlackRook());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackQueen());
        pieces.add(Piece.createBlackKing());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackRook());

        return new Rank(pieces);
    }

    public static Rank createWithWhitePawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < Board.LENGTH; i++) {
            pieces.add(Piece.createWhitePawn());
        }

        return new Rank(pieces);
    }

    public static Rank createWithBlackPawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < Board.LENGTH; i++) {
            pieces.add(Piece.createBlackPawn());
        }

        return new Rank(pieces);
    }

    public static Rank createWithBlanks() {
        List<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < Board.LENGTH; i++) {
            pieces.add(Piece.createBlank());
        }

        return new Rank(pieces);
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Board.LENGTH; i++) {
            sb.append(pieces.get(i).getRepresentation());
        }

        return StringUtils.appendNewLine(sb.toString());
    }

    public int pieceCount() {
        return pieces.size();
    }
}