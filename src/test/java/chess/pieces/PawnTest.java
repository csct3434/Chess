package chess.pieces;

import chess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰과 검은색 폰이 생성되어야 한다")
    public void create() {
        Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
        assertThat(verifyPawn(whitePawn, Pawn.WHITE_COLOR)).isEqualTo(true);

        Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);
        assertThat(verifyPawn(blackPawn, Pawn.BLACK_COLOR)).isEqualTo(true);
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
