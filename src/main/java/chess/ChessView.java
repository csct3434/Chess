package chess;

import chess.board.Board;
import chess.board.Rank;

import java.util.List;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        List<Rank> ranks = board.getRanks();

        for (int rankIndex = board.BOARD_LENGTH - 1; rankIndex >= 0; rankIndex--) {
            String rankRepresentation = ranks.get(rankIndex).getRepresentation();
            sb.append(rankRepresentation);
        }

        return sb.toString();
    }
}
