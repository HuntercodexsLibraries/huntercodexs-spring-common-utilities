package com.huntercodexs.common.util.formatter;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.formatter.CommonFormatterUtil.*;
import static com.huntercodexs.common.util.generator.CommonDataGeneratorUtil.randomCnpj;
import static com.huntercodexs.common.util.generator.CommonDataGeneratorUtil.randomCpf;
import static org.junit.jupiter.api.Assertions.*;

class CommonFormatterUtilTests {

    @Test
    public void numberFormatterTest() {
        assertEquals("000000001", numberFormatter(1, "%09d"));
    }

    @Test
    public void stringFormatterTest() {
        assertEquals("       XXX", stringFormatter("XXX", "%10s"));
        assertEquals("XXX       ", stringFormatter("XXX", "%-10s"));
    }

    @Test
    public void fillerFormatterTest() {
        assertEquals("XXXFFFFFFFFFFFFFFFFF", fillerFormatter("XXX", "F", "left", 20));
        assertEquals("FFFFFFFFFFFFFFFFFXXX", fillerFormatter("XXX", "F", "right", 20));
        assertEquals("ZZZ88888888888888888", fillerFormatter("ZZZ", "8", "left", 20));
        assertEquals("88888888888888888ZZZ", fillerFormatter("ZZZ", "8", "right", 20));
        assertNull(fillerFormatter("YYY", "A", "left", -20));
        assertNull(fillerFormatter("YYY", "A", "right", -20));
    }

    @Test
    public void rgFormatterTest() {
        assertEquals("RG231048415RJ", rgFormatter("231048415", "RJ", true));
        assertEquals("RG2310484159MG", rgFormatter("2310484159", "MG", true));
        assertEquals("RG2310484150", rgFormatter("2310484150", "SP", true));
        assertEquals("RG2310484150", rgFormatter("2310484150", "SSPSP", true));
        assertEquals("RG2310484150TO", rgFormatter("2310484150", "SSPTO", true));
        assertEquals("RG2310484150SC", rgFormatter("2310484150", "SSPSC", true));
        assertEquals("RG2310484150", rgFormatter("2310484150", "CNH", true));
        assertEquals("RG2310484150", rgFormatter("2310484150", "DOC", true));
        assertEquals("231048415", rgFormatter("231048415", "RJ", false));
        assertEquals("2310484159", rgFormatter("2310484159", "MG", false));
        assertEquals("2310484150", rgFormatter("2310484150", "SP", false));
        assertEquals("2310484150", rgFormatter("2310484150", "SSPSP", false));
        assertEquals("2310484150", rgFormatter("2310484150", "SSPTO", false));
        assertEquals("2310484150", rgFormatter("2310484150", "SSPSC", false));
        assertEquals("2310484150", rgFormatter("2310484150", "CNH", false));
        assertEquals("2310484150", rgFormatter("2310484150", "DOC", false));
    }

