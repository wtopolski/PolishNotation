/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class pl_wtopolski_android_polishnotation_support_JniHelper */

#ifndef _Included_pl_wtopolski_android_polishnotation_support_JniHelper
#define _Included_pl_wtopolski_android_polishnotation_support_JniHelper
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     pl_wtopolski_android_polishnotation_support_JniHelper
 * Method:    convertToPostfixNotation
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_convertToPostfixNotation
  (JNIEnv *, jclass, jstring);

/*
 * Class:     pl_wtopolski_android_polishnotation_support_JniHelper
 * Method:    countValueFromPostfixNotation
 * Signature: (Ljava/lang/String;)D
 */
JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_countValueFromPostfixNotation
  (JNIEnv *, jclass, jstring);

/*
 * Class:     pl_wtopolski_android_polishnotation_support_JniHelper
 * Method:    convertToPrefixNotation
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_convertToPrefixNotation
  (JNIEnv *, jclass, jstring);

/*
 * Class:     pl_wtopolski_android_polishnotation_support_JniHelper
 * Method:    countValueFromPrefixNotation
 * Signature: (Ljava/lang/String;)D
 */
JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_polishnotation_support_JniHelper_countValueFromPrefixNotation
  (JNIEnv *, jclass, jstring);

#ifdef __cplusplus
}
#endif
#endif
