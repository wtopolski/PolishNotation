#include <string.h>
#include <stack>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>
#include <iostream>
#include <sstream>
#include <iterator>
#include "headers/tokentype.h"
#include "headers/token.h"
#include "headers/operand.h"
#include "headers/operator.h"
#include "headers/support.h"

using namespace std;

void test(const char* input_chars)
{
	string input = input_chars;
	string output = "";
	cout << endl << "Test: " << input << endl;

	output = Support::convert_to_postfix_notation(input);
	double res1 = Support::count_postfix(output);
	cout << "Postfix: [" << output << "] = " << res1 << endl;

	output = Support::convert_to_prefix_notation(input);
	double res2 = Support::count_prefix(output);
	cout << "Prefix: [" << output << "] = " << res2 << endl;

	if (res1 != res2)
	{
		cout << "!!!!!!!!!!!!!!!KABUM!!!!!!!!!!!!!!!!" << endl;
	}
}

int main( int argc, const char* argv[] )
{
	test("11-22");
	test("11+22");
	test("11*22");
	test("11/22");
	test("11*22+33");
	test("11+22*33");
	test("11/22/33");
	test("11-22-33");
	test("11-22*33+44");
	test("11/22-33*44");
	return 0;
}
