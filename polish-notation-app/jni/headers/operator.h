#ifndef __OPERATOR__
#define __OPERATOR__

#include <string>
#include "tokentype.h"
#include "token.h"

using namespace std;

class Operator : public Token
{
public:
	Operator(string& _val);
	Token_Type get_type();
	bool is_greater_then(Operator& op);
	bool is_greater_or_equal_then(Operator& op);
};

#endif

