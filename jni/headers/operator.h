#ifndef __OPERATOR__
#define __OPERATOR__

#include <string>
#include "tokentype.h"
#include "token.h"
#include "support.h"

using namespace std;

class Operator : public Token
{
public:
	Operator(string& _val);
	Token_Type get_type();
	bool is_equal_or_greater(Operator& op);
};

#endif

