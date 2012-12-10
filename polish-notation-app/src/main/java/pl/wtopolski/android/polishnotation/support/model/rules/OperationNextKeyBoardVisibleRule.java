package pl.wtopolski.android.polishnotation.support.model.rules;

import pl.wtopolski.android.polishnotation.support.view.KeyBoard;

public class OperationNextKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        String next = getNextChar(position, content);
        String secondNext = getNextChar(position, content, 1);

        if (KeyBoard.SPECIAL_CHAR_DIVISION.equals(next)) {
            return false;
        } else if (KeyBoard.SPECIAL_CHAR_PLUS.equals(next)) {
            return false;
        } else if (KeyBoard.SPECIAL_CHAR_MULTIPLICATION.equals(next)) {
            return false;
        } else if (KeyBoard.SPECIAL_CHAR_MULTIPLICATION.equals(next) && KeyBoard.SPECIAL_CHAR_MULTIPLICATION.equals(secondNext)) {
            return false;
        }

        return true;
    }
}
