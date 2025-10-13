package com.huntercodexs.common.util.currency;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.ParseException;

import static com.huntercodexs.common.util.currency.CommonCurrencyUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonCurrencyUtilTests {

    @Test
    public void brCurrencyFloatTest() {
        System.out.println(brCurrency(Float.parseFloat("999111111111.00")));
    }

    @Test
    public void brCurrencyDoubleTest() {
        System.out.println(brCurrency(999111111111.00));
    }

    @Test
    public void currencySumTest() {

        double result = currencySum(0.00, 0.01);
        result += currencySum(0.01, 0.10);
        result += currencySum(0.10, 0.11);
        result += currencySum(0.11, 1.11);
        result += currencySum(1.00, 1.01);
        result += currencySum(1.00, 1.10);
        result += currencySum(11.00, 111.10);
        result += currencySum(1111.00, 11.10);
        result += currencySum(111111.00, 111.10);
        result += currencySum(111.00, 11.01);
        result += currencySum(111111111.00, 11.01);
        result += currencySum(999111111111.00, 11.01);

        System.out.println("Total");
        System.out.println(brCurrency(result));

        /*Proof*/
        double proff = currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        assertEquals("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void currencySumFromStringTest() {

        /*Proof*/
        double proff = currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        assertEquals("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void doubleTest() {
        System.out.println(Double.parseDouble("123.45001"));
        System.out.println(Double.parseDouble("123.45001d"));
        System.out.println(Double.parseDouble("123.45000"));
        System.out.println(Double.parseDouble("123.45001D"));

        System.out.println(Double.valueOf("123.45d"));
        System.out.println(Double.valueOf("123.4500d"));
        System.out.println(Double.valueOf("123.45D"));

        System.out.println(Double.valueOf("12345"));
    }

    @Test
    public void decimalFormatTest () throws ParseException {
        String str1 = "1.000.000.000,00";
        double resulted1 = DecimalFormat.getNumberInstance().parse(str1).doubleValue();
        System.out.println(brCurrency(resulted1));

        String str2 = "1,000,000,000.00";
        double resulted2 = DecimalFormat.getNumberInstance().parse(str2
                .replaceAll(",", "")
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted2));

        String str3 = "1000000000,00";
        double resulted3 = DecimalFormat.getNumberInstance().parse(str3).doubleValue();
        System.out.println(brCurrency(resulted3));

        String str4 = "1000000000.00";
        double resulted4 = DecimalFormat.getNumberInstance().parse(str4
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted4));
    }

    @Test
    public void currencyFormatRealTest() {
        assertEquals("R$ 1,00", currencyFormatReal(1));
        assertEquals("R$ 10,00", currencyFormatReal(10));
        assertEquals("R$ 100,00", currencyFormatReal(100));
        assertEquals("R$ 1.000,00", currencyFormatReal(1000));
        assertEquals("R$ 10.000,00", currencyFormatReal(10000));
        assertEquals("R$ 100.000,00", currencyFormatReal(100000));
        assertEquals("R$ 1.000.000,00", currencyFormatReal(1000000));
        assertEquals("R$ 10.000.000,00", currencyFormatReal(10000000));
        assertEquals("R$ 100.000.000,00", currencyFormatReal(100000000));
        assertEquals("R$ 1.000.000.000,00", currencyFormatReal(1000000000));

        assertEquals("R$ 1,00", currencyFormatReal("1"));
        assertEquals("R$ 10,00", currencyFormatReal("10"));
        assertEquals("R$ 100,00", currencyFormatReal("100"));
        assertEquals("R$ 1.000,00", currencyFormatReal("1000"));
        assertEquals("R$ 10.000,00", currencyFormatReal("10000"));
        assertEquals("R$ 100.000,00", currencyFormatReal("100000"));
        assertEquals("R$ 1.000.000,00", currencyFormatReal("1000000"));
        assertEquals("R$ 10.000.000,00", currencyFormatReal("10000000"));
        assertEquals("R$ 100.000.000,00", currencyFormatReal("100000000"));
        assertEquals("R$ 1.000.000.000,00", currencyFormatReal("1000000000"));
    }

    @Test
    public void currencyFormatDollarTest() {
        assertEquals("$ 1.00", currencyFormatDollar(1));
        assertEquals("$ 10.00", currencyFormatDollar(10));
        assertEquals("$ 100.00", currencyFormatDollar(100));
        assertEquals("$ 1,000.00", currencyFormatDollar(1000));
        assertEquals("$ 10,000.00", currencyFormatDollar(10000));
        assertEquals("$ 100,000.00", currencyFormatDollar(100000));
        assertEquals("$ 1,000,000.00", currencyFormatDollar(1000000));
        assertEquals("$ 10,000,000.00", currencyFormatDollar(10000000));
        assertEquals("$ 100,000,000.00", currencyFormatDollar(100000000));
        assertEquals("$ 1,000,000,000.00", currencyFormatDollar(1000000000));

        assertEquals("$ 1.00", currencyFormatDollar("1"));
        assertEquals("$ 10.00", currencyFormatDollar("10"));
        assertEquals("$ 100.00", currencyFormatDollar("100"));
        assertEquals("$ 1,000.00", currencyFormatDollar("1000"));
        assertEquals("$ 10,000.00", currencyFormatDollar("10000"));
        assertEquals("$ 100,000.00", currencyFormatDollar("100000"));
        assertEquals("$ 1,000,000.00", currencyFormatDollar("1000000"));
        assertEquals("$ 10,000,000.00", currencyFormatDollar("10000000"));
        assertEquals("$ 100,000,000.00", currencyFormatDollar("100000000"));
        assertEquals("$ 1,000,000,000.00", currencyFormatDollar("1000000000"));
    }

    @Test
    public void currencyFormatEuroTest() {
        assertEquals("1,00 EUR", currencyFormatEuro(1, false));
        assertEquals("10,00 EUR", currencyFormatEuro(10, false));
        assertEquals("100,00 EUR", currencyFormatEuro(100, false));
        assertEquals("1 000,00 EUR", currencyFormatEuro(1000, false));
        assertEquals("10 000,00 EUR", currencyFormatEuro(10000, false));
        assertEquals("100 000,00 EUR", currencyFormatEuro(100000, false));
        assertEquals("1 000 000,00 EUR", currencyFormatEuro(1000000, false));
        assertEquals("10 000 000,00 EUR", currencyFormatEuro(10000000, false));
        assertEquals("100 000 000,00 EUR", currencyFormatEuro(100000000, false));
        assertEquals("1 000 000 000,00 EUR", currencyFormatEuro(1000000000, false));

        assertEquals("1,00 EUR", currencyFormatEuro("1", false));
        assertEquals("10,00 EUR", currencyFormatEuro("10", false));
        assertEquals("100,00 EUR", currencyFormatEuro("100", false));
        assertEquals("1 000,00 EUR", currencyFormatEuro("1000", false));
        assertEquals("10 000,00 EUR", currencyFormatEuro("10000", false));
        assertEquals("100 000,00 EUR", currencyFormatEuro("100000", false));
        assertEquals("1 000 000,00 EUR", currencyFormatEuro("1000000", false));
        assertEquals("10 000 000,00 EUR", currencyFormatEuro("10000000", false));
        assertEquals("100 000 000,00 EUR", currencyFormatEuro("100000000", false));
        assertEquals("1 000 000 000,00 EUR", currencyFormatEuro("1000000000", false));

        assertEquals("1,00 €", currencyFormatEuro(1, true));
        assertEquals("10,00 €", currencyFormatEuro(10, true));
        assertEquals("100,00 €", currencyFormatEuro(100, true));
        assertEquals("1 000,00 €", currencyFormatEuro(1000, true));
        assertEquals("10 000,00 €", currencyFormatEuro(10000, true));
        assertEquals("100 000,00 €", currencyFormatEuro(100000, true));
        assertEquals("1 000 000,00 €", currencyFormatEuro(1000000, true));
        assertEquals("10 000 000,00 €", currencyFormatEuro(10000000, true));
        assertEquals("100 000 000,00 €", currencyFormatEuro(100000000, true));
        assertEquals("1 000 000 000,00 €", currencyFormatEuro(1000000000, true));

        assertEquals("1,00 €", currencyFormatEuro("1", true));
        assertEquals("10,00 €", currencyFormatEuro("10", true));
        assertEquals("100,00 €", currencyFormatEuro("100", true));
        assertEquals("1 000,00 €", currencyFormatEuro("1000", true));
        assertEquals("10 000,00 €", currencyFormatEuro("10000", true));
        assertEquals("100 000,00 €", currencyFormatEuro("100000", true));
        assertEquals("1 000 000,00 €", currencyFormatEuro("1000000", true));
        assertEquals("10 000 000,00 €", currencyFormatEuro("10000000", true));
        assertEquals("100 000 000,00 €", currencyFormatEuro("100000000", true));
        assertEquals("1 000 000 000,00 €", currencyFormatEuro("1000000000", true));
    }

    @Test
    public void convertToCentsTest() {
        assertEquals(34, convertToCents("R$ 0,34"));
        assertEquals(500, convertToCents("R$ 5,00"));
        assertEquals(1100, convertToCents("R$ 11,00"));
        assertEquals(19200, convertToCents("R$ 192,00"));
        assertEquals(219200, convertToCents("R$ 2192,00"));
        assertEquals(8219200, convertToCents("R$ 82.192,00"));
    }

}
