package com.huntercodexs.common.util.parser;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.DataBuilder.PROPERTIES_MESSAGE_FILE_TEST;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonPropertiesParserUtilTests {

    @Test
    void shouldReturnEmptyStringWhenKeyNotFound() {
        String result = CommonPropertiesParserUtil.getErrorMessage("UNKNOWN_KEY", PROPERTIES_MESSAGE_FILE_TEST);
        assertTrue(result.contains("Properties Message Error: "));
        assertTrue(result.contains("key UNKNOWN_KEY"));
    }

    @Test
    void shouldFallbackToDefaultFileWhenFilePathIsNull() {
        String result = CommonPropertiesParserUtil.getErrorMessage("ERR002100000000000", null);
        assertTrue(result.contains("Properties Message Error: "));
        assertTrue(result.contains("key ERR002100000000000"));
    }

    @Test
    void shouldFallbackToDefaultFileWhenFilePathNotExists() {
        String result = CommonPropertiesParserUtil.getErrorMessage("ERR002100000000000", "invalid-file-path");
        assertTrue(result.contains("Properties Message Error: "));
        assertTrue(result.contains("Can't find bundle for base name invalid-file-path"));
    }
}






