package com.huntercodexs.common.util.data.structure;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.common.util.data.structure.CommonDataStructureUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonDataStructureUtilTests {

    @Test
    public void intVectorTest() {
        int[] result = intVector(1234567890);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
        assertEquals(4, result[3]);
        assertEquals(5, result[4]);
        assertEquals(6, result[5]);
        assertEquals(7, result[6]);
        assertEquals(8, result[7]);
        assertEquals(9, result[8]);
        assertEquals(0, result[9]);
    }

    @Test
    public void charVectorTest() {
        String[] result = charVector("1234567890XYZ[~");
        assertEquals("1", result[0]);
        assertEquals("2", result[1]);
        assertEquals("3", result[2]);
        assertEquals("4", result[3]);
        assertEquals("5", result[4]);
        assertEquals("6", result[5]);
        assertEquals("7", result[6]);
        assertEquals("8", result[7]);
        assertEquals("9", result[8]);
        assertEquals("0", result[9]);
        assertEquals("X", result[10]);
        assertEquals("Y", result[11]);
        assertEquals("Z", result[12]);
        assertEquals("[", result[13]);
        assertEquals("~", result[14]);
    }

    @Test
    public void createIntegerListTest() {
        List<Integer> result = createIntegerList(1234567888);
        assertEquals(1, result.get(0));

        result = createIntegerList("1234567888");
        assertEquals(1, result.get(0));
    }

    @Test
    public void createIntegerHashMapTest() {
        HashMap<Integer, Object> result = createIntegerHashMap(1234567888);
        assertEquals(1, result.get(0));

        result = createIntegerHashMap("1234567888");
        assertEquals(4, result.get(3));
    }

}
