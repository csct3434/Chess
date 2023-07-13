package chess;

import chess.board.Board;
import utils.StringUtils;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public void showMessage(String message) {
        System.out.print(message);
    }

    public void showWarningMessage(String message) {
        String warningMessage = new StringBuilder()
                .append(StringUtils.NEWLINE)
                .append(message)
                .append(StringUtils.NEWLINE)
                .toString();
        System.out.println(warningMessage);
    }

    public String showBoard() {
        System.out.println("\n[현재 체스판]");
        String boardRepresentation = board.getBoardRepresentation();
        System.out.println(boardRepresentation);
        return boardRepresentation;
    }

}
