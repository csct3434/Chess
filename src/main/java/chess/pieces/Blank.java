package chess.pieces;

import chess.board.Position;

import java.util.ArrayList;

public class Blank extends Piece {
    private Blank(Color color, Type type, Position position) {
        super(color, type, position);
    }
    public static Blank create(Position position) {
        return new Blank(Color.NO_COLOR, Type.NO_PIECE, position);
    }

    @Override
    public Piece cloneExceptPosition(Position position) {
        return new Blank(this.color, this.type, position);
    }

    @Override
    public boolean verifyMovePosition(Position targetPosition) {
        return false;
    }

}
