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
#include "headers/support.h"

using namespace std;

double Support::count(string& sentence)
{
    vector<string> tokens;
    stack<double> heap;

    istringstream iss(sentence);
    copy(istream_iterator<string>(iss), istream_iterator<string>(), back_inserter<vector<string> >(tokens));

    vector<string>::iterator it;
    for( it=tokens.begin(); it!=tokens.end(); ++it )
    {
        string token = *it;

        if (token.compare("+") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(a + b);
        }
        else if (token.compare("-") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(b - a);
        }
        else if (token.compare("*") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(a * b);
        }
        else if (token.compare("/") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(b / a);
        }
        else
        {
            double value = atof(token.c_str());
        	heap.push(value);
        }
    }

	double res = heap.top();
	heap.pop();

	return res;
}

string Support::convertToNotation(string& input)
{
    vector<Token*> infix_tokens;
    Support::split(infix_tokens, input);

    vector<Token*> postfix_tokens;
    Support::convert_to_postfix_notation(postfix_tokens, infix_tokens);

    string output;
    vector<Token*>::iterator it;
    for (it=postfix_tokens.begin(); it!=postfix_tokens.end(); ++it)
    {
                Token* token = *it;
                output.append(token->get_value());
                output.append(" ");
                delete token;
        }

    return output;
}

// Swap two elements in vector [x][y][z] >> [x][z][y]
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
	
	Token* el1 = tokens[pos_el_1];	
	Token* el2 = tokens.at(pos_el_2);
	
	tokens.erase(tokens.begin() + pos_el_2);
	tokens.erase(tokens.begin() + pos_el_1);

	tokens.insert(tokens.begin() + pos_el_1, el2);
	tokens.insert(tokens.begin() + pos_el_2, el1);
}

// Add operator to postfix vector ex. [12+] << [*] -> [12*+]
int Support::process_operator(vector<Token*>& output, Operator* new_operator)
{
	output.push_back(new_operator);

    int index_of_right = output.size() - 1;
    int index_of_left = output.size() - 2;

    while (index_of_left >= 0)
    {
        if (output[index_of_left]->get_type() != Operator_Type)
        {
            break;
        }

        Operator* op_right = (Operator*) output[index_of_right];
        Operator* op_left = (Operator*) output[index_of_left];

        if (op_right->is_greater_then(*op_left))
        {
            Support::swap(output, index_of_left, index_of_right);
            index_of_right--;
            index_of_left--;
        }
        else
        {
            break;
        }
    }

    return index_of_right;
}

// Add operand to postfix vector ex. [12*+] << [3] -> [123*+]
void Support::process_operand(vector<Token*>& output, Operand* op, int place)
{
	if (output.empty())
	{
		output.push_back(op);
		return;
	}

	Token* last = output.back();
	if (last->get_type() == Operand_Type)
	{
		output.push_back(op);
	}
	else
	{
	    // Just add before previous added operator
		vector<Token*>::iterator insert_place = output.end();
		insert_place -= (output.size() - place);
        output.insert(insert_place, op);
	}
}

// Convert simple infix notation ex. 1+2*3 to postfix notation 123*+ (without brackets)
void Support::convert_to_postfix_notation(vector<Token*>& postfix_tokens, vector<Token*>& infix_tokens)
{
    vector<Token*>::iterator it;
    int last_place_of_operator = 0;
    for (it=infix_tokens.begin(); it!=infix_tokens.end(); ++it)
    {
		Token* token = *it;
		switch (token->get_type())
		{
		case Operand_Type:
			process_operand(postfix_tokens, (Operand*)token, last_place_of_operator);
		break;
		case Operator_Type:
			last_place_of_operator = process_operator(postfix_tokens, (Operator*)token);
		break;
		}
	}
}

// Split input string to tokens ex. 1+2 >> [1][+][2]
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
		else if (ch == MULTIPLICATION 
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
		else if (ch == SPACE)
		{
			return None_Type;
		}
	}

	return Operand_Type; 
}
