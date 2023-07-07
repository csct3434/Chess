package chess;

import chess.pieces.Piece;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int LENGTH = 8;

    private List<Rank> ranks;

    public void initialize() {
        ranks = new ArrayList<>();

        // Rank-1 기물 생성
        ranks.add(Rank.createWithWhiteMajorPieces());
        // Rank-2 기물 생성
        ranks.add(Rank.createWithWhitePawns());
        // Rank-3 ~ Rank-6 기물 생성
        ranks.add(Rank.createEmptyRank());
        ranks.add(Rank.createEmptyRank());
        ranks.add(Rank.createEmptyRank());
        ranks.add(Rank.createEmptyRank());
        // Rank-7 가물 생성
        ranks.add(Rank.createWithBlackPawns());
        // Rank-8 기물 생성
        ranks.add(Rank.createWithBlackMajorPieces());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        for (int rankIdx = LENGTH - 1; rankIdx >= 0; rankIdx--) {
            String rankRepresentation = ranks.get(rankIdx).getRepresentation();
            sb.append(rankRepresentation);
        }

        return sb.toString();
    }

    public int countTotalPieces() {
        int totalPieceCount = 0;

        for (Rank rank : ranks) {
            totalPieceCount += rank.countTotalPieces();
        }

        return totalPieceCount;
    }

    public int countPiecesOf(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;

        for (Rank rank : ranks) {
            pieceCount += rank.countPiecesOf(color, type);
        }

        return pieceCount;
    }

    public Piece findPiece(String position) {
        Position pos = new Position(position);

        Rank rank = ranks.get(pos.getRankIndex());

        return rank.getPiece(pos.getFileIndex());
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();

        for (int i = 0; i < LENGTH; i++) {
            ranks.add(Rank.createEmptyRank());
        }
    }

    public void move(String position, Piece piece) {
        Position pos = new Position(position);
        Rank rank = ranks.get(pos.getRankIndex());
        rank.setPiece(pos.getFileIndex(), piece);
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0.0;

        point += calculatePlusPoint(color);
        point += calculateMinusPoint(color);

        return point;
    }

    private double calculatePlusPoint(Piece.Color color) {
        double plusPoint = 0.0;

        for (Rank rank : ranks) {
            plusPoint += rank.calculatePoint(color);
        }

        return plusPoint;
    }

    private double calculateMinusPoint(Piece.Color color) {
        double minusPoint = 0.0;

        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            int pawnCnt = countPawnsInFile(fileIndex, color);
            if (pawnCnt > 1) {
                minusPoint -= 0.5 * pawnCnt;
            }
        }

        return minusPoint;
    }

    private int countPawnsInFile(int fileIndex, Piece.Color color) {
        int count = 0;

        for (int rankIndex = 0; rankIndex < LENGTH; rankIndex++) {
            Piece piece = ranks.get(rankIndex).getPiece(fileIndex);
            if (piece.getColor() == color && piece.getType() == Piece.Type.PAWN) {
                count++;
            }
        }

        return count;
    }

    public List<Piece> sortPiecesByPointAscending(Piece.Color color) {
        List<Piece> pieces = findPiecesByColor(color);

        Collections.sort(pieces);

        return pieces;
    }


    public List<Piece> sortPiecesByPointDescending(Piece.Color color) {
        List<Piece> pieces = findPiecesByColor(color);

        Collections.sort(pieces, Collections.reverseOrder());

        return pieces;
    }

    private List<Piece> findPiecesByColor(Piece.Color color) {
        List<Piece> pieces = new ArrayList<>();

        for (Rank rank : ranks) {
            pieces.addAll(rank.findPiecesByColor(color));
        }

        return pieces;
    }

}