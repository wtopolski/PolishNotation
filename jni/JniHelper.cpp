#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stack>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>
#include <iostream>
#include <sstream>
#include <iterator>
#include "pl_wtopolski_android_ppn_JniHelper.h"

#define DEBUG_TAG "JNI_HELPER"
#define LOGD(x...) __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, x)

using namespace std;

double count(string sentence)
{
    vector<string> tokens;
    stack<double> heap;

    istringstream iss(sentence);
    copy(istream_iterator<string>(iss), istream_iterator<string>(), back_inserter<vector<string> >(tokens));

    vector<string>::iterator it;
    for( it=tokens.begin(); it!=tokens.end(); ++it )
    {
        string token = *it;
        LOGD("token: %s", token.c_str());

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
            heap.push(a - b);
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

// Funkcja zamieniajaca wyrazenie infiksowe w postfiksowe
void conversion(char *expression, int start, int stop)
{
	// Sprawdzam czy start i stop nie wskazyja na ta sama liczbe
	bool next = false;
	for (int i = start; i <= stop; i++)
	{
		if (expression[i] == ' ')
		{
			next = true;
		}
	}

	if (next == false)
	{
		return;
    }

	// Omijam znaki nawiasu dla podoperacji
	int counter_bracket = 0;
	int counter_bracket_end = 0;
	if (expression[start] == '(' && expression[stop] == ')')
	{
		for (int i = start; i <= stop; i++)
		{
			if (expression[i] == '(')
			{
				counter_bracket++;
			}
			if (expression[i] == ')')
			{
				counter_bracket--;
			}
			// Jesli mamy oklajacy nawias to go pomijamy
			if (counter_bracket == 0)
			{
				counter_bracket_end = i;
				break;
			}
		}
	}
	// Jesli licznik nazwiasow zamknal sie dopiero na ostatnim wyrazeniu
	if (counter_bracket_end == stop)
	{
		start += 2;
		stop -= 2;
	}

	int a_start = start, a_stop = start, b_start = stop, b_stop = stop;
	counter_bracket = 0;
	char char_current;

	// Znajduje koniec i poczatek wyrazenia 'a'
	while (true)
	{
		if (expression[a_stop] == '(')
			counter_bracket++;
		if (expression[a_stop] == ')')
			counter_bracket--;
		if ((expression[a_stop] == '-' && expression[a_stop + 1] == ' ') ||
		    expression[a_stop] == '+' || expression[a_stop] == '*' || expression[a_stop] == '/')
		{
			// Zamknieto wyrazenie (...( wyrazenie )...)
			if (counter_bracket == 0)
			{
				a_stop -= 2;// Odejmuje by zniwelowac znak operacji i spacje
				break;
			}
		}
		a_stop++;
	}

	// Ustawiam flagi
	char_current = expression[a_stop + 2];
	b_start = a_stop + 4; // 2 znaki operacji + 1 spacja + 1 nastepeny znak

	// Przepisuje wyrazenie b o 2 znaki w prawo
	for (int i = b_start; i <= b_stop; i++)
		expression[i - 2] = expression[i];

	// Ostatnie dwa znaki przeprawiam na spacje i znak operacji
	expression[b_stop - 1] = ' ';
	expression[b_stop] = char_current;

	// Aktualizuje indeksy dla 'b'
	b_start -= 2;
	b_stop -= 2;

	conversion(expression, a_start, a_stop);
	conversion(expression, b_start, b_stop);
}

string convertToNotation(string value)
{
	char *expression = new char[value.size() + 1];
    strcpy(expression, value.c_str());

	conversion(expression, 0, strlen(expression) - 1);

    string response(expression);
	replace(response.begin(), response.end(), '(', ' ');
	replace(response.begin(), response.end(), ')', ' ');

    return response;
}

JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_ppn_JniHelper_convertToPrefixNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    string input(originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);

    LOGD("CTPN INPUT: %s", input.c_str());
	string output = convertToNotation(input);
	LOGD("CTPN OUTPUT %s", output.c_str());

    return env->NewStringUTF(output.c_str());
}

JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_ppn_JniHelper_countValueFromPrefixNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    string input(originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);

    LOGD("CVFPN INPUT: %s", input.c_str());
	double output = count(input);
	LOGD("CVFPN OUTPUT %f", output);

    return output;
}