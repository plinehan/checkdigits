Check digits (or characters) are a 'checksum' on a number or word to help prevent manual typing errors.

This was originally based from [Cryptography for Internet and Database Applications, pp 221-229](http://code.google.com/p/cida/).

This projects provides examples and source in both java and javacript.


Check digits (or characters) are a 'checksum' on a number or word to help prevent manual typing errors.   They are not used for long or binary data sets.  They are not hashes, nor cryptographic.  They do not do error correction.  They are for catching CheckDigitsAndManualErrors.

The following schemes are currently implemented:

  * Dihedral
  * Luhn, Mod 10
  * ISO 7064, Mod 11,2
  * ISO 7064, Mod 11,10
  * ISO 7064, Mod 97,10

More information on each is in CheckDigitSystems.

All versions have some type of 'object' with the following methods:

| `encode`	  | Adds check digit to given string |
|:-----------|:---------------------------------|
| `verify`	  | Verifies data is correct using check digits |
| `computeCheck` | Returns the check digit (as integer) for given data |
| `getCheck`	  | Extracts the check characters |
| `getData`	  | Extracts the data with out the check character |

`encode` may throw an exception if 'bad' data (e.g. non-numeric) is passed to it, while verify only returns true or false. The idea here is that encode normally gets it's data from 'trusted' internal sources (a database key for instance), so it should be pretty usual, in fact, exceptional that bad data is being passed in. verify doesn't throw exceptions since this data is coming from an 'untrusted' source such user input and who knows what they'll type in. Bad input is 'non-exceptional' and is expected.

Note: unless explicitly specified, the code does not check the input length or trim off white space since that is application specific.

The `verify` method could have been universally implemented as `encode(getData(str)) == getCheck(str)`, however in most caes custom code was implemented. This was done so that:

  * Reduce number of function calls (speed)
  * Reduce number of objects/string generate (speed and memory)
  * Allow the verify method to be 'yanked out' of the source file with out dependencies on other methods
  * Improve clarity of the algorithms. Frequently the computation of the check digits is tricky, while the verification is very clear.

### Java ###

All check schemes implement the `CheckDigit` interface.

Extensive Junit tests exist in the `com.modp.checkdigits.test` package.

Javadoc code is in the `java/doc` directory.

### JavaScript ###

The javascript versions follow the java code fairly closely. However, the javascript objects do not formally use an interface or use inheritance. They are effectively static classes.

Extensive unit tests exist in the `javascript/test` directory. Just open the html files in your browser to run the tests.

NOTE: The core of the unit test is based from jsunit, but the test runner is custom. Need to split out the new runner and document it.

