package com.huntercodexs.common.util.basic;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.basic.CommonsValidatorBasicUtil.*;
import static com.huntercodexs.common.util.generator.CommonDataGeneratorUtil.randomCpf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonValidatorBasicTests {

    @Test
    public void cpfValidatorTest() {
        boolean result = cpfValidator("07365238801");
        assertTrue(result);

        boolean result2 = cpfValidator("07365238899");
        assertFalse(result2);

        boolean result3 = cpfValidator(randomCpf());
        assertTrue(result3);
    }

    @Test
    public void mailValidatorTest() throws Exception {
        boolean result = mailValidator("marcos_portela@yahoo.com.br");
        assertTrue(result);

        result = mailValidator("johnsmith23@email.com");
        assertTrue(result);
    }

    @Test
    public void phoneValidatorTest() {
        boolean result = phoneValidator("5511982772389", "br");
        assertTrue(result);

        boolean result2 = phoneValidator("5511982772", "br");
        assertFalse(result2);

        boolean result3 = phoneValidator("551187722212", "br");
        assertTrue(result3);
    }

    @Test
    public void cvvValidatorTest() {
        assertFalse(cvvValidator("1111"));
        assertFalse(cvvValidator("a99"));
        assertFalse(cvvValidator("aaa"));
        assertTrue(cvvValidator("999"));
        assertTrue(cvvValidator("123"));
        assertTrue(cvvValidator("765"));
    }

    @Test
    public void cardNumberValidatorTest() {
        assertFalse(cardNumberValidator("xxxx-eeee-1111-zzzz"));
        assertFalse(cardNumberValidator("11e1-1111-1111-1e11"));
        assertTrue(cardNumberValidator("1111-1111-1111-1111"));
        assertTrue(cardNumberValidator("4544-8909-7865-6768"));
    }

}
