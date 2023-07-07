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

    public static Rank createEmptyRank() {
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

    public int countTotalPieces() {
        int pieceCount = 0;

        for (Piece piece : pieces) {
            if (piece.getType() != Piece.Type.NO_PIECE) {
                pieceCount++;
            }
        }

        return pieceCount;
    }

    public int countPiecesOf(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;

        for (Piece piece : pieces) {
            if (piece.getColor() == color && piece.getType() == type) {
                pieceCount++;
            }
        }

        return pieceCount;
    }

    public Piece getPiece(int fileIndex) {
        return pieces.get(fileIndex);
    }

    public void setPiece(int fileIndex, Piece piece) {
        pieces.set(fileIndex, piece);
    }

    public List<Piece> findPiecesByColor(Piece.Color color) {
        List<Piece> findResult = new ArrayList<>();

        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                findResult.add(piece);
            }
        }

        return findResult;
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0.0;

        for(Piece piece : pieces) {
            if(piece.getColor() == color) {
                point += piece.getType().getDefaultPoint();
            }
        }

        return point;
    }
}
