package pl.wtopolski.android.polishnotation.support.model.rules;

import pl.wtopolski.android.polishnotation.support.view.KeyBoard;

public class OperationPrevKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        String prev = getPrevChar(position, content);

        if (prev == null) {
            return false;
        } else if (valueIsOperation(prev)) {
            return false;
        } else if (KeyBoard.SPECIAL_CHAR_START_BRACKET.equals(prev)) {
            return false;
        }

        return true;
    }
}
