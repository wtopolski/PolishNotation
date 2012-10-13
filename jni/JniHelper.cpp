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
#include "headers/tokentype.h"
#include "headers/token.h"
#include "headers/operand.h"
#include "headers/operator.h"
#include "headers/bracket.h"
#include "headers/support.h"
#include "headers/pl_wtopolski_android_polishnotation_JniHelper.h"

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

        if (token.compare("+") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(a + b);
            LOGD("%f + %f", a, b);
        }
        else if (token.compare("-") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(b - a);
            LOGD("%f - %f", b, a);
        }
        else if (token.compare("*") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(a * b);
            LOGD("%f * %f", a, b);
        }
        else if (token.compare("/") == 0)
        {
            double a = heap.top();
            heap.pop();
            double b = heap.top();
            heap.pop();
            heap.push(b / a);
            LOGD("%f / %f", b, a);
        }
        else
        {
            double value = atof(token.c_str());
        	heap.push(value);
            LOGD("add: %f", value);
        }
    }

	double res = heap.top();
	heap.pop();

	return res;
}

JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_polishnotation_JniHelper_convertToNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    string input(originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);

    LOGD("CTPN INPUT: %s", input.c_str());

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

	LOGD("CTPN OUTPUT %s", output.c_str());
    return env->NewStringUTF(output.c_str());
}

JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_polishnotation_JniHelper_countValueFromNotation(JNIEnv *env, jclass cls, jstring inputJValue)
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