package com.huntercodexs.common.util.basic;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.huntercodexs.common.util.basic.CommonStringBasicUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonStringBasicUtilTests {

    @Test
    public void countStringTest() {

        String data = "Topology: Quad Core model: Intel Core i5-9300H bits: 64 type: MT MCP " +
                "arch: Kaby Lake rev: D L2 cache: 8192 KiB flags: avx avx2 lm nx pae sse sse2 sse3 sse4_1 sse4_2 ssse3 vmx " +
                "bogomips: 38400 Speed: 4000 MHz min/max: 800/4100 MHz " +
                "Core speeds (MHz): 1: 4000 2: 4000 3: 4000 4: 4000 5: 4020 6: 4000 7: 4020 8: 4000";

        int result = stringCounter(data, "[1-9]: ([0-9]{4})");
        assertEquals(8, result);

        result = stringCounter(data, "4020");
        assertEquals(2, result);

    }

    @Test
    public void repeatTest() {
        assertEquals("*****", repeat("*", 5));
        assertEquals("++++++++++", repeat("+", 10));
        assertEquals("XYZXYZXYZ", repeat("XYZ", 3));
    }

    @Test
    public void reverseTest() {
        assertEquals("0987654321", reverse("1234567890"));
        assertEquals("JIHGFEDCBA", reverse("ABCDEFGHIJ"));
    }

    @Test
    public void ucFirstTest() {
        String result = ucFirst("name");
        assertEquals("Name", result);

        result = ucFirst("n");
        assertEquals("N", result);

        result = ucFirst("");
        assertEquals("", result);

        result = ucFirst(null);
        assertEquals("", result);

        result = ucFirst("8automatic");
        assertEquals("8automatic", result);
    }

    @Test
    public void queryStringBuilderTest() {
        String result = queryStringBuilder("[ {age: 40, gender: female},{age: 30, gender: female}]");
        assertEquals("age=40&gender=female&age=30&gender=female", result);

        result = queryStringBuilder("[{age: 40, gender: female}]");
        assertEquals("age=40&gender=female", result);

        result = queryStringBuilder("{age: 40, gender: female}");
        assertEquals("age=40&gender=female", result);

        result = queryStringBuilder("{'age': '40', 'gender': 'female'}");
        assertEquals("age=40&gender=female", result);

        result = queryStringBuilder("{\"age\": \"40\", \"gender\": \"female\"}");
        assertEquals("age=40&gender=female", result);
    }

    @Test
    public void getDataFromQueryStringTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        Object result = getDataFromQueryString(queryString, "age");
        assertEquals("40", result.toString());
    }

    @Test
    public void queryStringToJsonTest() {
        String queryString = queryStringBuilder("[{age: 40, gender: female}]");
        JSONObject result = queryStringToJson(queryString);
        assertEquals(
                "{\"gender\":\"female\",\"age\":\"40\"}",
                result.toJSONString());
    }

    @Test
    public void stringToJsonTest() {
        JSONObject result = stringToJson("{\"age\": \"40\", \"gender\": \"female\"}");
        assertEquals(
                "{\"gender\":\"female\",\"age\":\"40\"}",
                result.toJSONString());
    }

    @Test
    public void sanitizeAsciiCaseSensitiveTest() {
        String result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", "upper");
        assertEquals("TESTE COM ACENTUACAO E INEVITAL !", result);

        result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", "lower");
        assertEquals("teste com acentuacao e inevital !", result);

        result = sanitizeAsciiCaseSensitive("Teste com acentuação é inevital !", null);
        assertEquals("Teste com acentuacao e inevital !", result);
    }

    @Test
    public void sanitizeAsciiTest() {
        String result = sanitizeAscii("Teste com acentuação é inevital, pois acontece mesmo.");
        assertEquals("Teste com acentuacao e inevital, pois acontece mesmo.", result);
    }

    @Test
    public void queryExtractorTest() {
        assertEquals("6543", queryExtractor("1234-5678-0000-6543", 15, 19));
    }

    @Test
    public void replaceIndexingTest() {
        CommonStringBasicUtil stringHandler = new CommonStringBasicUtil();

        String result = stringHandler.replaceIndexing(
                "t: testA v: valueA v: valueB t: testB",
                "t: ",
                "type",
                ": ",
                true);

        result = stringHandler.replaceIndexing(
                result,
                "v: ",
                "version",
                ": ",
                true);

        assertEquals("type_0: testA version_2: valueA version_3: valueB type_1: testB", result);
    }

    @Test
    public void stringExtractorTest() {
        String source = "Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz";
        String pattern = "(i[0-9]+|AMD|NVIDIA)([-_.0-9a-zA-Z]+)";
        String replacer = "model:$1$2";
        String clear = "model";
        assertEquals("i5-9300H", stringExtractor(source, clear, pattern, replacer, 1));

        source = "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard";
        clear = "source";
        pattern = "(source: [/-_.0-9a-zA-Z]+)( description: )";
        replacer = "$1";
        assertEquals("/dev/input/event4", stringExtractor(source, clear, pattern, replacer, 1));

        source = "source: /dev/input/event4 description: AT-Translated-Set-2-keyboard";
        clear = "description";
        pattern = "(description: [-_.0-9a-zA-Z]+)";
        replacer = "$1";
        assertEquals("AT-Translated-Set-2-keyboard", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        clear = "description";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "$1";
        assertEquals("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        clear = "DESCRIPTION";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "DESCRIPTION: $1";
        assertEquals("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "SOURCE: SOURCE: LO DESCRIPTION: LOOPBACK-NETWORK-INTERFACE";
        pattern = "DESCRIPTION: (WAN|WLAN|ETHERNET|WIFI|WIRELESS|LOOPBACK|LAN|LO)";
        replacer = "description:$1";
        clear = "description";

        assertEquals("LOOPBACK", stringExtractor(source, clear, pattern, replacer, 1));

        source = "Topology: Quad Core model: Intel Core i5-9300H bits: 64 type: MT MCP arch: Kaby Lake " +
                "rev: D L2 cache: 8192 KiB flags: avx avx2 lm nx pae sse sse2 sse3 sse4_1 sse4_2 ssse3 vmx " +
                "bogomips: 38400 Speed: 4000 MHz min/max: 800/4100 MHz " +
                "Core speeds (MHz): 1: 4000 2: 4000 3: 4000 4: 4000 5: 4000 6: 4000 7: 4000 8: 4000";
        pattern = "([1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4} [1-9]: [0-9]{4})";
        replacer = "$1";
        clear = "";

        assertEquals(
                "1 4000 2 4000 3 4000 4 4000 5 4000 6 4000 7 4000 8 4000",
                stringExtractor(source, clear, pattern, replacer, 1));
    }

    @Test
    public void extractByPatternTest() {
        String input = "Type: Laptop";
        String result = extractByPattern(input, "Type", null);

        assertEquals("Laptop", result);

        input = "Type: \"Laptop\"";
        result = extractByPattern(input, "Type", "\"");

        assertEquals("Laptop", result);

        input = "product: Nitro AN517-51 version: 1.33.3";
        result = extractByPattern(input, "product", " ");

        assertEquals("Nitro AN517-51", result);

        input = "product: Nitro AN517-51 v: 1.33.3";
        result = extractByPattern(input, "v", "");

        assertEquals("1.33.3", result);

        input = "RAM: total: 23.31 GiB used: 6.97 GiB (29.9%) RAM Report: permissions: Unable to run dmidecode. Root privileges required.";
        result = extractByPattern(input, "total", " ");

        assertEquals("23.31 GiB", result);

        result = extractByPattern(input, "used", " ");

        assertEquals("6.97 GiB (29.9%) RAM", result);
    }

    @Test
    public void stringListTest() {
        List<String> list = Arrays. asList(
                "data to remove: Intel(R) Core(TM)",
                "data to remove: Intel(R) Core(TM)",
                "data to remove: Intel(R) Core(TM)"
        );

        String result = stringList(list, "data to remove: ");

        assertEquals("Intel(R) Core(TM),Intel(R) Core(TM),Intel(R) Core(TM)", result);
    }

    @Test
    public void listExtractorTest() {
        List<String> source = Arrays. asList(
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4000 MHz",
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4001 MHz",
            "data to remove: Intel(R) Core(TM) i5-9300H CPU @ 2.40GHz 4002 MHz"
        );

        String pattern = "([0-9]+) (MHz)";
        String replacer = "speedCore:$1 $2";
        String detail = "speedCore";

        String result = listExtractor(source, detail, "data to remove: ", pattern, replacer);

        assertEquals("4000 MHz,4001 MHz,4002 MHz", result);
    }

    @Test
    public void alphaFieldPatternTest() {
        String item1 = "IF: enp7s0 state: up speed: 1000 Mbps duplex: full mac: <filter>";
        String result1 = alphaFieldPattern(item1, "IF", "");
        assertEquals("enp7s0", result1);

        String item2 = "IF-ID-1: br-1222323251ed state: down mac: <filter>";
        String result2 = alphaFieldPattern(item2, "IF-ID-1", "");
        assertEquals("br-1222323251ed", result2);

        String item3 = "IF-ID-1: br-1222323251ed state: down mac: <filter>";
        String result3 = alphaFieldPattern(item3, "IF|IF-ID-1", "");
        assertEquals("br-1222323251ed", result3);
    }

}
