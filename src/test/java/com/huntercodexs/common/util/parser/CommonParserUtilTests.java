package com.huntercodexs.common.util.parser;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommonParserUtilTests {

    static class Person {
        public String name;
        public int age;

        public Person() { //Jackson requires
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    void testConvertValue_Success() {
        Map<String, Object> source = Map.of("name", "Laura", "age", 10);

        Person result = CommonParserUtil.convertValue(source, Person.class);

        assertNotNull(result);
        assertEquals("Laura", result.name);
        assertEquals(10, result.age);
    }

    @Test
    void testConvertValue_NullSource() {
        Person result = CommonParserUtil.convertValue(null, Person.class);
        assertNull(result);
    }

    @Test
    void testConvertValues_Success() {
        List<Map<String, Object>> sourceList = List.of(
                Map.of("name", "Alice", "age", 30),
                Map.of("name", "Bob", "age", 40)
        );

        List<Person> result = CommonParserUtil.convertValues(sourceList, Person.class);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).name);
        assertEquals(40, result.get(1).age);
    }

    @Test
    void testConvertValues_NullSource() {
        List<Person> result = CommonParserUtil.convertValues(null, Person.class);
        assertNull(result);
    }

    @Test
    void testConvertToJSON_Success() {
        Person person = new Person("Isaac", 7);

        String json = CommonParserUtil.convertToJSON(person);

        assertTrue(json.contains("\"name\":\"Isaac\""));
        assertTrue(json.contains("\"age\":7"));
    }

    @Test
    void testConvertToJSON_NullObject() {
        String json = CommonParserUtil.convertToJSON(null);
        assertEquals("null", json);
    }

    @Test
    void testConvertJSONToObject_Success() {
        String json = "{\"name\":\"Carolina\",\"age\":35}";

        Person person = CommonParserUtil.convertJSONToObject(json, Person.class);

        assertNotNull(person);
        assertEquals("Carolina", person.name);
        assertEquals(35, person.age);
    }

    @Test
    void testConvertJSONToObject_InvalidJson_ThrowsException() {
        String invalidJson = "{name: }"; // Invalid JSON

        assertThrows(IllegalArgumentException.class, () ->
                CommonParserUtil.convertJSONToObject(invalidJson, Person.class)
        );
    }
}






