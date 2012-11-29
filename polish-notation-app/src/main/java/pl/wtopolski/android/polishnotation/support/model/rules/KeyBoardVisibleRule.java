package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;

import static pl.wtopolski.android.polishnotation.support.view.KeyBoard.*;
import static pl.wtopolski.android.polishnotation.support.view.KeyBoard.SPECIAL_CHAR_MINUS;
import static pl.wtopolski.android.polishnotation.support.view.KeyBoard.SPECIAL_CHAR_PLUS;

public abstract class KeyBoardVisibleRule {
    public abstract boolean pass(int position, String content);

    protected String getPrevChar(int position, String content) {
        if (position > 0) {
            return String.valueOf(content.charAt(position - 1));
        }

        return null;
    }

    protected String getPrevChar(int position, String content, int deep) {
        if (position > deep) {
            return String.valueOf(content.charAt(position - 1 - deep));
        }

        return null;
    }

    protected String getNextChar(int position, String content) {
        if (position < content.length()) {
            return String.valueOf(content.charAt(position));
        }

        return null;
    }

    protected String getNextChar(int position, String content, int deep) {
        if ((position + deep) < content.length()) {
            return String.valueOf(content.charAt(position + deep));
        }

        return null;
    }

    protected boolean valueIsBracket(String value) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }

        return  SPECIAL_CHAR_START_BRACKET.equals(value)
                || SPECIAL_CHAR_END_BRACKET.equals(value);
    }

    protected boolean valueIsOperation(String value) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }

        return  SPECIAL_CHAR_DIVISION.equals(value)
                || SPECIAL_CHAR_MULTIPLICATION.equals(value)
                || SPECIAL_CHAR_MINUS.equals(value)
                || SPECIAL_CHAR_PLUS.equals(value);
    }
}
