# Check Digit Systems #

## Decimal Systems ##

Decimal check systems operate on a string of digits '0' - '9', and also produce decimal check digits.

### Verhoeff's Dihedral Check ###
This scheme was first invented in 1969 by Verhoeff, and originally used on pre-euro German banknotes. It uses dihedral addition (specifically, the additive Group D5, which is based on rotations of a pentagon), and a permutation array (iterating (01589427)(36) ) to produce the check digit. While sounding complicated, the implemetation is very simple.

It is the only single digit check scheme that catches all single digit errors, and all single transpositions. Use this for new projects.

### Luhn Formula, Mod 10 ###

This formaula is known by many names: the "Luhn Formula", "The IBM Check", "Mod 10", and is officially specified in "Annex B to ISO/IEC 7812, Part 1" and in "ANSI X4.13". It is used as the check scheme on credit cards such as Visa, Master Card, and American Express.

It catches all single digit errors, but does not catch transposition errors with "0" and "9" (meaning "09" → "90" and "90" → "09" are not caught).

### ISO 7074 Mod 11,10 ###

The ISO 7074 standard defines a family of check digits in the form of "Mod N+1,N".

Usage: [blood bags](http://www.eurocode.org/guides/checkdig/english/index.html)

It catches all single digit errors but does not catch transposition errors "01" → "10" (but not vice-versa) and "34" → "43" (but not vice-versa).

### ISO 7064 Mod 97,10 ###

This scheme produces two digits as a check. And as a result, it catches just about every possible error. If you can afford the extra digit, this system is superior to the dihedral check.

It also has an especially compact formula. The check digits are given by `mod(98 - mod(data * 100, 97), 97)` and the verification is just `mod(data_check,97) == 1`. In practice an alternate algorithm (based on Horner's Rule) is used to avoid overflow issues.

Usage: [banking](http://a9.com/?q=banking+check+digit+mod+97+10)

## AlphaNumeric Systems ##

Check digits schemes can also work on data that contains more than just digits.


### ISO 7064 Mod 11,2 ###

The data digits must be '0' - '9' but the check digit may also be 'X' (representing the value 10). Usage: unknown. This is not the ISBN check.

### ISO 7064 Mod 37,2 ###
Usage: Medical Labeling, [Blood](https://www.the-stationery-office.co.uk/nbs/rdbk2001/blood62.htm)

### ISO 7064 Mod 17,16 ###

This scheme works similar to Mod 11,10, but is defined but uses hexadecimal digits ('0' - '9', 'A' - 'F') for the data and check digit. Oddly it's not actually in the ISO 7064 spec, but some other ISO spec
Usage ISAN, V-ISAN

### ISO 7064 Mod 37,36 ###

This scheme works similar to Mod 11,10, but is defined to use alphanumeric characters '0' -'9', 'A' - 'Z' for the data and check digit

Usage: [Livestock identification](http://www.beefusa.org/NEWSLivestockIdentificationUseofAlternativeNumberingSystems10381.aspx) (not the primary source, but beats me how to look the appropriate USDA "docket")


### Oddballs: ISO 7064 Mod 27,26, Mod 661-26, Mod 1271-36 ###

I was unable to find any usage for the following check systems, but they are part of ISO 7064:

  * ISO 7064 Mod 27,26 -- same as Mod37,36 but restricted to 'A'-'Z'
  * ISO 7064 Mod 661-26 -- restricted to 'A'-'Z' and produces 2 check digits
  * ISO 7064 Mod 1271-36 -- alphanumeric and produces 2 check digits