package com.huntercodexs.common.util.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static com.huntercodexs.common.util.DataBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonErrorMessagesUtilTest {

    private CommonErrorMessagesUtil util;

    @BeforeEach
    void setUp() {
        util = new CommonErrorMessagesUtil();
    }

    @Test
    void shouldReturnMessageErrorFromSpecificFile() {
        String result = CommonErrorMessagesUtil.messageError(ERROR_CODE_TEST, PROPERTIES_MESSAGE_FILE_TEST);
        assertEquals(ERROR_MESSAGE_TEST, result);
    }

    @Test
    void shouldReturnMessageErrorsAsJsonString() {
        String json = CommonErrorMessagesUtil.messageErrors(ERROR_CODE_TEST, PROPERTIES_MESSAGE_FILE_TEST);
        assertTrue(json.contains(ERROR_CODE_TEST));
        assertTrue(json.contains(ERROR_MESSAGE_TEST));
    }

    @Test
    void shouldAddErrorAndIncreaseSize() throws Exception {
        Method addMethod = CommonErrorMessagesUtil.class.getDeclaredMethod("add", String.class, String.class);
        addMethod.setAccessible(true);
        addMethod.invoke(util, ERROR_CODE_TEST, PROPERTIES_MESSAGE_FILE_TEST);

        assertEquals(1, util.size());
    }

    @Test
    void shouldClearErrorList() throws Exception {
        Method addMethod = CommonErrorMessagesUtil.class.getDeclaredMethod("add", String.class, String.class);
        addMethod.setAccessible(true);
        addMethod.invoke(util, ERROR_CODE_TEST, PROPERTIES_MESSAGE_FILE_TEST);

        assertEquals(1, util.size());
        util.clear();
        assertEquals(0, util.size());
    }

    @Test
    void shouldConvertErrorsToJsonInToString() throws Exception {
        Method addMethod = CommonErrorMessagesUtil.class.getDeclaredMethod("add", String.class, String.class);
        addMethod.setAccessible(true);
        addMethod.invoke(util, ERROR_CODE_TEST, PROPERTIES_MESSAGE_FILE_TEST);

        String json = util.toString();
        assertTrue(json.contains(ERROR_CODE_TEST));
        assertTrue(json.contains(ERROR_MESSAGE_TEST));

        ObjectMapper mapper = new ObjectMapper();
        assertDoesNotThrow(() -> mapper.readTree(json));
    }

}
