# Check Digits: Yes #

Check digits (or characters) are a 'checksum' on a number or word to help prevent manual typing errors.

### Manual Errors ###

| **Error Type**              | **Example** | **Relative Occurance** |
|:----------------------------|:------------|:-----------------------|
| Single errors	     | a → b  | 60% - 95% |
| Omitting or adding a single digit | c → cc, cc → c | 10-20% |
| Adjacent transpositions   | ab → ba | 10% to 20% |
| Twin errors	             | aa → bb | 0.5% to 1.5% |
| Jump transpositions	     | abc → cba | 0.5% to 1.5% |
| Jump twin errors	     | aba → cbc  | Less than 1% |
| Phonetic errors (English) | a0 → 1a (e.g., 30 → 13)	| 0.5% to 1.5% |


# Check digits: No #

Do not use check digits in the following cases:

  * Error correction. For check systems, either it's right or not,and no second guessing.
  * Fraud detection or prevention. All of these check schemes are easily computed by anyone. They are not ciphers or hashes.
  * Very long datasets, or binary data sets. If you have this type of data, consider using something like crc or alder checksums, or a hash-based checksum.