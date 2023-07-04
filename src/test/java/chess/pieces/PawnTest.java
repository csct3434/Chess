package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰과 검은색 폰이 생성되어야 한다")
    public void create() {
        for(String pawnColor : Pawn.colors) {
            Pawn newPawn = new Pawn(pawnColor);
            assertThat(verifyPawn(newPawn, pawnColor)).isEqualTo(true);
        }
    }

    private boolean verifyPawn(Pawn pawn, String color) {
        return pawn.getColor().equals(color);
    }

    @Test
    @DisplayName("폰 색상 미지정 시, 흰색 폰이 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }
}
