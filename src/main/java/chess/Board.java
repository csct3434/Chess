package chess;

import chess.pieces.Piece;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int BOARD_LENGTH = 8;

    private List<Rank> ranks;

    public void initialize() {
        ranks = new ArrayList<>();
        // Rank-1 : 폰 이외의 흰색 기물 생성
        ranks.add(Rank.createWithWhiteMajorPieces());
        // Rank-2 : 흰색 폰 기물 생성
        ranks.add(Rank.createWithWhitePawns());
        // Rank-3 ~ Rank-6 : 기물이 없는 칸 생성
        createEmptyRanks();
        // Rank-7 : 검정색 폰 생성
        ranks.add(Rank.createWithBlackPawns());
        // Rank-8 : 폰 이외의 검정색 기물 생성
        ranks.add(Rank.createWithBlackMajorPieces());
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();
        for (int rankIndex = 0; rankIndex < BOARD_LENGTH; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    private void createEmptyRanks() {
        for (int rankIndex = 2; rankIndex <= 5; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    private Rank getRankAtPosition(Position position) {
        return ranks.get(position.getRankIndex());
    }

    public int countTotalPieces() {
        int totalPieceCount = 0;
        for (Rank rank : ranks) {
            totalPieceCount += rank.countTotalPieces();
        }
        return totalPieceCount;
    }

    public int countPiecesByColorAndType(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;
        for (Rank rank : ranks) {
            pieceCount += rank.countPiecesOf(color, type);
        }
        return pieceCount;
    }

    public int countPawnsByColorInFile(Piece.Color color, int fileIndex) {
        int count = 0;
        for (Rank rank : ranks) {
            Piece piece = rank.getPiece(fileIndex);
            if (piece.getColor() == color && piece.getType() == Piece.Type.PAWN) {
                count++;
            }
        }
        return count;
    }

    public Piece findPiece(String square) {
        Position pos = new Position(square);
        Rank rank = ranks.get(pos.getRankIndex());
        return rank.getPiece(pos.getFileIndex());
    }

    public List<Piece> findPiecesByColor(Piece.Color color) {
        List<Piece> pieces = new ArrayList<>();
        for (Rank rank : ranks) {
            pieces.addAll(rank.findPiecesByColor(color));
        }
        return pieces;
    }

    public void addPiece(String square, Piece piece) {
        Position position = new Position(square);
        Rank rank = getRankAtPosition(position);
        rank.setPiece(position.getFileIndex(), piece);
    }
}
