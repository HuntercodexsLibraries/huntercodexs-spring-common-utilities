package com.huntercodexs.common.util.extractor;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.basic.CommonsToolsBasicUtil.base64;
import static com.huntercodexs.common.util.extractor.CommonBasicAuthenticationExtractorUtil.getUserBasicAuthentication;
import static org.junit.jupiter.api.Assertions.*;

class CommonBasicAuthenticationExtractorUtilTests {

    @Test
    void shouldExtractAnCorrectUserBasicAuthenticationSuccessfully() {
        var authorization =  base64("user:password");
        var userBasicAuthentication = getUserBasicAuthentication("Basic " + authorization);

        assertNotNull(userBasicAuthentication);
        assertEquals("user" , userBasicAuthentication[0]);
        assertEquals("password" , userBasicAuthentication[1]);
    }

    @Test
    void shouldNotExtractAnCorrectUserBasicAuthenticationDueNorInformedPassword() {
        var authorization = base64("user:");
        var userBasicAuthentication = getUserBasicAuthentication("Basic " + authorization);

        assertNotNull(userBasicAuthentication);
        assertEquals("" , userBasicAuthentication[0]);
        assertEquals("" , userBasicAuthentication[1]);
    }

    @Test
    void shouldNotExtractAnCorrectUserBasicAuthenticationDueNotInformedBasicPrefix() {
        var authorization = base64("user:password");
        var userBasicAuthentication = getUserBasicAuthentication(authorization);

        assertNotNull(userBasicAuthentication);
        assertEquals("" , userBasicAuthentication[0]);
        assertEquals("" , userBasicAuthentication[1]);
    }

    @Test
    void shouldNotExtractAnCorrectUserBasicAuthenticationDueNotInformedACorrectBasicReturnIsNull() {
        var authorization = base64("user");
        var result = getUserBasicAuthentication("Basic" + authorization);
        assertNull(result);
    }

}
