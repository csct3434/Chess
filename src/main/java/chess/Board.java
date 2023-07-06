package chess;

import chess.pieces.Piece;
import chess.pieces.Rank;

import java.util.ArrayList;
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

        for (char file = 'a'; file <= 'h'; file++) {
            int pawnsPerFile = 0;

            for (char rank = '1'; rank <= '8'; rank++) {
                String position = new StringBuilder().append(file).append(rank).toString();

                Piece piece = findPiece(position);

                if (piece.getColor() == color) {
                    point += piece.getType().getDefaultPoint();

                    if (piece.getType() == Piece.Type.PAWN) {
                        pawnsPerFile++;
                    }
                }
            }

            if (pawnsPerFile > 1) {
                point -= 0.5 * pawnsPerFile;
            }
        }

        return point;
    }
}