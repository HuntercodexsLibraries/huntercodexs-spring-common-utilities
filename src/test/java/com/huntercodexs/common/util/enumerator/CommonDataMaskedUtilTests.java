package com.huntercodexs.common.util.enumerator;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.enumerator.CommonDataMaskedUtil.dataMasked;
import static com.huntercodexs.common.util.formatter.CommonMaskerUtil.cardNumberMasked;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonDataMaskedUtilTests {

    @Test
    public void cardNumberMaskedTest() {
        String mask1 = cardNumberMasked("1234-3456-8982-8908", "*");
        assertEquals("1234-****-****-8908", mask1);

        String mask2 = cardNumberMasked("1234345689828908", "X");
        assertEquals("1234XXXXXXXX8908", mask2);

        String mask3 = cardNumberMasked("1234 3456 8982 8908", "@");
        assertEquals("1234 @@@@ @@@@ 8908", mask3);
    }

    @Test
    public void dataMaskedCardTest() {
        String cardMasked = dataMasked("1234-3456-8982-8908", "*", CommonDataMaskedUtil.CARD_NUMBER_MASK);
        assertEquals("1234-****-****-8908", cardMasked);
    }

    @Test
    public void dataMaskedCpfTest() {
        String cpfMasked = dataMasked("447.359.437-81", "*", CommonDataMaskedUtil.CPF_NUMBER_MASK);
        assertEquals("***.***.437-81", cpfMasked);

        cpfMasked = dataMasked("447.359.437-81", "*", CommonDataMaskedUtil.CPF_NUMBER_DIGIT_MASK);
        assertEquals("***.359.437-**", cpfMasked);
    }

    @Test
    public void dataMaskedCnpjTest() {
        String cnpjMasked = dataMasked("38577435000176", "*", CommonDataMaskedUtil.CNPJ_NUMBER_MASK);
        assertEquals("38*********176", cnpjMasked);

        cnpjMasked = dataMasked("38.577.435/0001-76", "*", CommonDataMaskedUtil.CNPJ_NUMBER_MASK);
        assertEquals("38.***.***/***1-76", cnpjMasked);
    }

    @Test
    public void dataMaskedRgTest() {
        String rgMasked = dataMasked("238764581", "*", CommonDataMaskedUtil.RG_NUMBER_SSPSP_MASK);
        assertEquals("23******1", rgMasked);

        rgMasked = dataMasked("23.876.458-1", "*", CommonDataMaskedUtil.RG_NUMBER_SSPSP_MASK);
        assertEquals("23.***.***-1", rgMasked);
    }

    @Test
    public void dataMaskedEmailTest() {
        String emailMasked = dataMasked("john.wizz@email.com", "*", CommonDataMaskedUtil.EMAIL_ADDRESS_MASK);
        assertEquals("jo*****z@email.com", emailMasked);

        emailMasked = dataMasked("john@email.com", "*", CommonDataMaskedUtil.EMAIL_ADDRESS_MASK);
        assertEquals("jo*****n@email.com", emailMasked);

        emailMasked = dataMasked("john1000@email.com", "*", CommonDataMaskedUtil.EMAIL_ADDRESS_MASK);
        assertEquals("jo*****0@email.com", emailMasked);
    }

    @Test
    public void dataMaskedPhoneTest() {
        String phoneMasked = dataMasked("5512982277653", "*", CommonDataMaskedUtil.PHONE_NUMBER_MASK);
        assertEquals("55129****7653", phoneMasked);

        phoneMasked = dataMasked("982277653", "*", CommonDataMaskedUtil.PHONE_NUMBER_MASK);
        assertEquals("9****7653", phoneMasked);

        phoneMasked = dataMasked("12982277653", "*", CommonDataMaskedUtil.PHONE_NUMBER_MASK);
        assertEquals("129****7653", phoneMasked);

        phoneMasked = dataMasked("82277653", "*", CommonDataMaskedUtil.PHONE_NUMBER_MASK);
        assertEquals("****7653", phoneMasked);
    }

    @Test
    public void dataMaskedGuidTest() {
        String guidMasked = dataMasked("b642fd04-86e2-42e1-9b2c-2ba6d0b383cc", "*", CommonDataMaskedUtil.GUID_MASK);
        assertEquals("b642****-****-****-****-2ba6****83cc", guidMasked);
    }

    @Test
    public void dataMaskedGenericTest() {
        String genericMasked = dataMasked("only a test", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("o*********t", genericMasked);

        genericMasked = dataMasked("82394832948329", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("8************9", genericMasked);

        genericMasked = dataMasked("19/10/1988", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("1********8", genericMasked);

        genericMasked = dataMasked("new", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("n*w", genericMasked);

        genericMasked = dataMasked("InnovationDevs", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("I************s", genericMasked);

        genericMasked = dataMasked("Argument-1099", "*", CommonDataMaskedUtil.GENERIC_MASK);
        assertEquals("A***********9", genericMasked);
    }

}





