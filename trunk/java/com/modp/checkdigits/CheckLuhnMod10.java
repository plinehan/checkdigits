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

package com.modp.checkdigits;

/**
 * Implement the Luhn Formula Mod 10 check digit scheme
 * 
 * @author nickg
 * @version 1
 */
public class CheckLuhnMod10 implements CheckDigit {

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#encode(java.lang.String)
	 */
	public String encode(String digits) {
		return digits + computeCheck(digits);
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#verify(java.lang.String)
	 */
	public boolean verify(String digits) {
		try {
			return ((computeSum(digits) % 10) == 0);
		} catch (Exception e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#computeCheck(java.lang.String)
	 */
	public int computeCheck(String digits) {
		int val = computeSum(digits);
		return (val == 0) ? 0 : (10 - val);
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getCheckDigit(java.lang.String)
	 */
	public int getCheckDigit(String digits) {
		return digits.charAt(digits.length() - 1) - '0';
	}

	/* (non-Javadoc)
	 * @see com.modp.checkdigit.CheckDigit#getData(java.lang.String)
	 */
	public String getData(String digits) {
		return digits.substring(0, digits.length() - 1);
	}

	// computes the special sum function
	private int computeSum(String digits) {
		int val = 0;
		// do 'even' parts
		for (int i = 0; i < digits.length(); i += 2) {
			int c = digits.charAt(i) - '0';
			if (c < 0 || c > 9) {
				throw new NumberFormatException("Bad digit: '"
						+ digits.charAt(i) + "'");
			}
			val += c;
		}

		for (int i = 1; i < digits.length(); i += 2) {
			int c = digits.charAt(i) - '0';
			if (c < 0 || c > 9) {
				throw new NumberFormatException("Bad digit: '"
						+ digits.charAt(i) + "'");
			}
			val += (c >= 5) ? (2*c - 9) : (2*c);
		}
		return val % 10;
	}
}
