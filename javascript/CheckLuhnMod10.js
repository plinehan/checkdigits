/* CheckLuhnMod10.js Version 1.0.0  24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */

function CheckLuhnMod10() {};

CheckLuhnMod10.encode = function (digits) {
  return digits + this.computeCheck(digits);
}

CheckLuhnMod10.verify = function (digits) {
  try {
    return (this.computeSum(digits) == 0);
  } catch (e) {
    return false;
  }
}

CheckLuhnMod10.computeCheck = function (digits) {
  var val = this.computeSum(digits);
  return (val == 0) ? 0 : (10 - val);
}

CheckLuhnMod10.getData = function (digits) {
  return digits.substring(0, digits.length - 1);
}

/**
 * This is a "private function".  No need for end caller.
 */
CheckLuhnMod10.computeSum = function (digits) {
  var x = 0;
  for (var i = 0; i < digits.length; i += 2) {
    x += digits.charCodeAt(i) - 48;
  }
  for (var i = 1; i < digits.length; i += 2) {
    var c = digits.charCodeAt(i) - 48;
    x += ((c >= 5) ? (2*c - 9) : (2*c));
  }
  return x % 10;
}  

