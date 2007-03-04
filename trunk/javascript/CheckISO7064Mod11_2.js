/* CheckISO7064Mod11_2 Version 1.0.0 24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */
function CheckISO7064Mod11_2() {};

CheckISO7064Mod11_2.encode = function (str) {
  var c = this.computeCheck(str);
  if (c == 10) {
    return str + 'X';
  } else {
    return str + c;
  }
}

CheckISO7064Mod11_2.verify = function (str) {
  // normally I'd redo the algorith, but with check digit
  // using 0-9 + X, it's easiler this way.
  return this.computeCheck(this.getData(str)) == this.getCheckDigit(str);
}

CheckISO7064Mod11_2.computeCheck = function (str) {
  var p = 0;
  for (var i = 0; i < str.length; ++i) {
    var c= str.charCodeAt(i) - 48;
    p = 2*(p+c);
  }
  p %= 11;
  return (12 - p) % 11;
}

CheckISO7064Mod11_2.getCheckDigit = function (str) {
  var c = str.charAt(str.length - 1);
  if (c == 'x' || c == 'X') {
    return 10;
  } else {
    // redo to get ascii code
    c = str.charCodeAt(str.length - 1);
    return c - 48;
  }
}

CheckISO7064Mod11_2.getData = function (str) {
  return str.substring(0, str.length - 1);
}
