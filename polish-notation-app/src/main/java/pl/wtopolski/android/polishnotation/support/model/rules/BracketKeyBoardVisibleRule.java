package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;
import android.util.Log;
import pl.wtopolski.android.polishnotation.support.view.KeyBoard;

public class BracketKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        String prev = getPrevChar(position, content);

        boolean first = true;
        if (prev != null) {
            if (TextUtils.isDigitsOnly(prev)) {
                // ...9|...
                first = false;
            } else if (KeyBoard.SPECIAL_CHAR_DOT.equals(prev)) {
                // ...,|...
                first = false;
            } else if (KeyBoard.SPECIAL_CHAR_END_BRACKET.equals(prev)) {
                // ...)|...
                first = false;
            }
        }

        boolean second = true;
        if (prev != null) {
            boolean isBracketOpen = isBracketOpen(content, position);
            if (!isBracketOpen) {
                second = false;
            } else if (valueIsOperation(prev)) {
                // ...+|...
                second = false;
            } else if (KeyBoard.SPECIAL_CHAR_START_BRACKET.equals(prev)) {
                // ...(|...
                second = false;
            }
        }

        if (!first && !second) {
            return false;
        }

        return true;
    }

    public static boolean isBracketOpen(String content, int position) {
        String prevContent = content.substring(0, position);
        Log.d("wtopolski", prevContent);

        int start = 0;
        int stop = 0;
        for (int i = 0; i < prevContent.length(); i++) {
            char c = prevContent.charAt(i);
            if ('(' == c) {
                start++;
            } else if (')' == c) {
                stop++;
            }
        }

        Log.d("wtopolski", "start: " + start);
        Log.d("wtopolski", "stop: " + stop);

        return start > stop;
    }
}
