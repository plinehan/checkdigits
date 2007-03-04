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
import com.modp.checkdigits.CheckLuhnMod10;

/**
 * Junit test for CheckLuhnMod10
 * 
 * @author nickg
 * @version 1
 *
 */
public class CheckLuhnMod10Test extends CheckCoreTest {

	public CheckDigit getCheckInstance() {
		return new CheckLuhnMod10();
	}
	
	/**
	 * THe Mod10 test fails when a transposition of (0,9) is done
	 * 
	 * @param a
	 * @param b
	 * @return true if it's a bad transposition
	 */
	public boolean badTransposition(char a, char b) {
		return (a == '0' && b == '9') || (a == '9' && b == '0');
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(CheckLuhnMod10Test.class);
	}
}
