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

int main( int argc, const char* argv[] )
{
	string input = "a*b+c/d";
	string output = Support::convertToNotation(input);
	cout << input << endl << output << endl;
	return 0;
}
