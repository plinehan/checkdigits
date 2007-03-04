/* CheckISO7064Mod11_1.js  Version 1.0.0  24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */

function ISO7064Mod11_10Check() {};

ISO7064Mod11_10Check.encode = function (str) {
  return str + this.computeCheck(str);
}

ISO7064Mod11_10Check.verify = function (str) {
  var t = 10;
  for (var i = 0; i < str.length -1; ++i) {
    var c = str.charCodeAt(i) - 48;
    if (c < 0 || c > 9) return false;
    t = (2 * this.f(t+c)) % 11;
  }
  return (((t + this.getCheckDigit(str)) % 10) == 1);
}

/**
 * "private" helper function
 */
ISO7064Mod11_10Check.f = function (x) {
  var val = x % 10;
  return (val == 0) ? 10 : val;
}

ISO7064Mod11_10Check.computeCheck = function (str) {
  var t = 10;
  for (var i = 0; i < str.length; ++i) {
    t = (2 * this.f(t + str.charCodeAt(i) - 48)) % 11;
  }
  return (11 - t) % 10;
}

ISO7064Mod11_10Check.getCheckDigit = function (str) {
  return str.charCodeAt(str.length - 1) - 48;
}

ISO7064Mod11_10Check.getData = function (str) {
  return str.substring(0, str.length - 1);
}
