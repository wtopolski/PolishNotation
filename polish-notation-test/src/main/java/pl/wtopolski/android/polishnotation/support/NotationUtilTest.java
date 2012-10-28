package pl.wtopolski.android.polishnotation.support;

import android.test.AndroidTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: wtopolski
 * Date: 28.10.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class NotationUtilTest extends AndroidTestCase {

    protected void setUp() throws Exception {
        super.setUp();
        System.loadLibrary("polish-notation-app");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSimpleConvert(){
        // given
        String argument = "1+1";
        String expected = "1 1 + ";
        String result = "";

        // then
        try {
            result = NotationUtil.convertInfixToPostfix(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(result, expected);
    }

    public void testSimpleCount(){
        // given
        String argument = "1 1 + ";
        double expected = 2f;
        double result = -1f;

        // then
        try {
            result = NotationUtil.countFromPostfixNotation(argument);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertEquals(result, expected);
    }
}
