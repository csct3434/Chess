package softeer2nd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰과 검은색 폰이 생성되어야 한다")
    public void create() {
        String colorWhite = "white";
        String colorBlack = "black";

        Pawn whitePawn = new Pawn(colorWhite);
        assertThat(verifyPawn(whitePawn, colorWhite)).isEqualTo(true);

        Pawn blackPawn = new Pawn(colorBlack);
        assertThat(verifyPawn(blackPawn, colorBlack)).isEqualTo(true);
    }

    private boolean verifyPawn(Pawn pawn, String color) {
        return pawn.getColor().equals(color);
    }

    @Test
    @DisplayName("폰 색상 미지정 시, 흰색 폰이 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals("white", pawn.getColor());
    }
}
