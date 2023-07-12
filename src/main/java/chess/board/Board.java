package chess.board;

import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    public static final int LENGTH = 8;

    private List<Rank> ranks;

    public void initialize() {
        ranks = new ArrayList<>();
        ranks.add(Rank.createWithWhiteMajorPieces());
        ranks.add(Rank.createWithWhitePawns());
        addEmptyRanks();
        ranks.add(Rank.createWithBlackPawns());
        ranks.add(Rank.createWithBlackMajorPieces());
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();
        for (int rankIndex = 0; rankIndex < LENGTH; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
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
                .setPieceTo(position.getXPos(), piece);
    }

    public int countAllPieces() {
        return ranks.stream()
                .mapToInt(Rank::countTotalPieces).sum();
    }

    public int countPieceOf(Color color, Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.countPieceOf(color, type))
                .sum();
    }

    public int countPawnsByColorInFile(Color color, int xPos) {
        return (int) ranks.stream()
                .map(rank -> rank.getPieceAt(xPos))
                .filter(piece -> piece.checkColorAndType(color, Type.PAWN))
                .count();
    }

    public String getBoardRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int rankIndex = Board.LENGTH - 1; rankIndex >= 0; rankIndex--) {
            String rankRepresentation = ranks.get(rankIndex).getRepresentation();
            stringBuilder.append(rankRepresentation);
        }

        return stringBuilder.toString();
    }

    private void addEmptyRanks() {
        for (int rankIndex = 2; rankIndex <= 5; rankIndex++) {
            ranks.add(Rank.createEmptyRank(rankIndex));
        }
    }

}
