package pl.wtopolski.android.polishnotation.support.model.rules;

import static pl.wtopolski.android.polishnotation.support.view.KeyBoard.*;

public class NumberKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        String prev = getPrevChar(position, content);
        if (SPECIAL_CHAR_END_BRACKET.equals(prev)) {
            return false;
        }

        return true;
    }
}
