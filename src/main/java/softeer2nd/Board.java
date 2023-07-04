package softeer2nd;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pawn> pawnList = new ArrayList<>();

    public void addPawn(String color) {
        Pawn newPawn = new Pawn(color);
        pawnList.add(newPawn);
    }
}
