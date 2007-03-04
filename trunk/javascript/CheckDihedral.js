/* CheckDihedral.js  Version 1.0  24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */

// stub class
function DihedralCheck() {};

// "private members and functions"

// dihedral addition matrix A+B = a[A][B]
DihedralCheck.a = [[ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
		   [ 1, 2, 3, 4, 0, 6, 7, 8, 9, 5 ],
		   [ 2, 3, 4, 0, 1, 7, 8, 9, 5, 6 ],
		   [ 3, 4, 0, 1, 2, 8, 9, 5, 6, 7 ],
		   [ 4, 0, 1, 2, 3, 9, 5, 6, 7, 8 ],
		   [ 5, 9, 8, 7, 6, 0, 4, 3, 2, 1 ],
		   [ 6, 5, 9, 8, 7, 1, 0, 4, 3, 2 ],
		   [ 7, 6, 5, 9, 8, 2, 1, 0, 4, 3 ],
		   [ 8, 7, 6, 5, 9, 3, 2, 1, 0, 4 ],
		   [ 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 ] ];
DihedralCheck.inverse = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];
DihedralCheck.p = [ [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
		    [ 1, 5, 7, 6, 2, 8, 3, 0, 9, 4 ],
		    [ 5, 8, 0, 3, 7, 9, 6, 1, 4, 2 ],
		    [ 8, 9, 1, 6, 0, 4, 3, 5, 2, 7 ],
		    [ 9, 4, 5, 3, 1, 2, 6, 8, 7, 0 ],
		    [ 4, 2, 8, 6, 5, 7, 3, 9, 0, 1 ],
		    [ 2, 7, 9, 3, 8, 0, 6, 4, 1, 5 ],
		    [ 7, 0, 4, 6, 9, 1, 3, 2, 5, 8 ] ];

// "public" functions
DihedralCheck.encode = function (str) {
  return this.computeCheck(str) + str;
}

DihedralCheck.verify = function (str) {
  try {
    var x = 0;
    for (var i = 0; i < str.length; ++i) {
      x = this.a[x][this.p[i % 8][str.charCodeAt(i) - 48]];
    }
    return x == 0;
  } catch (e) {
    return false;
  }
}

DihedralCheck.computeCheck = function (str) {
  var x = 0;
  for (var i = 0; i < str.length; ++i) {
    x = this.a[x][this.p[(i + 1) % 8][str.charCodeAt(i) - 48]];
  }
  return this.inverse[x];
}
  
/**
 * Data all but first digit
 */
DihedralCheck.getData = function (str) {
  return str.substring(1);
}

/**
 * check digit is first digit
 */
DihedralCheck.getCheckDigit = function (str) {
  return str.charCodeAt(0) - 48;
}
