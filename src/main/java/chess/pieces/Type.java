package chess.pieces;

public enum Type {
    PAWN('p', 1.0),
    KNIGHT('n', 2.5),
    ROOK('r', 5.0),
    BISHOP('b', 3.0),
    QUEEN('q', 9.0),
    KING('k', 0.0),
    NO_PIECE('.', 0.0);

    // 기물 식별자
    private final char representation;
    // 기물 점수
    private final double defaultPoint;

    Type(char representation, double defaultPoint) {
        this.representation = representation;
        this.defaultPoint = defaultPoint;
    }

    public char getWhiteRepresentation() {
        return this.representation;
    }

    public char getBlackRepresentation() {
        return Character.toUpperCase(this.representation);
    }

    public double getDefaultPoint() {
        return defaultPoint;
    }
}

