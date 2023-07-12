package chess;

import chess.board.Board;
import chess.board.Position;
import chess.pieces.Blank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;

import java.util.Collections;
import java.util.List;

public class ChessGame {

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

    public void addPiece(Position position, Piece piece) {
        board.addPiece(position, piece);
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);

        if(verifyMoveConditions(sourcePiece, targetPiece)) {
            movePieceTo(sourcePiece, targetPiece.getPosition());
            movePieceTo(Blank.create(targetPiece.getPosition()), sourcePiece.getPosition());
        }
    }

    private boolean verifyMoveConditions(Piece sourcePiece, Piece targetPiece) {
        boolean isTargetColorDifferent = sourcePiece.getColor() != targetPiece.getColor();
        boolean isValidDirection = sourcePiece.verifyMovePosition(targetPiece.getPosition());
        boolean isPathClear = hasNoPieceOnPath(sourcePiece, targetPiece);
        boolean isAttackPossible = verifyAttackPossible(sourcePiece, targetPiece);
        return isTargetColorDifferent && isValidDirection && isPathClear && isAttackPossible;
    }

    private boolean verifyAttackPossible(Piece sourcePiece, Piece targetPiece) {
        if(targetPiece.getColor() == Color.NO_COLOR) {
            return true;
        }

        if(sourcePiece.getType() == Type.PAWN && sourcePiece.getPosition().getXPos() == targetPiece.getPosition().getXPos()) {
            return false;
        }

        return true;
    }

    private boolean hasNoPieceOnPath(Piece sourcePiece, Piece targetPiece) {
        if(sourcePiece.getType() == Type.KNIGHT)
            return true;

        Position sourcePosition = sourcePiece.getPosition();
        Position targetPosition = targetPiece.getPosition();
        Position intermediatePosition;

        int xDegree = getXDegree(sourcePosition, targetPosition);
        int yDegree = getYDegree(sourcePosition, targetPosition);

        intermediatePosition = sourcePosition.getMovedPosition(xDegree, yDegree);

        while(!intermediatePosition.equals(targetPosition)) {
            if(board.findPiece(intermediatePosition).getType() == Type.NO_PIECE) {
                intermediatePosition = intermediatePosition.getMovedPosition(xDegree, yDegree);
            } else {
                return false;
            }
        }

        return true;
    }

    private int getXDegree(Position sourcePosition, Position targetPosition) {
        int xPosDiff = targetPosition.getXPos() - sourcePosition.getXPos();
        if(xPosDiff == 0)
            return xPosDiff;
        return xPosDiff / Math.abs(xPosDiff);
    }

    private int getYDegree(Position sourcePosition, Position targetPosition) {
        int yPosDiff = targetPosition.getYPos() - sourcePosition.getYPos();
        if(yPosDiff == 0)
            return yPosDiff;
        return yPosDiff / Math.abs(yPosDiff);
    }

    private void movePieceTo(Piece piece, Position position) {
        board.addPiece(position, piece.cloneExceptPosition(position));
    }

    public double calculatePoint(Color color) {
        return calculatePlusPoint(color) + calculatePenaltyPoint(color);
    }

    private double calculatePlusPoint(Color color) {
        double plusPoint = board.getRanks().stream()
                .mapToDouble(rank -> rank.calculatePoint(color))
                .sum();
        return plusPoint;
    }

    private double calculatePenaltyPoint(Color color) {
        double penaltyPoint = 0.0;
        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            int pawnCnt = board.countPawnsByColorInFile(color, fileIndex);
            if (pawnCnt > 1) {
                penaltyPoint -= 0.5 * pawnCnt;
            }
        }
        return penaltyPoint;
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

}
