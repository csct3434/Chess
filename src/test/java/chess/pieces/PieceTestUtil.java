package chess.pieces;

import chess.board.Position;

public class PieceTestUtil {
    public static boolean verifyMovePosition(Piece piece, int xDegree, int yDegree) {
        Position targetPosition = Position.createWithDegreeOffset(piece.getPosition(), xDegree, yDegree);
        return piece.verifyMovePosition(targetPosition);
    }
}
