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
    public static native String convertToNotation(String value);

    /**
     * Count value from postfix sentence.
     * @param value postfix ex. 1 2 3 * + 4 -
     * @return operation result
     */
    public static native double countValueFromNotation(String value);
}
