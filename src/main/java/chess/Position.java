package chess;

public class Position {

    private final int rankIndex;
    private final int fileIndex;

    Position(String position) {
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
}
