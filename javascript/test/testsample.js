// write your tests here... or include them as a library in the header

// basic pass
function testPositive()
{
    assert(is_positive(100));
    assertFalse(is_positive(0));
    assertFalse(is_positive(-100));
}

function testDivideIt()
{
    assertEquals(2, divide_it(16, 8));
    assertEquals(3, divide_it(16, 8));
}

// whoops -- bad input into the test
function testBadCall()
{
   assert( 1 / 0);
}


var jsur_title = "my sample.js"

var jsur_testcases =  [testPositive, testDivideIt, testBadCall];

