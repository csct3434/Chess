package chess.board;

public class Position {
    private final int xPos;
    private final int yPos;

    public Position(String square) throws IllegalArgumentException {
        char file = square.charAt(0);
        char rank = square.charAt(1);
        if (!(file >= 'a' && file <= 'h') || !(rank >= '1' && rank <= '8')) {
            throw new IllegalArgumentException("유효하지 않은 좌표입니다 : " + square);
        }
        this.xPos = file - 'a';
        this.yPos = rank - '1';
    }

    public Position(int xPos, int yPos) throws IllegalArgumentException {
        if (xPos < 0 || xPos > 7 || yPos < 0 || yPos > 7) {
            throw new IllegalArgumentException("유효하지 않은 좌표입니다." + toSquare(xPos, yPos));
        }
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static String toSquare(int xPos, int yPos) {
        return new StringBuilder()
                .append((char) ('a' + xPos))
                .append((char) ('1' + yPos)).toString();
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public static int calculateXPosMove(Position sourcePosition, Position targetPosition) {
        return targetPosition.xPos - sourcePosition.xPos;
    }

    public static int calculateYPosMove(Position sourcePosition, Position targetPosition) {
        return targetPosition.yPos - sourcePosition.yPos;
    }

    public String toSquare() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append((char) ('a' + this.xPos)).append((char) ('1' + this.yPos)).toString();
    }

    public Position getMovedPosition(int xDegree, int yDegree) {
        return new Position(this.xPos + xDegree, this.yPos + yDegree);
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

        Position position = (Position) obj;
        return ((this.xPos == position.xPos) && (this.yPos == position.yPos));
    }
}
