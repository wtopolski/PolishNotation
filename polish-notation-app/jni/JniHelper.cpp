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
#include "headers/support.h"
#include "headers/pl_wtopolski_android_polishnotation_support_JniHelper.h"

#define DEBUG_TAG "PolishNotation"
#define LOGD(x...) __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, x)

using namespace std;

JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_convertToNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    string input(originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);
    LOGD("Convert To Notation [input: %s]", input.c_str());
    string output = Support::convert_to_postfix_notation(input);
    LOGD("Convert To Notation [output: %s]", output.c_str());
    return env->NewStringUTF(output.c_str());
}

JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_countValueFromNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    string input(originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);
    LOGD("Count Value [input: %s]", input.c_str());
	double output = Support::count_postfix(input);
	LOGD("Count Value [output %f]", output);
    return output;
}



