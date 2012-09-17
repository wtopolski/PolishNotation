#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stack>
#include <cmath>
#include "pl_wtopolski_android_ppn_JniHelper.h"

#define DEBUG_TAG "JNI_HELPER"

using namespace std;

// Funkcja liczaca wartosc wyrazenia postfiksowego
double post2suma(char *expression)
{
	int expression_length = strlen(expression);
    stack<double> heap;
	char *number = new char[20];
	char *temp_number = number;

	for (int i = 0; i < expression_length; i++)
	{
		switch (expression[i])
		{
			// Koniec napisu
			case '\0':
			{
				*number = '\0'; // Konce napis w liczbie
				number = temp_number; // Powracam na poczatek
				if (*number != '\0') // Jesli brak napisu nie konertuj
				{
				    double value = atof(number);
					heap.push(value);
				}
				i = expression_length; // samo break wyszlo by tylko z switch
			}
			break;

			// Separator
			case ' ':
			{
				*number = '\0';
				number = temp_number;
				if (*number != '\0')
				{
				    double value = atof(number);
					heap.push(value);
				}
			}
			break;

			// Operacja dodawania
			case '+':
			{
				double a = heap.top();
				heap.pop();
				double b = heap.top();
				heap.pop();

				heap.push(a + b);
			}
			break;

			// Operacja odejmowania
			case '-':
			{
				// Odejmowanie
				if (expression[i+1] == '\0' || expression[i+1] == ' ')
				{
				    double a = heap.top();
				    heap.pop();
				    double b = heap.top();
				    heap.pop();

					// Z racji iz sa zdejmowane w odwrotnej kolejnosci
					// wiec prawidlowa operacja jest b operacja a;
					heap.push(b - a);
				}
				// Liczba ujemna
				else
				{
					*number++ = expression[i];
				}
			}
			break;

			// Operacja mnozenia
			case '*':
			{
				double a = heap.top();
				heap.pop();
				double b = heap.top();
				heap.pop();

				heap.push(a * b);
			}
			break;

			// Operacja dzielenia
			case '/':
			{
				double a = heap.top();
				heap.pop();
				double b = heap.top();
				heap.pop();

				// Z racji iz sa zdejmowane w odwrotnej kolejnosci
				// wiec prawidlowa operacja jest b operacja a;
				heap.push(b / a);
			}
			break;

			// Normalny znak
			default:
			{
				*number++ = expression[i];
			}
		}
	}
	delete [] number;

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
			next = true;
	}

	if (next == false)
		return;

	// Omijam znaki nawiasu dla podoperacji
	int counter_bracket = 0;
	int counter_bracket_end = 0;
	if (expression[start] == '(' && expression[stop] == ')')
	{
		for (int i = start; i <= stop; i++)
			{
			if (expression[i] == '(')
				counter_bracket++;
			if (expression[i] == ')')
				counter_bracket--;
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

void infiks2postfiks(char *expression, int start, int stop)
{
	// Zamieniam postac infoksowa w postfiksowa
	conversion(expression, start, stop);

	// Usuwam nawiasy
	int offset = 0;
	for (int i = start; i <= stop; i++)
	{
		if (expression[i] == '(' || expression[i] == ')')
		{
			offset -= 2;
		}
		else
		{
			expression[i + offset] = expression[i];
		}
	}
	expression[stop + offset + 1] = '\0';
}

JNIEXPORT jstring JNICALL Java_pl_wtopolski_android_ppn_JniHelper_convertToPrefixNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "INPUT: %s", originCharValue);
    char charOutput[strlen(originCharValue)];
    strcpy(charOutput, originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);

	infiks2postfiks(charOutput, 0, strlen(charOutput) - 1);
	__android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "OUTPUT %s", charOutput);

    return env->NewStringUTF(charOutput);
}

JNIEXPORT jdouble JNICALL Java_pl_wtopolski_android_ppn_JniHelper_countValueFromPrefixNotation(JNIEnv *env, jclass cls, jstring inputJValue)
{
    jboolean isCopy;
    const char* originCharValue = env->GetStringUTFChars(inputJValue, &isCopy);
    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "INPUT: %s", originCharValue);
    char charOutput[strlen(originCharValue)];
    strcpy(charOutput, originCharValue);
    env->ReleaseStringUTFChars(inputJValue, originCharValue);

    return post2suma(charOutput);
}