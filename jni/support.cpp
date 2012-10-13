#include <string.h>
#include <stack>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>
#include <iostream>
#include <sstream>
#include <iterator>
#include "headers/tokentype.h"
#include "headers/token.h"
#include "headers/operand.h"
#include "headers/operator.h"
#include "headers/bracket.h"
#include "headers/support.h"

using namespace std;
	
void Support::swap(vector<Token*>& tokens, int pos_el_1, int pos_el_2)
{
	if (pos_el_1 == pos_el_2)
	{
		return;
	}
	else if (pos_el_1 > pos_el_2)
	{
		int pos_temp = pos_el_1;
		pos_el_1 = pos_el_2;
		pos_el_2 = pos_temp;
	}
	
	cout << pos_el_1 << " " << pos_el_2 << endl;
	
	Token* el1 = tokens[pos_el_1];	
	Token* el2 = tokens.at(pos_el_2);	

	cout << el1->get_value() << " " << el2->get_value() << endl;
	
	tokens.erase(tokens.begin() + pos_el_2);
	tokens.erase(tokens.begin() + pos_el_1);

	tokens.insert(tokens.begin() + pos_el_1, el2);
	tokens.insert(tokens.begin() + pos_el_2, el1);
	
}

void Support::split(vector<Token*>& tokens, string& input)
{
	string s;
	for (int index = 0; index < input.length(); index++)
	{
		char ch = input.at(index);
		if (ch == SPACE)
		{
			continue;
		}
		else if (ch == BRACKET_START 
			|| ch == BRACKET_STOP 
			|| ch == MULTIPLICATION 
			|| ch == ADDITION 
			|| ch == DIVISION 
			|| (ch == SUBTRACTION && !s.empty()))
		{
			add_token(tokens, s);
			s.push_back(ch);
			add_token(tokens, s);
		}
		else
		{
			s.push_back(ch);
		}
	}	
	add_token(tokens, s);
}

void Support::add_token(vector<Token*>& tokens, string& value)
{
	if (value.empty())
	{
		return;
	}


	Token_Type type = get_token_type(value);
	switch (type)
	{
	case Operand_Type:
		tokens.push_back(new Operand(value));
	break;
	case Operator_Type:
		tokens.push_back(new Operator(value));
	break;
	case Bracket_Type:
		tokens.push_back(new Bracket(value));
	break;
	}
	value.clear();
}
	
Token_Type Support::get_token_type(string& value)
{
	if (value.length() == 1)
	{
		char ch = value.at(0);
		if (ch == ADDITION || ch == SUBTRACTION || ch == MULTIPLICATION || ch == DIVISION)
		{
			return Operator_Type;
		}
		else if (ch == BRACKET_START || ch == BRACKET_STOP)
		{
			return Bracket_Type;
		}
		else if (ch == SPACE)
		{
			return None_Type;
		}
	}

	return Operand_Type; 
}
