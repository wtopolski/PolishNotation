package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;
import pl.wtopolski.android.polishnotation.support.model.keyboard.KeyBoardVisibleRule;

public class DotKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        boolean prevState = checkPrev(position, content);
        boolean nextState = checkNext(position, content);
        return prevState && nextState;
    }

    private boolean checkNext(int position, String content) {
        String singleChar = getNextChar(position, content);
        if (singleChar != null && TextUtils.isDigitsOnly(singleChar)) {
            for (int index = position; index < content.length(); index++) {
                singleChar = String.valueOf(content.charAt(index));
                if (TextUtils.isDigitsOnly(singleChar)) {
                    continue;
                } else if (singleChar.equals(".")) {
                    // ex. 1|2.3
                    return false;
                } else {
                    // ex. 1|23+
                    return true;
                }
            }
        }

        // ex. dot is the last char
        return true;
    }

    private boolean checkPrev(int position, String content) {
        String singleChar = getPrevChar(position, content);
        if (singleChar != null && TextUtils.isDigitsOnly(singleChar)) {
            for (int index = position - 1; index >= 0; index--) {
                singleChar = String.valueOf(content.charAt(index));
                if (TextUtils.isDigitsOnly(singleChar)) {
                    continue;
                } else if (singleChar.equals(".")) {
                    // ex. 1.23|
                    return false;
                } else {
                    // ex. -123|
                    return true;
                }
            }
            // ex. 123|
            return true;
        }

        // ex. 123+|
        return false;
    }
}
