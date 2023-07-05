package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.StringUtils.NEWLINE;
import static utils.StringUtils.appendNewLine;

public class StringUtilsTest {
    @Test
    public void appendNewLineTest() {
        String text = "pppppppp";
        String expectedString = text + NEWLINE;

        assertEquals(expectedString, appendNewLine(text));
    }
}
