package pl.wtopolski.android.polishnotation.support.model.rules;

import android.text.TextUtils;

public class DotPrevKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        return checkPrev(position, content);
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
