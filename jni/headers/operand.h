#ifndef __OPERAND__
#define __OPERAND__

#include <string>
#include "tokentype.h"
#include "token.h"

using namespace std;

class Operand : public Token
{
public:
	Operand(string& _val);
	Token_Type get_type();
};

#endif

