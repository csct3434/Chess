package chess.pieces;

import chess.board.Position;

import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {

    protected final Color color;
    protected final Type type;
    protected final Position position;

    protected Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public abstract Piece cloneExceptPosition(Position position);

    public abstract boolean verifyMovePosition(Position targetPosition);

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        if (isWhite()) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public Position getPosition() {
        return position;
    }

    public boolean isWhite() {
        return (this.color.equals(Color.WHITE));
    }

    public boolean isBlack() {
        return (this.color.equals(Color.BLACK));
    }

    public boolean verifyColor(Color color) {
        return this.color == color;
    }

    public boolean verifyColorAndType(Color color, Type type) {
        return this.color == color && this.type == type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Piece piece = (Piece) obj;
        return ((this.color == piece.color) && (this.type == piece.type) && (this.position.equals(piece.position)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public int compareTo(Piece piece) {
        if (Double.compare(this.type.getDefaultPoint(), piece.type.getDefaultPoint()) == 0) {
            return this.position.toSquare().compareTo(piece.position.toSquare());
        }
        return Double.compare(this.type.getDefaultPoint(), piece.type.getDefaultPoint());
    }

}
