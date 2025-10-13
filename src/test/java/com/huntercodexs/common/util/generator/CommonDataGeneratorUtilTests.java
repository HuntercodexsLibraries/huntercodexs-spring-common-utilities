package com.huntercodexs.common.util.generator;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.basic.CommonsToolsBasicUtil.stdout;
import static com.huntercodexs.common.util.generator.CommonDataGeneratorUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonDataGeneratorUtilTests {

    @Test
    public void randomNumberTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                assertTrue(String.valueOf(randomNumber(1)).matches("^[0-9]{1}$"));
                assertTrue(String.valueOf(randomNumber(2)).matches("^[0-9]{2}$"));
                assertTrue(String.valueOf(randomNumber(3)).matches("^[0-9]{3}$"));
                assertTrue(String.valueOf(randomNumber(4)).matches("^[0-9]{4}$"));
                assertTrue(String.valueOf(randomNumber(5)).matches("^[0-9]{5}$"));
                assertTrue(String.valueOf(randomNumber(6)).matches("^[0-9]{6}$"));
                assertTrue(String.valueOf(randomNumber(7)).matches("^[0-9]{7}$"));
                assertTrue(String.valueOf(randomNumber(8)).matches("^[0-9]{8}$"));
                assertTrue(String.valueOf(randomNumber(9)).matches("^[0-9]{9}$"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomNumberGenerateTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                assertTrue(String.valueOf(randomNumberGenerate(1)).matches("^[0-9]{1}$"));
                assertTrue(String.valueOf(randomNumberGenerate(2)).matches("^[0-9]{2}$"));
                assertTrue(String.valueOf(randomNumberGenerate(3)).matches("^[0-9]{3}$"));
                assertTrue(String.valueOf(randomNumberGenerate(4)).matches("^[0-9]{4}$"));
                assertTrue(String.valueOf(randomNumberGenerate(5)).matches("^[0-9]{5}$"));
                assertTrue(String.valueOf(randomNumberGenerate(6)).matches("^[0-9]{6}$"));
                assertTrue(String.valueOf(randomNumberGenerate(7)).matches("^[0-9]{7}$"));
                assertTrue(String.valueOf(randomNumberGenerate(8)).matches("^[0-9]{8}$"));
                assertTrue(String.valueOf(randomNumberGenerate(9)).matches("^[0-9]{9}$"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomMoneyTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                stdout("REAL R$");
                assertTrue(randomMoney(1, "real").matches("^R\\$ [0-9],00$"));
                assertTrue(randomMoney(2, "real").matches("^R\\$ [0-9]{2},00$"));
                assertTrue(randomMoney(3, "real").matches("^R\\$ [0-9]{3},00$"));
                assertTrue(randomMoney(4, "real").matches("^R\\$ [0-9]\\.[0-9]{3},00$"));
                assertTrue(randomMoney(5, "real").matches("^R\\$ [0-9]{2}\\.[0-9]{3},00$"));
                assertTrue(randomMoney(6, "real").matches("^R\\$ [0-9]{3}\\.[0-9]{3},00$"));
                assertTrue(randomMoney(7, "real").matches("^R\\$ [0-9]\\.[0-9]{3}\\.[0-9]{3},00$"));
                assertTrue(randomMoney(8, "real").matches("^R\\$ [0-9]{2}\\.[0-9]{3}\\.[0-9]{3},00$"));
                assertTrue(randomMoney(9, "real").matches("^R\\$ [0-9]{3}\\.[0-9]{3}\\.[0-9]{3},00$"));
                stdout("DOLLAR $");
                assertTrue(randomMoney(1, "dollar").matches("^\\$ [0-9]\\.00$"));
                assertTrue(randomMoney(2, "dollar").matches("^\\$ [0-9]{2}\\.00$"));
                assertTrue(randomMoney(3, "dollar").matches("^\\$ [0-9]{3}\\.00$"));
                assertTrue(randomMoney(4, "dollar").matches("^\\$ [0-9],[0-9]{3}\\.00$"));
                assertTrue(randomMoney(5, "dollar").matches("^\\$ [0-9]{2},[0-9]{3}\\.00$"));
                assertTrue(randomMoney(6, "dollar").matches("^\\$ [0-9]{3},[0-9]{3}\\.00$"));
                assertTrue(randomMoney(7, "dollar").matches("^\\$ [0-9],[0-9]{3},[0-9]{3}\\.00$"));
                assertTrue(randomMoney(8, "dollar").matches("^\\$ [0-9]{2},[0-9]{3},[0-9]{3}\\.00$"));
                assertTrue(randomMoney(9, "dollar").matches("^\\$ [0-9]{3},[0-9]{3},[0-9]{3}\\.00$"));
                stdout("EURO €");
                assertTrue(randomMoney(1, "euro").matches("^[0-9],00 EUR$"));
                assertTrue(randomMoney(2, "euro").matches("^[0-9]{2},00 EUR$"));
                assertTrue(randomMoney(3, "euro").matches("^[0-9]{3},00 EUR$"));
                assertTrue(randomMoney(4, "euro").matches("^[0-9] [0-9]{3},00 EUR$"));
                assertTrue(randomMoney(5, "euro").matches("^[0-9]{2} [0-9]{3},00 EUR$"));
                assertTrue(randomMoney(6, "euro").matches("^[0-9]{3} [0-9]{3},00 EUR$"));
                assertTrue(randomMoney(7, "euro").matches("^[0-9] [0-9]{3} [0-9]{3},00 EUR$"));
                assertTrue(randomMoney(8, "euro").matches("^[0-9]{2} [0-9]{3} [0-9]{3},00 EUR$"));
                assertTrue(randomMoney(9, "euro").matches("^[0-9]{3} [0-9]{3} [0-9]{3},00 EUR$"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCentsTest() {
        int control = 0;
        while (control < 5) {
            try {
                Thread.sleep(1000);
                assertTrue(randomCents("dollar").matches("^\\$ 0\\.[0-9]{2}$"));
                assertTrue(randomCents("euro").matches("^0,[0-9]{2} EUR$"));
                assertTrue(randomCents( "real").matches("^R\\$ 0,[0-9]{2}$"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCardNumberTest() {
        assertTrue(randomCardNumber(" ").matches("^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$"));
        assertTrue(randomCardNumber(" ").matches("^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$"));
        assertTrue(randomCardNumber(" ").matches("^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$"));
        assertTrue(randomCardNumber(" ").matches("^[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}$"));
    }

    @Test
    public void randomIdTest() {
        assertTrue(randomId("ID_").matches("^ID_[0-9]{6}$"));
        assertTrue(randomId("ID_").matches("^ID_[0-9]{6}$"));
        assertTrue(randomId("ID_").matches("^ID_[0-9]{6}$"));
    }

    @Test
    public void randomGuidTest() {
        assertTrue(randomGuid().matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}"));
    }

    @Test
    public void randomTokenTest() {
        assertEquals(16, randomToken(16).length());
        assertEquals(32, randomToken(32).length());
        assertEquals(64, randomToken(64).length());
        assertEquals(128, randomToken(128).length());
    }

    @Test
    public void randomJwtTest() {
        assertTrue(randomJwt().matches("[0-9a-zA-Z]{36}\\.[0-9a-zA-Z]{136}.[0-9a-zA-Z]{64}"));
    }

    @Test
    public void randomHashTest() {
        assertTrue(randomHash().matches("[0-9a-f]{32}"));
        assertTrue(randomHash().matches("[0-9a-f]{32}"));
        assertTrue(randomHash().matches("[0-9a-f]{32}"));
    }

    @Test
    public void randomBinaryTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                assertTrue(randomBinary(4).matches("[01]{4}"));
                assertTrue(randomBinary(8).matches("[01]{8}"));
                assertTrue(randomBinary(2).matches("[01]{2}"));
                assertTrue(randomBinary(6).matches("[01]{6}"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCpfTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                assertTrue(randomCpf().matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomCnpjTest() {
        int control = 0;
        while (control < 3) {
            try {
                Thread.sleep(1000);
                assertTrue(randomCnpj().matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/0001-[0-9]{2}"));
                control += 1;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void randomDateTest() {
        assertTrue(randomDate().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
    }

    @Test
    public void randomDateTimeTest() {
        assertTrue(randomDateTime().matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"));
    }
}
