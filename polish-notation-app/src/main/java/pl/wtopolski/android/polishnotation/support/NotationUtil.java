package pl.wtopolski.android.polishnotation.support;

import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotationUtil {
    private static final String TOKEN_PREFIX = "T";
    private static final String TOKEN_MAIN = TOKEN_PREFIX + "0";

    /**
     * Count value from prefix sentence.
     *
     * @param postfix
     * @return
     */
    public static double countFromPrefixNotation(String postfix) {
        return JniHelper.countValueFromPrefixNotation(postfix);
    }

    /**
     * Convert infix sentence to prefix sentence.
     *
     * @param infix sentence
     * @return prefix sentence
     * @throws Exception
     */
    public static String convertInfixToPrefix(String infix) throws Exception {
        Map<String, String> conversionMap = splitBrackets(infix);

        for (String key : conversionMap.keySet()) {
            String value = JniHelper.convertToPrefixNotation(conversionMap.get(key));
            conversionMap.put(key, value);
        }

        String result = stickFragments(conversionMap);
        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }

    /**
     * Count value from postfix sentence.
     *
     * @param postfix
     * @return
     */
    public static double countFromPostfixNotation(String postfix) {
        return JniHelper.countValueFromPostfixNotation(postfix);
    }

    /**
     * Convert infix sentence to postfix sentence.
     *
     * @param infix sentence
     * @return postfix sentence
     * @throws Exception
     */
    public static String convertInfixToPostfix(String infix) throws Exception {
        Map<String, String> conversionMap = splitBrackets(infix);

        for (String key : conversionMap.keySet()) {
            String value = JniHelper.convertToPostfixNotation(conversionMap.get(key));
            conversionMap.put(key, value);
        }

        String result = stickFragments(conversionMap);
        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }

    /**
     * Split sentence into map, use brackets as a divider.
     *
     * @param input
     * @return
     * @throws Exception
     */
    private static Map<String, String> splitBrackets(String input) throws Exception {
        List<Integer> startBrackets = new LinkedList<Integer>();
        List<Integer> endBrackets = new LinkedList<Integer>();
        Map<String, String> resultMap = new LinkedHashMap<String, String>();

        int fromIndex = 0;
        int findIndex = -1;
        // Find all start brackets '('
        while ((findIndex = input.indexOf("(", fromIndex)) != -1) {
            startBrackets.add(findIndex);
            fromIndex = findIndex + 1;
        }

        fromIndex = 0;
        findIndex = -1;
        // Find all end brackets ')'
        while ((findIndex = input.indexOf(")", fromIndex)) != -1) {
            endBrackets.add(findIndex + 1);
            fromIndex = findIndex + 1;
        }

        // Match lists.
        if (startBrackets.size() != endBrackets.size()) {
            throw new Exception("Wrong number of brackets!");
        }

        List<String> resultList = new LinkedList<String>();

        // Cut all brackets from input.
        for (Integer endMark : endBrackets) {
            Integer markNumberToRemove = -1;
            for (int startMark : startBrackets) {
                if (startMark > markNumberToRemove && startMark < endMark) {
                    markNumberToRemove = startMark;
                } else {
                    break;
                }
            }

            if (markNumberToRemove >= 0) {
                startBrackets.remove(markNumberToRemove);
                String bracketSentence = input.substring(markNumberToRemove, endMark);
                if (!resultList.contains(bracketSentence)) {
                    resultList.add(bracketSentence);
                }
            }
        }

        // Put sentences to map and replace common parts.
        for (int index = 1; index <= resultList.size(); index++) {
            String pattern = resultList.get(index - 1);
            resultMap.put(TOKEN_PREFIX + index, pattern);
            for (int innerIndex = index; innerIndex < resultList.size(); innerIndex++) {
                String content = resultList.get(innerIndex);
                content = content.replace(pattern, TOKEN_PREFIX + index);
                resultList.set(innerIndex, content);
            }
        }

        // Remove '(' and ')' chars.
        for (String key : resultMap.keySet()) {
            String value = resultMap.get(key);
            input = input.replace(value, key);
            value = value.replace('(', ' ').replace(')', ' ').trim();
            resultMap.put(key, value);
        }

        resultMap.put(TOKEN_MAIN, input);

        return resultMap;
    }

    /**
     * Stick map into one sentence.
     *
     * @param postfixMap
     * @return
     */
    private static String stickFragments(Map<String, String> postfixMap) {
        for (int index = 1; index < postfixMap.size(); index++) {
            String value = postfixMap.get(TOKEN_PREFIX + index);
            if (value == null) {
                continue;
            }

            for (int innerIndex = index + 1; innerIndex < postfixMap.size(); innerIndex++) {
                String innerValue = postfixMap.get(TOKEN_PREFIX + innerIndex);
                if (innerValue != null) {
                    innerValue = innerValue.replace(TOKEN_PREFIX + index, value);
                    postfixMap.put(TOKEN_PREFIX + innerIndex, innerValue);
                }
            }

            String innerValue = postfixMap.get(TOKEN_MAIN);
            innerValue = innerValue.replace(TOKEN_PREFIX + index, value);
            postfixMap.put(TOKEN_MAIN, innerValue);
        }

        return postfixMap.get(TOKEN_MAIN);
    }
}
