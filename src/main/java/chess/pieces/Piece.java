package chess.pieces;

import chess.Position;

import java.util.Objects;

public class Piece implements Comparable<Piece> {
    // 기물의 색상
    private final Color color;
    // 기물의 종류
    private final Type type;
    // 기물의 위치
    private final Position position;

    private Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (isWhite()) {
            return type.getWhiteRepresentation();
        } else {
            return type.getBlackRepresentation();
        }
    }

    public Position getPosition() {
        return position;
    }

    public static Piece createWhitePawn(Position position) {
        return createWhite(Type.PAWN, position);
    }

    public static Piece createBlackPawn(Position position) {
        return createBlack(Type.PAWN, position);
    }

    public static Piece createWhiteKnight(Position position) {
        return createWhite(Type.KNIGHT, position);
    }

    public static Piece createBlackKnight(Position position) {
        return createBlack(Type.KNIGHT, position);
    }

    public static Piece createWhiteRook(Position position) {
        return createWhite(Type.ROOK, position);
    }

    public static Piece createBlackRook(Position position) {
        return createBlack(Type.ROOK, position);
    }

    public static Piece createWhiteBishop(Position position) {
        return createWhite(Type.BISHOP, position);
    }

    public static Piece createBlackBishop(Position position) {
        return createBlack(Type.BISHOP, position);
    }

    public static Piece createWhiteQueen(Position position) {
        return createWhite(Type.QUEEN, position);
    }

    public static Piece createBlackQueen(Position position) {
        return createBlack(Type.QUEEN, position);
    }

    public static Piece createWhiteKing(Position position) {
        return createWhite(Type.KING, position);
    }

    public static Piece createBlackKing(Position position) {
        return createBlack(Type.KING, position);
    }

    public static Piece createBlank(Position position) {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE, position);
    }

    private static Piece createWhite(Type type, Position position) {
        return new Piece(Color.WHITE, type, position);
    }

    private static Piece createBlack(Type type, Position position) {
        return new Piece(Color.BLACK, type, position);
    }

    public boolean isWhite() {
        return (this.color.equals(Color.WHITE));
    }

    public boolean isBlack() {
        return (this.color.equals(Color.BLACK));
    }

    public Piece cloneWithoutPosition(Position position) {
        return new Piece(this.color, this.type, position);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Piece piece = (Piece) obj;
        return ((this.color == piece.color) && (this.type == piece.type) && (this.position.equals(piece.position)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public int compareTo(Piece piece) {
        if (Double.compare(this.type.getDefaultPoint(), piece.type.getDefaultPoint()) == 0) {
            return this.position.toSquare().compareTo(piece.position.toSquare());
        }
        return Double.compare(this.type.getDefaultPoint(), piece.type.getDefaultPoint());
    }

}