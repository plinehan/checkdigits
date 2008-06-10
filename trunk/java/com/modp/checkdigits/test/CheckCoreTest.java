/*
 * Copyright 2005, Nick Galbreath
 * All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software for any purpose
 * with or without fee is hereby granted, provided that the above copyright
 * notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF THIRD PARTY RIGHTS. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Except as contained in this notice, the name of a copyright holder shall not
 * be used in advertising or otherwise to promote the sale, use or other dealings
 * in this Software without prior written authorization of the copyright holder. 
 */
package com.modp.checkdigits.test;

import com.modp.checkdigits.CheckDigit;

import junit.framework.TestCase;

/**
 * Subclass of TestCase to perform common tests for check digits
 * 
 * @author nickg
 * @version 1
 */
public abstract class CheckCoreTest extends TestCase {

	/**
	 * Sample strings to encode.  These only contain '0' to '9'.
	 */
	public static String[] cases = {
			"0000000000",
			"1111111111",
			"2222222222",
			"3333333333",
			"4444444444",
			"5555555555",
			"6666666666",
			"7777777777",
			"8888888888",
			"9999999999",
			"1234567890",
			"0123456789",
			"0246813579",
			"0369147258",
			"9048152637",
			"0516273849"
	};
	
	/**
	 * Return a CheckDigit instance.
	 * 
	 * @return a CheckDigit instance
	 */
	public abstract CheckDigit getCheckInstance();
	
	/**
	 * Test to see if transposition is known to fail.
	 * 
	 * @param a
	 * @param b
	 * @return true, if transposition is known fail; false otherwise.
	 */
	public abstract boolean badTransposition(char a, char b);

	/**
	 * Smoke test for check digit schemes
	 *
	 */
	public void testCheck() {
		CheckDigit check = getCheckInstance();
		for (int i = 0; i < cases.length; ++i) {
			String e = check.encode(cases[i]);
			// make sure it did something
			assertTrue(e.length() > cases[i].length());
			
			// make sure data is correct
			assertEquals(cases[i], check.getData(e));
			
			// make sure it verifies
			assertTrue(check.verify(e));
			

		}
	}
	
	/**
	 * Test for single errors.
	 *
	 */
	public void testSingleError() {
		CheckDigit check = getCheckInstance();
		for (int i = 0; i < cases.length; ++i) {
			// created encoded, convert to char array
			char[] c = check.encode(cases[i]).toCharArray();
			for (int j = 0; j < c.length; ++j) {
				for (int k = 1; k <= 9; ++k) {
					char nc = (char)(((c[j]-'0' + 1) % 10) + '0');
					c[j] = nc;
					assertFalse(check.verify(new String(c)));
				}
				// restore original value
				c[j] =(char)(((c[j]-'0' + 1) % 10) + '0');
			}
		}
	}
	
	/**
	 * Test single transposition errors
	 *
	 * Not all check digit schemes can catch all
	 * transposition errors.
	 */
	public void testAdjacentTraspositions() {
		CheckDigit check = getCheckInstance();
		for (int i = 0; i < cases.length; ++i) {
			String c = check.encode(cases[i]);
			for (int j = 0; j < c.length() - 1; ++j) {
				// transpose at (j, j+1)
				String nc = c.substring(0,j) + 
					c.charAt(j+1) + c.charAt(j)+
						c.substring(j+2);
				// make sure we didn't screw up
				assertEquals(nc.length(), c.length());
				
				// if original input isn't symmetrical, do a test
				if (!(c.equals(nc) || badTransposition(c.charAt(j), c.charAt(j+1)))) {
					if (check.verify(nc)) {
						System.out.println("i=" + i + 
								", j=" + j + ", " + c + ", " + nc);
					}
					assertFalse(check.verify(nc));
				}
			}
		}
	}
	
	/**
	 * Test to make sure algorithms fail where they are
	 * suppose to fail.
	 *
	 */
	public void testBadAdjacentTraspositions() {
		CheckDigit check = getCheckInstance();
		for (int i = 0; i < cases.length; ++i) {
			String c = check.encode(cases[i]);
			for (int j = 0; j < c.length() - 1; ++j) {
				// transpose at (j, j+1)
				String nc = c.substring(0,j) + 
					c.charAt(j+1) + c.charAt(j)+
						c.substring(j+2);
				// make sure we didn't screw up
				assertEquals(nc.length(), c.length());
				
				// if original input isn't symmetrical, do a test
				if (!c.equals(nc) && badTransposition(c.charAt(j), c.charAt(j+1))) {
					if (check.verify(nc)) {
						System.out.println("i=" + i + 
								", j=" + j + ", " + c + ", " + nc);
					}
					assertTrue(check.verify(nc));
				}
			}
		}
	}
	/**
	 * Test to make sure <code>verify</code> doesn't throw an exception.
	 * 
	 * Verify should never throw an exception -- only
	 * true or false.  The idea is that input can come
	 * from "untrusted" sources.  Meaning, bad input is 
	 * expected and "non-exceptional"
	 */
	public void testBadVerify() {
		CheckDigit check = getCheckInstance();
		assertFalse(check.verify(null));
		assertFalse(check.verify(""));
		assertFalse(check.verify("-1"));
		assertFalse(check.verify("_bad_"));
		assertFalse(check.verify("0.345"));
	}
}
