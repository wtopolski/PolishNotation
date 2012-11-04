package pl.wtopolski.android.polishnotation.support;

import java.util.LinkedList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import android.util.Log;

import android.test.AndroidTestCase;

/**
 * Created with IntelliJ IDEA. User: wtopolski Date: 28.10.12 Time: 17:47 To
 * change this template use File | Settings | File Templates.
 */
public class NotationUtilTest extends AndroidTestCase {

    static {
        System.loadLibrary("polish-notation-app");
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSimplePostfixConvert() {
        // given
        String argument = "7 - (1 + 1)";
        String expected = "7 1 1 + -";
        String result = "";

        // when
        try {
            result = NotationUtil.convertInfixToPostfix(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(expected, result);
    }

    public void testSimplePostfixCount() {
        // given
        String argument = "1 1 +";
        double expected = 2f;
        double result = -1f;

        // when
        try {
            result = NotationUtil.countFromPostfixNotation(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(expected, result);
    }

    public void testSimplePrefixConvert() {
        // given
        String argument = "1+1";
        String expected = "+ 1 1";
        String result = "";

        // when
        try {
            result = NotationUtil.convertInfixToPrefix(argument);
            result = result.trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(result, expected);
    }

    public void testSimplePrefixCount() {
        // given
        String argument = "+ 1 1";
        double expected = 2f;
        double result = -1f;

        // when
        try {
            result = NotationUtil.countFromPrefixNotation(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(result, expected);
    }

    public void testMultiMathematicalExpressions() {
        // given
        Map<String, Double> expressions = getMathematicalExpressions();

        try {
            for (String expression : expressions.keySet()) {
                // when
                double expected = expressions.get(expression);

                String postfixNotation = NotationUtil.convertInfixToPostfix(expression);
                double postfixResult = NotationUtil.countFromPostfixNotation(postfixNotation);
                postfixResult = Math.round(postfixResult * 100f) / 100f;

                String prefixNotation = NotationUtil.convertInfixToPrefix(expression);
                double prefixResult = NotationUtil.countFromPrefixNotation(prefixNotation);
                prefixResult = Math.round(prefixResult * 100f) / 100f;

                // then
                assertEquals(postfixResult, expected);
                assertEquals(prefixResult, expected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Double> getMathematicalExpressions() {
        Map<String, Double> expressions = new LinkedHashMap<String, Double>();
        expressions.put("7 - (1 + 1)", Double.valueOf(5f));
        expressions.put("(15 / (7 - (1 + 1))) * 3", Double.valueOf(9f));
        expressions.put("((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))", Double.valueOf(5f));
        expressions.put("4 * 4 - 6 / 5", Double.valueOf(14.80f));
        expressions.put("4 + 4 * 6 - 53", Double.valueOf(-25f));
        expressions.put("-13 * -9 / -3 --4", Double.valueOf(-35f));
        return expressions;
    }
}
