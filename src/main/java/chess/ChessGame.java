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
    private int turnCount;
    private boolean whiteKingDead;
    private boolean blackKingDead;

    ChessGame(Board board) {
        this.board = board;
        this.turnCount = 1;
        whiteKingDead = false;
        blackKingDead = false;
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
        Piece sourcePiece = board.findPiece(new Position(sourceSquare));
        Piece targetPiece = board.findPiece(new Position(targetSquare));

        verifyTurn(sourcePiece.getColor());
        verifyDistinctPosition(sourcePiece.getPosition(), targetPiece.getPosition());

        verifyMoveConditions(sourcePiece, targetPiece);

        checkKingDied(targetPiece);

        movePieceTo(sourcePiece, targetPiece.getPosition());
        movePieceTo(Blank.create(targetPiece.getPosition()), sourcePiece.getPosition());

        turnCount += 1;
    }


    private void verifyTurn(Color color) {
        if ((turnCount % 2 == 1 && color != Color.WHITE) || (turnCount % 2 == 0 && color != Color.BLACK)) {
            throw new RuntimeException("상대방 기물은 이동시킬 수 없습니다.");
        }
    }

    private static void verifyDistinctPosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new RuntimeException("동일한 위치로 이동할 수 없습니다.");
        }
    }

    private void verifyMoveConditions(Piece sourcePiece, Piece targetPiece) {
        boolean isMovePossible =
                sourcePiece.verifyMovePosition(targetPiece.getPosition())
                        && hasNoObstructionWhileMove(sourcePiece, targetPiece)
                        && !(sourcePiece.checkColor(targetPiece.getColor()))
                        && verifyAttack(sourcePiece, targetPiece);

        if (!isMovePossible) {
            throw new RuntimeException("해당 위치로 이동할 수 없습니다.");
        }
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

        Position intermediatePosition = Position.createWithDegreeOffset(sourcePosition, xDegree, yDegree);

        while (!intermediatePosition.equals(targetPosition)) {
            if (!board.findPiece(intermediatePosition).checkType(Type.NO_PIECE)) {
                return false;
            }
            intermediatePosition = Position.createWithDegreeOffset(intermediatePosition, xDegree, yDegree);
        }

        return true;
    }

    private boolean verifyAttack(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.checkType(Type.PAWN)) {
            return verifyPawnAttack(sourcePiece, targetPiece);
        }
        return true;
    }

    private boolean verifyPawnAttack(Piece sourcePiece, Piece targetPiece) {
        boolean isLinearMove = sourcePiece.getPosition().getXPos() == targetPiece.getPosition().getXPos();
        if (isLinearMove) {
            return targetPiece.checkType(Type.NO_PIECE);
        }
        return !targetPiece.checkType(Type.NO_PIECE);
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

    private void checkKingDied(Piece targetPiece) {
        if (targetPiece.checkType(Type.KING)) {
            if (targetPiece.checkColor(Color.WHITE)) {
                whiteKingDead = true;
            } else {
                blackKingDead = true;
            }
        }
    }

    public String getTurnPresentation() {
        if (turnCount % 2 == 1) {
            return "(백)";
        }
        return "(흑)";
    }


    public boolean isKingDead() {
        return whiteKingDead || blackKingDead;
    }

    public String getWinner() {
        if (whiteKingDead) {
            return "흑";
        }
        return "백";
    }

    public double getWhitePoint() {
        return calculatePoint(Color.WHITE);
    }

    public double getBlackPoint() {
        return calculatePoint(Color.BLACK);
    }
}
