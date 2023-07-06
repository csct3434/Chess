package chess;

import chess.pieces.Piece;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static utils.StringUtils.appendNewLine;

public class Board {

    public static final int FILES = 8;
    public static final int RANK = 8;

    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public void initialize() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        createWhitePieces();
        createBlackPieces();
    }

    private void createWhitePieces() {
        createWhitePawns();
        createWhiteMajorPieces();
    }

    private void createBlackPieces() {
        createBlackMajorPieces();
        createBlackPawns();
    }

    private void createWhitePawns() {
        for (int i = 0; i < FILES; i++) {
            whitePieces.add(Piece.createWhitePawn());
        }
    }

    private void createBlackPawns() {
        for (int i = 0; i < FILES; i++) {
            blackPieces.add(Piece.createBlackPawn());
        }
    }

    private void createWhiteMajorPieces() {
        whitePieces.add(Piece.createWhiteRock());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteQueen());
        whitePieces.add(Piece.createWhiteKing());
        whitePieces.add(Piece.createWhiteBishop());
        whitePieces.add(Piece.createWhiteKnight());
        whitePieces.add(Piece.createWhiteRock());
    }

    private void createBlackMajorPieces() {
        blackPieces.add(Piece.createBlackRock());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackQueen());
        blackPieces.add(Piece.createBlackKing());
        blackPieces.add(Piece.createBlackBishop());
        blackPieces.add(Piece.createBlackKnight());
        blackPieces.add(Piece.createBlackRock());
    }

    public int pieceCount() {
        return whitePieces.size() + blackPieces.size();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        String blankRankResult = appendNewLine("........");

        sb.append(getBlackPiecesResult())
                .append(blankRankResult)
                .append(blankRankResult)
                .append(blankRankResult)
                .append(blankRankResult)
                .append(getWhitePiecesResult());

        return sb.toString();
    }

    private String getWhitePiecesResult() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < whitePieces.size(); i++) {
            sb.append(whitePieces.get(i).getRepresentation());
            if (i == 7) {
                sb.append(StringUtils.NEWLINE);
            }
        }

        return appendNewLine(sb.toString());
    }

    private String getBlackPiecesResult() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < blackPieces.size(); i++) {
            sb.append(blackPieces.get(i).getRepresentation());
            if (i == 7) {
                sb.append(StringUtils.NEWLINE);
            }
        }

        return appendNewLine(sb.toString());
    }

}