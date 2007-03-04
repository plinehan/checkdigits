/* coretest.js  Version 1.0.0  24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */
var checkcases= [
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
		 ];

function testCheck() {
  var check = getCheckInstance();
  for (var i = 0; i < checkcases.length; ++i) {
    var e = check.encode(checkcases[i]);
    // make sure it did something
    assertTrue(e.length > checkcases[i].length);

    // make sure data is correct
    assertEquals(checkcases[i], check.getData(e));
    
    // make sure it verifies
    assertTrue(e + " did not verify", check.verify(e))
  }
}

/**
 * At each position, test every possible error
 */
function testSingleError() {
  var check = getCheckInstance();
  for (var i = 0; i < checkcases.length; ++i) {
     var c = check.encode(checkcases[i]);
    for (var j = 0; j < c.length; ++j) {
      for (var k = 1; k <= 9; ++k) {
	// nc is the 'new character' that is in error
	var nc = (c.charCodeAt(j) - 48 + k) % 10;
	// create new string
	var nsx = c.substring(0, j) + nc + c.substring(j+1, c.length);
	//Assert.IsFalse(check.verify(nsx));
	assertFalse(c + " to " + nsx + " should not validate", check.verify(nsx));
      }
    }
  }
}

function testAdjacentTraspositions() {
  var check = getCheckInstance();
  for (var i = 0; i < checkcases.length; ++i) {
    var c = check.encode(checkcases[i]);
    for (var j = 0; j < c.length - 1; ++j) {
      // transpose at (j, j+1)
      var nc = c.substring(0,j) + 
	c.charAt(j+1) + c.charAt(j)+
	c.substring(j+2);
      // make sure we didn't screw up
      assertEquals(nc.length, c.length);
      
      // if original input isn't symmetrical, do a test
      if (!(c == nc || badTransposition(c.charAt(j), c.charAt(j+1)))) {
	assertFalse("i=" + i + 
		    ", j=" + j + ", " + c + ", " + nc,
		    check.verify(nc));
      }
    }
  }
}


