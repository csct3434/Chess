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


    // 폰 이외의 흰색 기물들(룩, 나이트, 비숍, 퀸, 킹)을 생성
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

    // 폰 이외의 검정색 기물들(룩, 나이트, 비숍, 퀸, 킹)을 생성
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
        return getPiecesResult(Piece.Color.WHITE);
    }

    private String getBlackPiecesResult() {
        return getPiecesResult(Piece.Color.BLACK);
    }

    private String getPiecesResult(Piece.Color color) {
        StringBuilder sb = new StringBuilder();
        List<Piece> pieces;

        if(color.equals(Piece.Color.WHITE)) {
            pieces = whitePieces;
        }
        else {
            pieces = blackPieces;
        }

        for(int i = 0; i < pieces.size(); i++) {
            sb.append(pieces.get(i).getRepresentation());
            if(i == 7) {
                sb.append(StringUtils.NEWLINE);
            }
        }

        return appendNewLine(sb.toString());
    }
}