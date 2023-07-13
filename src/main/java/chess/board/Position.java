package chess.board;

public class Position {
    private final int xPos;
    private final int yPos;

    public Position(String square) throws IllegalArgumentException {
        char file, rank;

        square = square.toLowerCase();

        file = square.charAt(0);
        rank = square.charAt(1);

        if (!(file >= 'a' && file <= 'h') || !(rank >= '1' && rank <= '8')) {
            throw new IllegalArgumentException("유효하지 않은 좌표입니다.");
        }

        this.xPos = file - 'a';
        this.yPos = rank - '1';
    }

    public Position(int xPos, int yPos) throws IllegalArgumentException {
        if (xPos < 0 || xPos > 7 || yPos < 0 || yPos > 7) {
            throw new IllegalArgumentException("유효하지 않은 좌표입니다.");
        }
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static Position createWithDegreeOffset(Position position, int xDegree, int yDegree) {
        return new Position(position.getXPos() + xDegree, position.yPos + yDegree);
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
