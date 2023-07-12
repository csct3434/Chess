package chess.board;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        addEmptyRanks();
        // Rank-7 : 검정색 폰 생성
        ranks.add(Rank.createWithBlackPawns());
        // Rank-8 : 폰 이외의 검정색 기물 생성
        ranks.add(Rank.createWithBlackMajorPieces());
    }

    private void addEmptyRanks() {
        for (int rankIndex = 2; rankIndex <= 5; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();
        for (int rankIndex = 0; rankIndex < BOARD_LENGTH; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

    public List<Rank> getRanks() {
        return ranks;
    }

    public int countTotalPieces() {
        return ranks.stream()
                .mapToInt(Rank::countTotalPieces).sum();
    }

    public int countPiecesByColorAndType(Color color, Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.countPiecesOf(color, type))
                .sum();
    }

    public int countPawnsByColorInFile(Color color, int xPos) {
        return (int) ranks.stream()
                .map(rank -> rank.getPieceAt(xPos))
                .filter(piece -> verifyPawnTypeAndColor(piece, color))
                .count();
    }

    private boolean verifyPawnTypeAndColor(Piece piece, Color color) {
        return piece.getType() == Type.PAWN && piece.getColor() == color;
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getYPos())
                .getPieceAt(position.getXPos());
    }

    public List<Piece> findPiecesByColor(Color color) {
        return ranks.stream()
                .flatMap(rank -> rank.findPiecesByColor(color).stream())
                .collect(Collectors.toList());
    }

    public void addPiece(Position position, Piece piece) {
        ranks.get(position.getYPos())
                .setPiece(position.getXPos(), piece);
    }
}
