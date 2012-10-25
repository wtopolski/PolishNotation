#include <string>
#include "headers/token.h"

Token::Token(string& _val) : val(_val) {}

string& Token::get_value()
{
	return val;
}

