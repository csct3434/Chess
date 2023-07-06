package chess.pieces;

public class Piece {

    // 기물 색상 상수
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    // 기물 종류 상수
    public enum Type {
        PAWN('p'),
        KNIGHT('n'),
        ROOK('r'),
        BISHOP('b'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        // 기물 식별자 상수
        private char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return this.representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(this.representation);
        }
    }

    // 기물의 색상
    private final Color color;
    // 기물의 종류
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
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

    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }

    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }

    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }

    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }

    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }

    public boolean isWhite() {
        return (this.color.equals(Color.WHITE));
    }

    public boolean isBlack() {
        return (this.color.equals(Color.BLACK));
    }

}