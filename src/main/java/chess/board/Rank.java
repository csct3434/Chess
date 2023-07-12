package chess.board;

import chess.pieces.*;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private static final int WHITE_PAWN_RANK_INDEX = 1;
    private static final int BLACK_PAWN_RANK_INDEX = 6;

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

        pieces.add(Rook.createWhite(new Position("a1")));
        pieces.add(Knight.createWhite(new Position("b1")));
        pieces.add(Bishop.createWhite(new Position("c1")));
        pieces.add(Queen.createWhite(new Position("d1")));
        pieces.add(King.createWhite(new Position("e1")));
        pieces.add(Bishop.createWhite(new Position("f1")));
        pieces.add(Knight.createWhite(new Position("g1")));
        pieces.add(Rook.createWhite(new Position("h1")));

        return new Rank(pieces);
    }

    public static Rank createWithBlackMajorPieces() {
        List<Piece> pieces = new ArrayList<>();

        pieces.add(Rook.createBlack(new Position("a8")));
        pieces.add(Knight.createBlack(new Position("b8")));
        pieces.add(Bishop.createBlack(new Position("c8")));
        pieces.add(Queen.createBlack(new Position("d8")));
        pieces.add(King.createBlack(new Position("e8")));
        pieces.add(Bishop.createBlack(new Position("f8")));
        pieces.add(Knight.createBlack(new Position("g8")));
        pieces.add(Rook.createBlack(new Position("h8")));

        return new Rank(pieces);
    }

    public static Rank createWithWhitePawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            String square = Position.toSquare(fileIndex, WHITE_PAWN_RANK_INDEX);
            Position position = new Position(square);
            pieces.add(Pawn.createWhite(position));
        }

        return new Rank(pieces);
    }

    public static Rank createWithBlackPawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int fileIndex = 0; fileIndex < Board.BOARD_LENGTH; fileIndex++) {
            String square = Position.toSquare(fileIndex, BLACK_PAWN_RANK_INDEX);
            Position position = new Position(square);
            pieces.add(Pawn.createBlack(position));
        }

        return new Rank(pieces);
    }

    public static Rank createEmptyRank(int yPos) {
        List<Piece> pieces = new ArrayList<>();

        for (int xPos = 0; xPos < Board.BOARD_LENGTH; xPos++) {
            Position position = new Position(xPos, yPos);
            pieces.add(Blank.create(position));
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
        return (int) pieces.stream()
                .filter(piece -> piece.getType() != Type.NO_PIECE)
                .count();
    }

    public int countPiecesOf(Color color, Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.verifyColorAndType(color, type))
                .count();
    }

    public Piece getPieceAt(int xPos) {
        return pieces.get(xPos);
    }

    public void setPiece(int xPos, Piece piece) {
        pieces.set(xPos, piece);
    }

    public List<Piece> findPiecesByColor(Color color) {
        return pieces.stream()
                .filter(piece -> piece.verifyColor(color))
                .collect(Collectors.toList());
    }

    public double calculatePoint(Color color) {
        return pieces.stream()
                .filter(piece -> piece.verifyColor(color))
                .mapToDouble(piece -> piece.getType().getDefaultPoint())
                .sum();
    }
}
