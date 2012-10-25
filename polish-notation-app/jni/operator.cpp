#include <string>
#include "headers/operator.h"
#include "headers/tokentype.h"
#include "headers/token.h"
#include "headers/support.h"

using namespace std;

Operator::Operator(string& _val) : Token(_val) { }

Token_Type Operator::get_type()
{
	return Operator_Type; 
}

bool Operator::is_greater_then(Operator& op)
{
	string op_value = op.get_value();
	char op_char = op_value.at(0);
	char this_char = val.at(0);

	if ((this_char == Support::MULTIPLICATION || this_char == Support::DIVISION)
	    && (op_char == Support::ADDITION || op_char == Support::SUBTRACTION))
	{
	    return true;
	}

	return false;
}

