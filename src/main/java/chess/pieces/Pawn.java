package chess.pieces;

public class Pawn {

    // 폰의 색상을 나타냅니다.
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    // 폰의 출력 문자를 나타냅니다.
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';

    private String color;
    private char representation;

    public Pawn() {
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }
}