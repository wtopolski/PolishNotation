#ifndef __BRACKET__
#define __BRACKET__

#include <string>
#include "tokentype.h"
#include "token.h"

using namespace std;

class Bracket : public Token
{
public:
	Bracket(string& _val);
	Token_Type get_type();
};

#endif

