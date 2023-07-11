package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

class ChessViewTest {

    Board board;
    ChessView chessView;

    @BeforeEach
    void init() {
        board = new Board();
        chessView = new ChessView(board);
    }

    @Test
    @DisplayName("초기화 된 체스판의 출력을 검증한다")
    void create() throws Exception {
        board.initialize();

        String blankRank = appendNewLine("........");

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessView.showBoard());
    }

    @Test
    @DisplayName("기물이 없는 체스판의 출력을 검증한다")
    void createEmpty() throws Exception {
        board.initializeEmpty();

        String blankRank = appendNewLine("........");

        assertEquals(
                blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank + blankRank,
                chessView.showBoard());
    }
}