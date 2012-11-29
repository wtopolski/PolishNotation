package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;

import static pl.wtopolski.android.polishnotation.support.view.KeyBoard.*;

public class MinusMiddleKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        String prev = getPrevChar(position, content);
        String next = getNextChar(position, content);

        if (valueIsOperation(prev) && valueIsOperation(next)) {
            return false;
        }

        if (valueIsOperation(prev) && valueIsBracket(next)) {
            return false;
        }

        if (valueIsBracket(prev) && valueIsOperation(next)) {
            return false;
        }

        return true;
    }
}
