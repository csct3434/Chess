package chess.pieces;

import chess.board.Position;

public class Rook extends Piece {
    private Rook(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Rook createWhite(Position position) {
        return new Rook(Color.WHITE, Type.ROOK, position);
    }

    public static Rook createBlack(Position position) {
        return new Rook(Color.BLACK, Type.ROOK, position);
    }

    @Override
    public Piece cloneExceptPosition(Position position) {
        return new Rook(this.color, this.type, position);
    }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return Direction.isLinearMove(xPosMove, yPosMove);
    }

}
