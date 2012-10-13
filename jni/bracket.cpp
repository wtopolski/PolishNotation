#include <string>
#include "headers/bracket.h"
#include "headers/tokentype.h"
#include "headers/token.h"

using namespace std;

Bracket::Bracket(string& _val) : Token(_val) { }

Token_Type Bracket::get_type()
{
	return Bracket_Type; 
}

