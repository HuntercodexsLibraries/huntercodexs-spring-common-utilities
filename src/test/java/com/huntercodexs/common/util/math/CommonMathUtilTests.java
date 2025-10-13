package com.huntercodexs.common.util.math;

import org.junit.jupiter.api.Test;

import static com.huntercodexs.common.util.math.CommonMathUtil.binaryToDecimal;
import static com.huntercodexs.common.util.math.CommonMathUtil.decimalToBinary;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonMathUtilTests {

    @Test
    public void decimalToBinaryTest() {
        assertEquals("0", decimalToBinary(0));
        assertEquals("1", decimalToBinary(1));
        assertEquals("10", decimalToBinary(2));
        assertEquals("11", decimalToBinary(3));
        assertEquals("100", decimalToBinary(4));
        assertEquals("101", decimalToBinary(5));
        assertEquals("110", decimalToBinary(6));
        assertEquals("111", decimalToBinary(7));
        assertEquals("1000", decimalToBinary(8));
        assertEquals("1001", decimalToBinary(9));
        assertEquals("1010", decimalToBinary(10));
        assertEquals("1011", decimalToBinary(11));
        assertEquals("1100", decimalToBinary(12));
        assertEquals("1100100", decimalToBinary(100));
    }

    @Test
    public void binaryToDecimalTest() {
        assertEquals("0", String.valueOf(binaryToDecimal("0")));
        assertEquals("1", String.valueOf(binaryToDecimal("1")));
        assertEquals("2", String.valueOf(binaryToDecimal("10")));
        assertEquals("3", String.valueOf(binaryToDecimal("11")));
        assertEquals("4", String.valueOf(binaryToDecimal("100")));
        assertEquals("5", String.valueOf(binaryToDecimal("101")));
        assertEquals("6", String.valueOf(binaryToDecimal("110")));
        assertEquals("7", String.valueOf(binaryToDecimal("111")));
        assertEquals("8", String.valueOf(binaryToDecimal("1000")));
        assertEquals("9", String.valueOf(binaryToDecimal("1001")));
        assertEquals("10", String.valueOf(binaryToDecimal("1010")));
        assertEquals("11", String.valueOf(binaryToDecimal("1011")));
        assertEquals("12", String.valueOf(binaryToDecimal("1100")));
        assertEquals("100", String.valueOf(binaryToDecimal("1100100")));
        assertEquals("32", String.valueOf(binaryToDecimal("100000")));
        assertEquals("64", String.valueOf(binaryToDecimal("1000000")));
        assertEquals("128", String.valueOf(binaryToDecimal("10000000")));
        assertEquals("256", String.valueOf(binaryToDecimal("100000000")));
    }

}





