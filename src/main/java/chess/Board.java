package chess;

import chess.pieces.Piece;

import java.util.ArrayList;

public class Board {

    private ArrayList<Piece> whitePieceList = new ArrayList<>();
    private ArrayList<Piece> blackPieceList = new ArrayList<>();

    public void initialize() {
        for(int i=0; i<8; i++) {
            whitePieceList.add(new Piece(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION));
            blackPieceList.add(new Piece(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION));
        }
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Piece.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Piece.BLACK_COLOR);
    }

    private String getPawnsResult(String color) {
        StringBuilder sb = new StringBuilder();

        ArrayList<Piece> pieceList;

        if(color.equals(Piece.WHITE_COLOR)) {
            pieceList = whitePieceList;
        } else {
            pieceList = blackPieceList;
        }

        for(Piece piece : pieceList) {
            sb.append(piece.getRepresentation());
        }

        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append("........\n");
        sb.append(getBlackPawnsResult() + '\n');
        sb.append("........\n");
        sb.append("........\n");
        sb.append("........\n");
        sb.append("........\n");
        sb.append(getWhitePawnsResult() + '\n');
        sb.append("........\n");

        return sb.toString();
    }
}