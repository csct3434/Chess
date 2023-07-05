package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;

public class Board {

    private ArrayList<Pawn> whitePawnList = new ArrayList<>();
    private ArrayList<Pawn> blackPawnList = new ArrayList<>();

    public void initialize() {
        for(int i=0; i<8; i++) {
            whitePawnList.add(new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
            blackPawnList.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.BLACK_COLOR);
    }

    private String getPawnsResult(String color) {
        StringBuilder sb = new StringBuilder();

        ArrayList<Pawn> pawnList;

        if(color.equals(Pawn.WHITE_COLOR)) {
            pawnList = whitePawnList;
        } else {
            pawnList = blackPawnList;
        }

        for(Pawn pawn : pawnList) {
            sb.append(pawn.getRepresentation());
        }

        return sb.toString();
    }
}