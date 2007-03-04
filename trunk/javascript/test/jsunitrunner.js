/* jsunitrunner.js  Version 1.0.0 24-Jun-05
 * http://modp.com/release/checkdigits/
 * Copyright 2005, Nick Galbreath.  All Rights Reserved.
 * Terms of use: standard BSD License at http://modp.com/license-bsd.txt
 * or http://www.opensource.org/licenses/bsd-license.php
 */

function TestRunner() {};

// hack to get the function name from a function
TestRunner.ExtractFunctionName = function (functionRef) 
{
  var fs = functionRef.toString();
  return fs.slice(fs.indexOf("function") + 9, fs.indexOf("()"));
}

// input an array of function references
  TestRunner.RunTests = function (fary)
{
  var timeStart = new Date();
  var statsPass = 0;
  var statsFail = 0;
  var statsError = 0;

  var r = new String();
  r += "<table>";
  for (var i = 0; i < fary.length; i++) {

    try {
      fary[i]();
      statsPass++;
      r += '<tr style="background: green"><td valign="top">' +  TestRunner.ExtractFunctionName(fary[i]) + "</td><td>pass</td><td>&nbsp;</td></tr>";
    } catch (e) {
      r += '<tr style="background: red"><td valign="top">'  +  TestRunner.ExtractFunctionName(fary[i]) + '</td><td valign="top">';
      // is this our exception?
      if (typeof e.isJsUnitException == 'undefined') {
	// no.. it's from the browser.
	statsError++;
	r += "error</td><td>";
	r += "<table>";
	for (k in e) {
	  r += "<tr><td>" + k + "</td><td>" + e[k] + "</td></tr>";
	}
	r += "</table>";
      } else {
	// this is our exception, halt test and print error
	statsFail++;
	r += "fail</td><td>";
	r += "<table>";
	for (k in e) {
	  r += "<tr><td>" + k + "</td><td>" + e[k] + "</td></tr>";
	}
	r += "</table>";
      }
      r += "</td></tr>";
    }
  }
  r += "</table>";
  var timeEnd = new Date();
  var seconds = (timeEnd.getTime()-timeStart.getTime()) / 1000;

  document.writeln("Tests completed in " + seconds.toFixed(3) + " seconds.<br />");
  document.writeln(statsPass + " passed, " + statsFail + " failed, " + statsError + " errors.");
  document.writeln("<hr />");
  document.writeln(r);
}
