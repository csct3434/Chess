package chess;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.Blank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ChessGame {

    private static final double PENALTY_POINT = 0.5;

    private final Board board;

    ChessGame(Board board) {
        this.board = board;
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void initializeEmptyBoard() {
        board.initializeEmpty();
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }

    public void addPiece(Position position, Piece piece) {
        board.addPiece(position, piece);
    }

    public double calculatePoint(Color color) {
        return calculatePlusPoint(color) + calculatePenaltyPoint(color);
    }

    public List<Piece> sortPiecesByPointAscending(Color color) {
        List<Piece> pieces = board.findPiecesByColor(color);
        Collections.sort(pieces);
        return pieces;
    }

    public List<Piece> sortPiecesByPointDescending(Color color) {
        List<Piece> pieces = board.findPiecesByColor(color);
        Collections.sort(pieces, Collections.reverseOrder());
        return pieces;
    }

    public void move(String sourceSquare, String targetSquare) {
        Position sourcePosition = new Position(sourceSquare);
        Position targetPosition = new Position(targetSquare);

        if(!verifyDistinctPosition(sourcePosition, targetPosition)) {
            return;
        }

        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        if (verifyMoveConditions(sourcePiece, targetPiece)) {
            movePieceTo(sourcePiece, targetPiece.getPosition());
            movePieceTo(Blank.create(targetPiece.getPosition()), sourcePiece.getPosition());
        }
    }

    private static boolean verifyDistinctPosition(Position sourcePosition, Position targetPosition) {
        return !sourcePosition.equals(targetPosition);
    }

    private double calculatePlusPoint(Color color) {
        return board.findPiecesByColor(color).stream()
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
    }

    private double calculatePenaltyPoint(Color color) {
        int penaltyCount = IntStream.range(0, Board.LENGTH)
                .map(fileIndex -> board.countPawnsByColorInFile(color, fileIndex))
                .filter(pawnsCountInFile -> pawnsCountInFile > 1)
                .sum();

        return PENALTY_POINT * penaltyCount;
    }

    private boolean verifyMoveConditions(Piece sourcePiece, Piece targetPiece) {
        return sourcePiece.verifyMovePosition(targetPiece.getPosition())
                && hasNoObstructionWhileMove(sourcePiece, targetPiece)
                && !(sourcePiece.checkColor(targetPiece.getColor()))
                && verifyAttack(sourcePiece, targetPiece);
    }

    private boolean verifyAttack(Piece sourcePiece, Piece targetPiece) {
        if (targetPiece.checkColor(Color.NO_COLOR) || !sourcePiece.checkType(Type.PAWN)) {
            return true;
        }
        return verifyPawnAttack(sourcePiece, targetPiece);
    }

    private boolean verifyPawnAttack(Piece sourcePiece, Piece targetPiece) {
        return sourcePiece.getPosition().getXPos() != targetPiece.getPosition().getXPos();
    }

    private boolean hasNoObstructionWhileMove(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.checkType(Type.KNIGHT)) {
            return true;
        }
        return verifyPathClear(sourcePiece.getPosition(), targetPiece.getPosition());
    }

    private boolean verifyPathClear(Position sourcePosition, Position targetPosition) {
        int xDegree = getXDegree(sourcePosition, targetPosition);
        int yDegree = getYDegree(sourcePosition, targetPosition);

        Position intermediatePosition = sourcePosition.getMovedPosition(xDegree, yDegree);

        while (!intermediatePosition.equals(targetPosition)) {
            if (!board.findPiece(intermediatePosition).checkType(Type.NO_PIECE)) {
                return false;
            }
            intermediatePosition = intermediatePosition.getMovedPosition(xDegree, yDegree);
        }

        return true;
    }

    private int getXDegree(Position sourcePosition, Position targetPosition) {
        int xPosDiff = targetPosition.getXPos() - sourcePosition.getXPos();
        if (xPosDiff == 0)
            return xPosDiff;
        return xPosDiff / Math.abs(xPosDiff);
    }

    private int getYDegree(Position sourcePosition, Position targetPosition) {
        int yPosDiff = targetPosition.getYPos() - sourcePosition.getYPos();
        if (yPosDiff == 0)
            return yPosDiff;
        return yPosDiff / Math.abs(yPosDiff);
    }

    private void movePieceTo(Piece piece, Position position) {
        board.addPiece(position, piece.cloneExceptPosition(position));
    }

}
