#ifndef __SUPPORT__
#define __SUPPORT__

#include <string.h>
#include <stack>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>
#include <iostream>
#include <sstream>
#include <iterator>
#include "tokentype.h"
#include "token.h"
#include "operand.h"
#include "operator.h"

using namespace std;

class Support
{
public:
	static const char ADDITION = '+';
	static const char SUBTRACTION = '-';
	static const char MULTIPLICATION = '*';
	static const char DIVISION = '/';
	static const char BRACKET_START = '(';
	static const char BRACKET_STOP = ')';
	static const char SPACE = ' ';
	
	// Prefix
	static string convert_to_prefix_notation(string& input);
	static double count_prefix(string& sentence);
	static void convert_to_prefix(vector<Token*>& postfix_tokens, vector<Token*>& infix_tokens);

	// Postfix
	static string convert_to_postfix_notation(string& input);
	static double count_postfix(string& sentence);
	static void convert_to_postfix(vector<Token*>& postfix_tokens, vector<Token*>& infix_tokens);

	// Common
	static void process_operand(vector<Token*>& output, Operand* op, int place);
	static int process_operator(vector<Token*>& output, Operator* op, bool is_postfix);
	static void split(vector<Token*>& tokens, string& input);
	static void swap(vector<Token*>& tokens, int pos_el_1, int pos_el_2);
	static void add_token(vector<Token*>& tokens, string& value);
	static Token_Type get_token_type(string& value);
};

#endif

