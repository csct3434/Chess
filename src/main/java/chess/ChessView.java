package chess;

import chess.board.Board;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public void showPrompt() {
        System.out.print("userInput: ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String showBoard() {
        System.out.println("\n[현재 체스판]");
        String boardRepresentation = board.getBoardRepresentation();
        System.out.println(boardRepresentation);
        return boardRepresentation;
    }

}
