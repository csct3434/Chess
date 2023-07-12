package chess.pieces;

import chess.board.Position;

import java.util.List;

public class Pawn extends Piece {
    private Pawn(Color color, Type type, Position position) {
        super(color, type, position);
    }

    public static Pawn createWhite(Position position) { return new Pawn(Color.WHITE, Type.PAWN, position); }

    public static Pawn createBlack(Position position) { return new Pawn(Color.BLACK, Type.PAWN, position); }

    @Override
    public Piece cloneExceptPosition(Position position) { return new Pawn(this.color, this.type, position); }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        int xPosMove = Position.calculateXPosMove(this.position, targetPosition);
        int yPosMove = Position.calculateYPosMove(this.position, targetPosition);
        return verifySingleMove(xPosMove, yPosMove) || verifyDoubleMove(xPosMove, yPosMove);
    }

    private boolean verifySingleMove(int xPosMove, int yPosMove) {
        List<Direction> directions = getPawnDirectionsByColor();
        return directions.stream()
                .anyMatch(direction -> direction.getXDegree() == xPosMove && direction.getYDegree() == yPosMove);
    }

    private List<Direction> getPawnDirectionsByColor() {
        if(this.color == Color.WHITE) {
            return Direction.whitePawnDirection();
        }
        return Direction.blackPawnDirection();
    }

    private boolean verifyDoubleMove(int xPosMove, int yPosMove) {
        if (this.color == Color.WHITE) {
            return (this.position.getYPos() == 1) && (xPosMove == 0 && yPosMove == 2);
        }
        return (this.position.getYPos() == 6) && (xPosMove == 0 && yPosMove == -2);
    }


}
