package chess.pieces;

import chess.board.Position;

public class Knight extends Piece {
    private Knight(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Knight createWhite(Position position) { return new Knight(Color.WHITE, Type.KNIGHT, position); }

    public static Knight createBlack(Position position) {
        return new Knight(Color.BLACK, Type.KNIGHT, position);
    }

    @Override
    public Piece cloneExceptPosition(Position position) {
        return new Knight(this.color, this.type, position);
    }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return Direction.isKnightMove(xPosMove, yPosMove);
    }

}
