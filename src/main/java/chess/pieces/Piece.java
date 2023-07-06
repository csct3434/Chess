package chess.pieces;

public class Piece {

    // 기물 색상 상수
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    // 기물 종류 상수
    public static final String PAWN = "pawn";
    public static final String KNIGHT = "knight";
    public static final String ROCK = "rook";
    public static final String BISHOP = "bishop";
    public static final String QUEEN = "queen";
    public static final String KING = "king";

    // 기물 출력 문자 상수
    public static final char WHITE_PAWN_REPRESENTATION = 'p';
    public static final char BLACK_PAWN_REPRESENTATION = 'P';
    public static final char WHITE_KNIGHT_REPRESENTATION = 'n';
    public static final char BLACK_KNIGHT_REPRESENTATION = 'N';
    public static final char WHITE_ROCK_REPRESENTATION = 'r';
    public static final char BLACK_ROCK_REPRESENTATION = 'R';
    public static final char WHITE_BISHOP_REPRESENTATION = 'b';
    public static final char BLACK_BISHOP_REPRESENTATION = 'B';
    public static final char WHITE_QUEEN_REPRESENTATION = 'q';
    public static final char BLACK_QUEEN_REPRESENTATION = 'Q';
    public static final char WHITE_KING_REPRESENTATION = 'k';
    public static final char BLACK_KING_REPRESENTATION = 'K';

    // 기물의 색상
    private final String color;
    // 기물의 종류
    private final String name;
    // 기물의 출력문자
    private final char representation;

    private Piece(String color, String name, char representation) {
        this.color = color;
        this.name = name;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public char getRepresentation() {
        return representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, PAWN, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, PAWN, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, KNIGHT, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, KNIGHT, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRock() {
        return new Piece(WHITE_COLOR, ROCK, WHITE_ROCK_REPRESENTATION);
    }

    public static Piece createBlackRock() {
        return new Piece(BLACK_COLOR, ROCK, BLACK_ROCK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, BISHOP, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, BISHOP, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, QUEEN, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, QUEEN, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, KING, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, KING, BLACK_KING_REPRESENTATION);
    }

    public boolean isWhite() {
        return (this.color == WHITE_COLOR);
    }

    public boolean isBlack() {
        return (this.color == BLACK_COLOR);
    }

}