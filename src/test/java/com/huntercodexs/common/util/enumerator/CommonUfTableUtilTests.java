package com.huntercodexs.common.util.enumerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUfTableUtilTests {

    @Test
    public void checkUfExistsTest() {
        assertTrue(CommonUfTableUtil.checkUfExists("SP"));
        assertFalse(CommonUfTableUtil.checkUfExists("NN"));
    }

    @Test
    public void checkUfCodeExistsTest() {
        assertTrue(CommonUfTableUtil.checkUfCodeExists("35"));
        assertFalse(CommonUfTableUtil.checkUfCodeExists("99"));
    }

    @Test
    public void checkUfNameExistsTest() {
        assertTrue(CommonUfTableUtil.checkUfNameExists("São Paulo"));
        assertFalse(CommonUfTableUtil.checkUfNameExists("Sao Test"));
    }

    @Test
    public void checkRgSspExistsTest() {
        assertTrue(CommonUfTableUtil.checkRgSspExists("SSPSP"));
        assertFalse(CommonUfTableUtil.checkRgSspExists("SSPLL"));
    }

}
