package chess.utils;

import chess.board.Position;
import chess.pieces.Piece;

public class PieceTestUtil {
    public static boolean verifyMovePosition(Piece piece, int xDegree, int yDegree) {
        Position targetPosition = Position.createWithDegreeOffset(piece.getPosition(), xDegree, yDegree);
        return piece.verifyMovePosition(targetPosition);
    }
}
