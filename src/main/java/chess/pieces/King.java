package chess.pieces;

import chess.board.Position;

public class King extends Piece {
    private King(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static King createWhite(Position position) {
        return new King(Color.WHITE, Type.KING, position);
    }

    public static King createBlack(Position position) { return new King(Color.BLACK, Type.KING, position); }

    @Override
    public Piece cloneExceptPosition(Position position) {
        return new King(this.color, this.type, position);
    }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return (Math.abs(xPosMove) <= 1) && (Math.abs(yPosMove) <= 1);
    }

}
