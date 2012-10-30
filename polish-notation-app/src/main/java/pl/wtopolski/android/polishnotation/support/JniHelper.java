package pl.wtopolski.android.polishnotation.support;

/**
 * Supports calculation operations.
 *
 * @author Wojciech Topolski (wojciech.topolski@gmail.com)
 */
public class JniHelper {
    /**
     * Convert simple infix sentence (without brackets) for postfix sentence.
     * @param value infix ex. 1+2*3-4
     * @return postfix ex. 1 2 3 * + 4 -
     */
    public static native String convertToPostfixNotation(String value);

    /**
     * Count value from postfix sentence.
     * @param value postfix ex. 1 2 3 * + 4 -
     * @return operation result
     */
    public static native double countValueFromPostfixNotation(String value);

    /**
     * Convert simple infix sentence (without brackets) for prefix sentence.
     * @param value infix ex. 1+2
     * @return prefix ex. + 1 2
     */
    public static native String convertToPrefixNotation(String value);

    /**
     * Count value from prefix sentence.
     * @param value prefix ex. + 1 2
     * @return operation result
     */
    public static native double countValueFromPrefixNotation(String value);
}
