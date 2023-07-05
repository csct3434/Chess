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
        StringBuilder sb = new StringBuilder();
        for(Pawn whitePawn : whitePawnList) {
            sb.append(whitePawn.getRepresentation());
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for(Pawn blackPawn : blackPawnList) {
            sb.append(blackPawn.getRepresentation());
        }
        return sb.toString();
    }

}