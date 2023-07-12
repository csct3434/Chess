package chess.pieces;

import chess.board.Position;

public class Bishop extends Piece {
    private Bishop(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Bishop createWhite(Position position) { return new Bishop(Color.WHITE, Type.BISHOP, position);}

    public static Bishop createBlack(Position position) {
        return new Bishop(Color.BLACK, Type.BISHOP, position);
    }

    @Override
    public Piece cloneExceptPosition(Position position) { return new Bishop(this.color, this.type, position); }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return Direction.isDiagonalMove(xPosMove, yPosMove);
    }
}
