#ifndef __TOKEN__
#define __TOKEN__

#include <string.h>
#include <stack>
#include <string>
#include "tokentype.h"

using namespace std;

class Token
{
protected:
	string val;
public:
	Token(string& _val);
	string& get_value();
	virtual Token_Type get_type() = 0;
};

#endif

