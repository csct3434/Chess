package chess.board;

import chess.pieces.*;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private static final int WHITE_PAWN_Y_POSITION = 1;
    private static final int BLACK_PAWN_Y_POSITION = 6;

    private List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        verifyPieceCount(pieces);
        this.pieces = pieces;
    }

    private void verifyPieceCount(List<Piece> pieces) {
        if (pieces.size() != Board.LENGTH) {
            throw new RuntimeException("Rank의 기물 개수가 8개가 아닙니다.");
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

        for (int xPos = 0; xPos < Board.LENGTH; xPos++) {
            Position position = new Position(xPos, WHITE_PAWN_Y_POSITION);
            pieces.add(Pawn.createWhite(position));
        }

        return new Rank(pieces);
    }

    public static Rank createWithBlackPawns() {
        List<Piece> pieces = new ArrayList<>();

        for (int xPos = 0; xPos < Board.LENGTH; xPos++) {
            Position position = new Position(xPos, BLACK_PAWN_Y_POSITION);
            pieces.add(Pawn.createBlack(position));
        }

        return new Rank(pieces);
    }

    public static Rank createEmptyRank(int yPos) {
        List<Piece> pieces = new ArrayList<>();

        for (int xPos = 0; xPos < Board.LENGTH; xPos++) {
            Position position = new Position(xPos, yPos);
            pieces.add(Blank.create(position));
        }

        return new Rank(pieces);
    }

    public List<Piece> findPiecesByColor(Color color) {
        return pieces.stream()
                .filter(piece -> piece.checkColor(color))
                .collect(Collectors.toList());
    }

    public int countTotalPieces() {
        return (int) pieces.stream()
                .filter(piece -> piece.getType() != Type.NO_PIECE)
                .count();
    }

    public int countPieceOf(Color color, Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.checkColorAndType(color, type))
                .count();
    }

    public Piece getPieceAt(int xPos) {
        return pieces.get(xPos);
    }

    public void setPieceTo(int xPos, Piece piece) {
        pieces.set(xPos, piece);
    }

    public String getRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int xPos = 0; xPos < Board.LENGTH; xPos++) {
            stringBuilder.append(pieces.get(xPos).getRepresentation());
        }

        return StringUtils.appendNewLine(stringBuilder.toString());
    }
}
