package chess;

import chess.pieces.Piece;
import chess.pieces.Rank;

import java.util.ArrayList;
import java.util.Collections;
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

    private void createEmptyRanks() {
        for (int rankIndex = 2; rankIndex <= 5; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        for (int rankIdx = BOARD_LENGTH - 1; rankIdx >= 0; rankIdx--) {
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

    public Piece findPiece(String square) {
        Position pos = new Position(square);

        Rank rank = ranks.get(pos.getRankIndex());

        return rank.getPiece(pos.getFileIndex());
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();

        for (int rankIndex = 0; rankIndex < BOARD_LENGTH; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    public void move(String sourceSquare, String targetSquare) {
        Piece sourcePiece = findPiece(sourceSquare);
        Piece targetPiece = findPiece(targetSquare);

        Piece sourcePieceWithTargetPosition = sourcePiece.copyWithNewPosition(targetPiece.getPosition());
        Piece targetPieceWithSourcePosition = targetPiece.copyWithNewPosition(sourcePiece.getPosition());

        addPiece(sourceSquare, targetPieceWithSourcePosition);
        addPiece(targetSquare, sourcePieceWithTargetPosition);
    }

    private Rank getRankAtPosition(Position position) {
        return ranks.get(position.getRankIndex());
    }

    public double calculatePoint(Piece.Color color) {
        return calculatePlusPoint(color) + calculatePenaltyPoint(color);
    }

    private double calculatePlusPoint(Piece.Color color) {
        double plusPoint = 0.0;

        for (Rank rank : ranks) {
            plusPoint += rank.calculatePoint(color);
        }

        return plusPoint;
    }

    private double calculatePenaltyPoint(Piece.Color color) {
        double penaltyPoint = 0.0;

        for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
            int pawnCnt = countPawnsWithSameColorInFile(color, fileIndex);
            if (pawnCnt > 1) {
                penaltyPoint -= 0.5 * pawnCnt;
            }
        }

        return penaltyPoint;
    }

    private int countPawnsWithSameColorInFile(Piece.Color color, int fileIndex) {
        int count = 0;

        for (Rank rank : ranks) {
            Piece piece = rank.getPiece(fileIndex);
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

    public void addPiece(String square, Piece piece) {
        Position position = new Position(square);
        Rank rank = getRankAtPosition(position);
        rank.setPiece(position.getFileIndex(), piece);
    }

}