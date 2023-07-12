package chess.pieces;

import chess.board.Position;

public class Queen extends Piece {
    private Queen(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Queen createWhite(Position position) {
        return new Queen(Color.WHITE, Type.QUEEN, position);
    }

    public static Queen createBlack(Position position) {
        return new Queen(Color.BLACK, Type.QUEEN, position);
    }

    @Override
    public Piece cloneExceptPosition(Position position) { return new Queen(this.color, this.type, position); }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return Direction.isLinearMove(xPosMove, yPosMove) || Direction.isDiagonalMove(xPosMove, yPosMove);
    }

}