    @Test
    public void cpfFormatterTest() {
        assertTrue(cpfFormatter(randomCpf().replaceAll("[^0-9]", "")).matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"));
    }

    @Test
    public void cnpjFormatterTest() {
        assertTrue(cnpjFormatter(randomCnpj().replaceAll("[^0-9]", "")).matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/0001-[0-9]{2}"));
    }

    @Test
    public void moneyFormatterTest() {
        assertEquals("R$ 1.000,00", moneyFormatter("1000", "real"));
        assertEquals("$ 1,000.00", moneyFormatter("1000", "dollar"));
        assertEquals("1 000,00 €", moneyFormatter("1000", "euro"));
    }

    @Test
    public void dateFormatter_UsingHyphen_Test() {

        //DATETIME + HOUR + MINUTE + SECOND + MILLISECOND

        assertEquals(
                "2020-12-01 10:00:00.003",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss.ms", true));
        assertEquals(
                "2021-09-10 10:00:00.007",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss.ms", true));

        assertEquals(
                "31-08-2022 10:00:00.008",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss.ms", true));
        assertEquals(
                "01-07-2019 10:00:00.009",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss.ms", true));

        assertEquals(
                "01-04-15 10:00:00.010",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss.ms", true));
        assertEquals(
                "90-06-23 10:00:00.011",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss.ms", true));

        //DATE + HOUR + MINUTE + SECOND

        assertEquals(
                "2020-12-01 10:00:00",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm:ss", true));
        assertEquals(
                "2021-09-10 10:00:00",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm:ss", true));

        assertEquals(
                "31-08-2022 10:00:00",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm:ss", true));
        assertEquals(
                "01-07-2019 10:00:00",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm:ss", true));

        assertEquals(
                "01-04-15 10:00:00",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm:ss", true));
        assertEquals(
                "90-06-23 10:00:00",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm:ss", true));

        //DATE + HOUR + MINUTE

        assertEquals(
                "2020-12-01 10:00",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH:mm", true));
        assertEquals(
                "2021-09-10 10:00",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH:mm", true));

        assertEquals(
                "31-08-2022 10:00",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH:mm", true));
        assertEquals(
                "01-07-2019 10:00",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH:mm", true));

        assertEquals(
                "01-04-15 10:00",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH:mm", true));
        assertEquals(
                "90-06-23 10:00",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH:mm", true));

        //DATE + HOUR

        assertEquals(
                "2020-12-01 10",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd HH", true));
        assertEquals(
                "2021-09-10 10",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd HH", true));

        assertEquals(
                "31-08-2022 10",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy HH", true));
        assertEquals(
                "01-07-2019 10",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy HH", true));

        assertEquals(
                "01-04-15 10",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy HH", true));
        assertEquals(
                "90-06-23 10",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd HH", true));

        //ONLY DATE

        assertEquals(
                "2020-12-01",
                dateFormatter("2020/12/01 10:00:00.003", "yyy-MM-dd", true));
        assertEquals(
                "2021-09-10",
                dateFormatter("2021/09/10 10:00:00.007", "yyyy-MM-dd", true));

        assertEquals(
                "31-08-2022",
                dateFormatter("2022/08/31 10:00:00.008", "dd-MM-yyy", true));
        assertEquals(
                "01-07-2019",
                dateFormatter("2019/07/01 10:00:00.009", "dd-MM-yyyy", true));

        assertEquals(
                "01-04-15",
                dateFormatter("2015/04/01 10:00:00.010", "dd-MM-yy", true));
        assertEquals(
                "90-06-23",
                dateFormatter("1990/06/23 10:00:00.011", "yy-MM-dd", true));

        //GMT

        assertEquals(
                "2020-12-01T10:00:00.003Z",
                dateFormatter("2020/12/01T10:00:00.003Z", "yyy-MM-dd HH:mm:ss.ms", true));
        assertEquals(
                "2021-09-10T10:00:00.007Z",
                dateFormatter("2021/09/10T10:00:00.007Z", "yyyy-MM-dd HH:mm:ss.ms", true));

        assertEquals(
                "31-08-2022T10:00:00.008Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms", true));
        assertEquals(
                "01-07-2019T10:00:00.009Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms", true));

        assertEquals(
                "31-08-2022T10:00:00.008Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss.ms", true));
        assertEquals(
                "01-07-2019T10:00:00.009Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss.ms", true));

        assertEquals(
                "31-08-2022T10:00:00Z",
                dateFormatter("2022/08/31T10:00:00.008Z", "dd-MM-yyy HH:mm:ss", true));
        assertEquals(
                "01-07-2019T10:00:00Z",
                dateFormatter("2019/07/01T10:00:00.009Z", "dd-MM-yyyy HH:mm:ss", true));

        assertEquals(
                "01-04-15T10:00:00Z",
                dateFormatter("2015/04/01T10:00:00.010Z", "dd-MM-yy HH:mm:ss", true));
        assertEquals(
                "90-06-23T10:00:00Z",
                dateFormatter("1990/06/23T10:00:00.011Z", "yy-MM-dd HH:mm:ss", true));

        //ONLY NUMBERS

        assertEquals(
                "20201201100000003",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmmssms", true));
        assertEquals(
                "20210910100000007",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmmssms", true));

        assertEquals(
                "31082022100000008",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmmssms", true));
        assertEquals(
                "01072019100000009",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmmssms", true));

        assertEquals(
                "010415100000010",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmmssms", true));
        assertEquals(
                "900623100000011",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmmssms", true));

        assertEquals(
                "20201201100000",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmmss", true));
        assertEquals(
                "20210910100000",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmmss", true));

        assertEquals(
                "31082022100000",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmmss", true));
        assertEquals(
                "01072019100000",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmmss", true));

        assertEquals(
                "010415100000",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmmss", true));
        assertEquals(
                "900623100000",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmmss", true));

        assertEquals(
                "202012011000",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHHmm", true));
        assertEquals(
                "202109101000",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHHmm", true));

        assertEquals(
                "310820221000",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHHmm", true));
        assertEquals(
                "010720191000",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHHmm", true));

        assertEquals(
                "0104151000",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHHmm", true));
        assertEquals(
                "9006231000",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHHmm", true));

        assertEquals(
                "2020120110",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMddHH", true));
        assertEquals(
                "2021091010",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMddHH", true));

        assertEquals(
                "3108202210",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyyHH", true));
        assertEquals(
                "0107201910",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyyHH", true));

        assertEquals(
                "01041510",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyyHH", true));
        assertEquals(
                "90062310",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMddHH", true));

        assertEquals(
                "20201201",
                dateFormatter("2020/12/01 10:00:00.003", "yyyMMdd", true));
        assertEquals(
                "20210910",
                dateFormatter("2021/09/10 10:00:00.007", "yyyyMMdd", true));

        assertEquals(
                "31082022",
                dateFormatter("2022/08/31 10:00:00.008", "ddMMyyy", true));
        assertEquals(
                "01072019",
                dateFormatter("2019/07/01 10:00:00.009", "ddMMyyyy", true));

        assertEquals(
                "010415",
                dateFormatter("2015/04/01 10:00:00.010", "ddMMyy", true));
        assertEquals(
                "900623",
                dateFormatter("1990/06/23 10:00:00.011", "yyMMdd", true));
    }

    @Test
    public void dateFormatter_UsingBar_Test() {

        //DATETIME + HOUR + MINUTE + SECOND + MILLISECOND

        assertEquals(
                "2020/12/01 10:00:00.003",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm:ss.ms", true));
        assertEquals(
                "2021/09/10 10:00:00.007",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm:ss.ms", true));

        assertEquals(
                "31/08/2022 10:00:00.008",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm:ss.ms", true));
        assertEquals(
                "01/07/2019 10:00:00.009",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm:ss.ms", true));

        assertEquals(
                "01/04/15 10:00:00.010",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm:ss.ms", true));
        assertEquals(
                "90/06/23 10:00:00.011",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm:ss.ms", true));

        //DATE + HOUR + MINUTE + SECOND

        assertEquals(
                "2020/12/01 10:00:00",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm:ss", true));
        assertEquals(
                "2021/09/10 10:00:00",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm:ss", true));

        assertEquals(
                "31/08/2022 10:00:00",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm:ss", true));
        assertEquals(
                "01/07/2019 10:00:00",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm:ss", true));

        assertEquals(
                "01/04/15 10:00:00",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm:ss", true));
        assertEquals(
                "90/06/23 10:00:00",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm:ss", true));

        //DATE + HOUR + MINUTE

        assertEquals(
                "2020/12/01 10:00",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH:mm", true));
        assertEquals(
                "2021/09/10 10:00",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH:mm", true));

        assertEquals(
                "31/08/2022 10:00",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH:mm", true));
        assertEquals(
                "01/07/2019 10:00",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH:mm", true));

        assertEquals(
                "01/04/15 10:00",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH:mm", true));
        assertEquals(
                "90/06/23 10:00",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH:mm", true));

        //DATE + HOUR

        assertEquals(
                "2020/12/01 10",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd HH", true));
        assertEquals(
                "2021/09/10 10",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd HH", true));

        assertEquals(
                "31/08/2022 10",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy HH", true));
        assertEquals(
                "01/07/2019 10",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy HH", true));

        assertEquals(
                "01/04/15 10",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy HH", true));
        assertEquals(
                "90/06/23 10",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd HH", true));

        //ONLY DATE

        assertEquals(
                "2020/12/01",
                dateFormatter("2020-12-01 10:00:00.003", "yyy/MM/dd", true));
        assertEquals(
                "2021/09/10",
                dateFormatter("2021-09-10 10:00:00.007", "yyyy/MM/dd", true));

        assertEquals(
                "31/08/2022",
                dateFormatter("2022-08-31 10:00:00.008", "dd/MM/yyy", true));
        assertEquals(
                "01/07/2019",
                dateFormatter("2019-07-01 10:00:00.009", "dd/MM/yyyy", true));

        assertEquals(
                "01/04/15",
                dateFormatter("2015-04-01 10:00:00.010", "dd/MM/yy", true));
        assertEquals(
                "90/06/23",
                dateFormatter("1990-06-23 10:00:00.011", "yy/MM/dd", true));

        //GMT

        assertEquals(
                "2020/12/01T10:00:00.003Z",
                dateFormatter("2020-12-01T10:00:00.003Z", "yyy/MM/dd HH:mm:ss.ms", true));
        assertEquals(
                "2021/09/10T10:00:00.007Z",
                dateFormatter("2021-09-10T10:00:00.007Z", "yyyy/MM/dd HH:mm:ss.ms", true));

        assertEquals(
                "31/08/2022T10:00:00.008Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss.ms", true));
        assertEquals(
                "01/07/2019T10:00:00.009Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss.ms", true));

        assertEquals(
                "31/08/2022T10:00:00.008Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss.ms", true));
        assertEquals(
                "01/07/2019T10:00:00.009Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss.ms", true));

        assertEquals(
                "31/08/2022T10:00:00Z",
                dateFormatter("2022-08-31T10:00:00.008Z", "dd/MM/yyy HH:mm:ss", true));
        assertEquals(
                "01/07/2019T10:00:00Z",
                dateFormatter("2019-07-01T10:00:00.009Z", "dd/MM/yyyy HH:mm:ss", true));

        assertEquals(
                "01/04/15T10:00:00Z",
                dateFormatter("2015-04-01T10:00:00.010Z", "dd/MM/yy HH:mm:ss", true));
        assertEquals(
                "90/06/23T10:00:00Z",
                dateFormatter("1990-06-23T10:00:00.011Z", "yy/MM/dd HH:mm:ss", true));

        //ONLY NUMBERS

        assertEquals(
                "20201201100000003",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmmssms", true));
        assertEquals(
                "20210910100000007",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmmssms", true));

        assertEquals(
                "31082022100000008",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmmssms", true));
        assertEquals(
                "01072019100000009",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmmssms", true));

        assertEquals(
                "010415100000010",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmmssms", true));
        assertEquals(
                "900623100000011",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmmssms", true));

        assertEquals(
                "20201201100000",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmmss", true));
        assertEquals(
                "20210910100000",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmmss", true));

        assertEquals(
                "31082022100000",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmmss", true));
        assertEquals(
                "01072019100000",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmmss", true));

        assertEquals(
                "010415100000",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmmss", true));
        assertEquals(
                "900623100000",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmmss", true));

        assertEquals(
                "202012011000",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHHmm", true));
        assertEquals(
                "202109101000",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHHmm", true));

        assertEquals(
                "310820221000",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHHmm", true));
        assertEquals(
                "010720191000",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHHmm", true));

        assertEquals(
                "0104151000",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHHmm", true));
        assertEquals(
                "9006231000",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHHmm", true));

        assertEquals(
                "2020120110",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMddHH", true));
        assertEquals(
                "2021091010",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMddHH", true));

        assertEquals(
                "3108202210",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyyHH", true));
        assertEquals(
                "0107201910",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyyHH", true));

        assertEquals(
                "01041510",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyyHH", true));
        assertEquals(
                "90062310",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMddHH", true));

        assertEquals(
                "20201201",
                dateFormatter("2020-12-01 10:00:00.003", "yyyMMdd", true));
        assertEquals(
                "20210910",
                dateFormatter("2021-09-10 10:00:00.007", "yyyyMMdd", true));

        assertEquals(
                "31082022",
                dateFormatter("2022-08-31 10:00:00.008", "ddMMyyy", true));
        assertEquals(
                "01072019",
                dateFormatter("2019-07-01 10:00:00.009", "ddMMyyyy", true));

        assertEquals(
                "010415",
                dateFormatter("2015-04-01 10:00:00.010", "ddMMyy", true));
        assertEquals(
                "900623",
                dateFormatter("1990-06-23 10:00:00.011", "yyMMdd", true));
    }

}