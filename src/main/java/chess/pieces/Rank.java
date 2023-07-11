package chess.pieces;

import chess.Board;
import chess.Position;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private static final int WHITE_MAJOR_PIECE_RANK_INDEX = 0;
    private static final int WHITE_PAWN_RANK_INDEX = 1;
    private static final int BLACK_PAWN_RANK_INDEX = 6;
    private static final int BLACK_MAJOR_PIECE_RANK_INDEX = 7;

    private List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        verifyPieceCount(pieces);
        this.pieces = pieces;
    }

    private void verifyPieceCount(List<Piece> pieces) {
        if (pieces.size() != Board.BOARD_LENGTH) {
            throw new IllegalArgumentException("Rank의 기물 개수가 8개가 아닙니다.");
        }
    }

    public static Rank createWithWhiteMajorPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Piece.createWhiteRook(new Position("a1")));
        pieces.add(Piece.createWhiteKnight(new Position("b1")));
        pieces.add(Piece.createWhiteBishop(new Position("c1")));
        pieces.add(Piece.createWhiteQueen(new Position("d1")));
        pieces.add(Piece.createWhiteKing(new Position("e1")));
        pieces.add(Piece.createWhiteBishop(new Position("f1")));
        pieces.add(Piece.createWhiteKnight(new Position("g1")));
        pieces.add(Piece.createWhiteRook(new Position("h1")));

        return new Rank(pieces);
    }

    public static Rank createWithBlackMajorPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Piece.createBlackRook(new Position("a8")));
        pieces.add(Piece.createBlackKnight(new Position("b8")));
        pieces.add(Piece.createBlackBishop(new Position("c8")));
        pieces.add(Piece.createBlackQueen(new Position("d8")));
        pieces.add(Piece.createBlackKing(new Position("e8")));
        pieces.add(Piece.createBlackBishop(new Position("f8")));
        pieces.add(Piece.createBlackKnight(new Position("g8")));
        pieces.add(Piece.createBlackRook(new Position("h8")));

        return new Rank(pieces);
    }

    public static Rank createWithWhitePawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            String square = Position.toSquare(fileIndex, WHITE_PAWN_RANK_INDEX);
            Position position = new Position(square);
            pieces.add(Piece.createWhitePawn(position));
        }

        return new Rank(pieces);
    }

    public static Rank createWithBlackPawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            String square = Position.toSquare(fileIndex, BLACK_PAWN_RANK_INDEX);
            Position position = new Position(square);
            pieces.add(Piece.createBlackPawn(position));
        }

        return new Rank(pieces);
    }

    public static Rank createEmptyRank(int rankIndex) {
        List<Piece> pieces = new ArrayList<>();

        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            String square = Position.toSquare(fileIndex, rankIndex);
            Position position = new Position(square);
            pieces.add(Piece.createBlank(position));
        }

        return new Rank(pieces);
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();

        for (int pieceIndex = 0; pieceIndex < Board.BOARD_LENGTH; pieceIndex++) {
            sb.append(pieces.get(pieceIndex).getRepresentation());
        }

        return StringUtils.appendNewLine(sb.toString());
    }

    public int countTotalPieces() {
        int pieceCount = 0;

        for (Piece piece : pieces) {
            if (piece.getType() != Type.NO_PIECE) {
                pieceCount++;
            }
        }

        return pieceCount;
    }

    public int countPiecesOf(Color color, Type type) {
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

    public List<Piece> findPiecesByColor(Color color) {
        List<Piece> findResult = new ArrayList<>();

        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                findResult.add(piece);
            }
        }

        return findResult;
    }

    public double calculatePoint(Color color) {
        double point = 0.0;

        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                point += piece.getType().getDefaultPoint();
            }
        }

        return point;
    }
}
