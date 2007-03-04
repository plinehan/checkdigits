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
import com.modp.checkdigits.CheckISO7064Mod11_10;

/**
 * Junit test for CheckISO7064Mod11_10
 * 
 * @author nickg
 * @version 1
 */
public class CheckISO7064Mod11_10Test extends CheckCoreTest {
	
	public CheckDigit getCheckInstance() {
		return new CheckISO7064Mod11_10();
	}
	
	public boolean badTransposition(char a, char b) {
		return ((a == '0' && b == '1') || (a =='3' && b == '4'));
	}
	
	/**
	 * This example comes from ISO 7064-1983(E), page X
	 * TODO: page X?
	 */
	public void testExample() {
		CheckDigit cd = new CheckISO7064Mod11_10();
		assertEquals(5, cd.computeCheck("0794"));
		assertTrue(cd.verify("07945"));
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(CheckISO7064Mod11_10Test.class);
	}

}
