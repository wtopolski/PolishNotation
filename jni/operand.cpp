#include <string>
#include "headers/operand.h"
#include "headers/tokentype.h"
#include "headers/token.h"

using namespace std;

Operand::Operand(string& _val) : Token(_val) { }

Token_Type Operand::get_type()
{
	return Operand_Type; 
}

