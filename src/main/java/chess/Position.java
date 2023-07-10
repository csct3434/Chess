package chess;

public class Position {

    private final int rankIndex;
    private final int fileIndex;

    public static String toSquare(int fileIndex, int rankIndex) {
        return new StringBuilder().append((char) ('a' + fileIndex)).append((char) ('1' + rankIndex)).toString();
    }

    public Position(String position) {
        verifyPosition(position);
        this.fileIndex = position.charAt(0) - 'a';
        this.rankIndex = position.charAt(1) - '1';
    }

    private void verifyPosition(String position) {
        char file = position.charAt(0);
        char rank = position.charAt(1);

        if (!(file >= 'a' && file <= 'h'))
            throw new IllegalArgumentException("열 좌표가 유효하지 않습니다.");

        if (!(rank >= '1' && rank <= '8')) {
            throw new IllegalArgumentException("행 좌표가 유효하지 않습니다.");
        }
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public String toSquare() {
        StringBuilder sb = new StringBuilder();
        return sb.append('a' + this.fileIndex).append('1' + this.rankIndex).toString();
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
        return ((this.rankIndex == position.rankIndex) && (this.fileIndex == position.fileIndex));
    }
}
