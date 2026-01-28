package com.ontariotechu.sofe3980U;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

/**
 * Unit test for Binary class.
 */
public class BinaryTest 
{
    /**
     * Test The constructor with a valid binary vallue
     */
    @Test
    public void normalConstructor()
    {
		Binary binary=new Binary("1001001");
        assertTrue( binary.getValue().equals("1001001"));
    }
    /**
     * Test The constructor with an invalid binary value of out-of-range digits
     */
    @Test
    public void constructorWithInvalidDigits()
    {
		Binary binary=new Binary("1001001211");
        assertTrue( binary.getValue().equals("0"));
    }
    /**
     * Test The constructor with an invalid binary value of alphabetic characters
     */
    @Test
    public void constructorWithInvalidChars()
    {
		Binary binary=new Binary("1001001A");
        assertTrue( binary.getValue().equals("0"));
    }
    /**
     * Test The constructor with an invalid binary value that has a sign
     */
    @Test
    public void constructorWithNegativeSign()
    {
		Binary binary=new Binary("-1001001");
        assertTrue( binary.getValue().equals("0"));
    }
    /**
     * Test The constructor with a zero tailing valid binary value
     */
    @Test
    public void constructorWithZeroTailing()
    {
		Binary binary=new Binary("00001001");
        assertTrue( binary.getValue().equals("1001"));
    }
    /**
     * Test The constructor with an empty string
     */
    @Test
    public void constructorEmptyString()
    {
		Binary binary=new Binary("");
        assertTrue( binary.getValue().equals("0"));
    }
	/**
     * Test The add functions with two binary numbers of the same length
     */
    @Test
    public void add()
    {
		Binary binary1=new Binary("1000");
		Binary binary2=new Binary("1111");
		Binary binary3=Binary.add(binary1,binary2);
        assertTrue( binary3.getValue().equals("10111"));
    }
	/**
     * Test The add functions with two binary numbers, the length of the first argument is less than the second
     */
    @Test
    public void add2()
    {
		Binary binary1=new Binary("1010");
		Binary binary2=new Binary("11");
		Binary binary3=Binary.add(binary1,binary2);
        assertTrue( binary3.getValue().equals("1101"));
    }
	/**
     * Test The add functions with two binary numbers, the length of the first argument is greater than the second
     */
    @Test
    public void add3()
    {
		Binary binary1=new Binary("11");
		Binary binary2=new Binary("1010");
		Binary binary3=Binary.add(binary1,binary2);
        assertTrue( binary3.getValue().equals("1101"));
    }
	/**
     * Test The add functions with a binary numbers with zero
     */
    @Test
    public void add4()
    {
		Binary binary1=new Binary("0");
		Binary binary2=new Binary("1010");
		Binary binary3=Binary.add(binary1,binary2);
        assertTrue( binary3.getValue().equals("1010"));
    }
	/**
     * Test The add functions with two zeros
     */
    @Test
    public void add5()
    {
		Binary binary1=new Binary("0");
		Binary binary2=new Binary("0");
		Binary binary3=Binary.add(binary1,binary2);
        assertTrue( binary3.getValue().equals("0"));
    }

        // ---------------- OR tests (3+) ----------------

    @Test
    public void or1_basic() {
        Binary a = new Binary("1010");
        Binary b = new Binary("1100");
        assertEquals("1110", Binary.or(a, b).getValue());
    }

    @Test
    public void or2_withZero() {
        Binary a = new Binary("0");
        Binary b = new Binary("10101");
        assertEquals("10101", Binary.or(a, b).getValue());
    }

    @Test
    public void or3_differentLengths() {
        Binary a = new Binary("1");
        Binary b = new Binary("1000");
        assertEquals("1001", Binary.or(a, b).getValue());
    }

    // ---------------- AND tests (3+) ----------------

    @Test
    public void and1_basic() {
        Binary a = new Binary("1010");
        Binary b = new Binary("1100");
        assertEquals("1000", Binary.and(a, b).getValue());
    }

    @Test
    public void and2_withZero() {
        Binary a = new Binary("0");
        Binary b = new Binary("111");
        assertEquals("0", Binary.and(a, b).getValue());
    }

    @Test
    public void and3_differentLengths() {
        Binary a = new Binary("1");
        Binary b = new Binary("1000");
        assertEquals("0", Binary.and(a, b).getValue());
    }

    // ---------------- Multiply tests (3+) ----------------

    @Test
    public void multiply1_basic() {
        // 101 (5) * 11 (3) = 1111 (15)
        Binary a = new Binary("101");
        Binary b = new Binary("11");
        assertEquals("1111", Binary.multiply(a, b).getValue());
    }

    @Test
    public void multiply2_byZero() {
        Binary a = new Binary("0");
        Binary b = new Binary("10101");
        assertEquals("0", Binary.multiply(a, b).getValue());
    }

    @Test
    public void multiply3_powerOfTwo() {
        // 1000 (8) * 10 (2) = 10000 (16)
        Binary a = new Binary("1000");
        Binary b = new Binary("10");
        assertEquals("10000", Binary.multiply(a, b).getValue());
    }

}
