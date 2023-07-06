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
        ROCK('r'),
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
        if(isWhite()) {
            return type.getWhiteRepresentation();
        }
        else {
            return type.getBlackRepresentation();
        }
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, Type.KNIGHT);
    }

    public static Piece createWhiteRock() {
        return new Piece(Color.WHITE, Type.ROCK);
    }

    public static Piece createBlackRock() {
        return new Piece(Color.BLACK, Type.ROCK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, Type.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, Type.KING);
    }

    public boolean isWhite() {
        return (this.color.equals(Color.WHITE));
    }

    public boolean isBlack() {
        return (this.color.equals(Color.BLACK));
    }

}