package chess;

import chess.board.Board;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        String boardRepresentation = board.getBoardRepresentation();
        System.out.println(boardRepresentation);
        return boardRepresentation;
    }
}
