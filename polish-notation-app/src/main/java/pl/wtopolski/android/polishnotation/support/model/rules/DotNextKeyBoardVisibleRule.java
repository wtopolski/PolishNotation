package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;

public class DotNextKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        return checkNext(position, content);
    }

    private boolean checkNext(int position, String content) {
        String singleChar = getNextChar(position, content);
        if (singleChar != null && (TextUtils.isDigitsOnly(singleChar) || singleChar.equals("."))) {
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
}
