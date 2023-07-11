package chess;

import chess.pieces.Color;
import chess.pieces.Piece;

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

    public void addPiece(String square, Piece piece) {
        board.addPiece(square, piece);
    }

    public Piece findPiece(String square) {
        return board.findPiece(square);
    }

    public void move(String sourceSquare, String targetSquare) {
        try {
            Piece sourcePiece = board.findPiece(sourceSquare);
            Piece targetPiece = board.findPiece(targetSquare);

            movePieceTo(sourcePiece, targetPiece.getPosition());
            movePieceTo(targetPiece, sourcePiece.getPosition());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void movePieceTo(Piece piece, Position position) {
        board.addPiece(position.toSquare(), piece.cloneWithoutPosition(position));
    }

    public double calculatePoint(Color color) {
        return calculatePlusPoint(color) + calculatePenaltyPoint(color);
    }

    private double calculatePlusPoint(Color color) {
        double plusPoint = board.getRanks().stream().mapToDouble(rank -> rank.calculatePoint(color)).sum();
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
