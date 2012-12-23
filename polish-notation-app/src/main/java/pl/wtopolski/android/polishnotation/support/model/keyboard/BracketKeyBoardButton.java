package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import pl.wtopolski.android.polishnotation.support.model.rules.BracketKeyBoardVisibleRule;
import pl.wtopolski.android.polishnotation.support.model.rules.KeyBoardVisibleRule;
import pl.wtopolski.android.polishnotation.support.view.KeyBoard;

public class BracketKeyBoardButton extends KeyBoardButton {
    private Button button;

    public BracketKeyBoardButton(View parent, int buttonId, int widthInPixels, int heightInPixels) {
        this.buttonId = buttonId;
        button = (Button) parent.findViewById(buttonId);
        setViewSize(button, widthInPixels, heightInPixels);
    }

    @Override
    public int execute(Editable editable, int position) {
        String content = editable.toString();

        if (position > 0) {
            String prevChar = String.valueOf(content.charAt(position - 1));
            boolean isBracketOpen = BracketKeyBoardVisibleRule.isBracketOpen(content, position);

            if (KeyBoardVisibleRule.valueIsOperation(prevChar)) {
                // - + / *
                editable.insert(position, "(");
            } else if (KeyBoard.SPECIAL_CHAR_START_BRACKET.equals(prevChar)) {
                // (
                editable.insert(position, "(");
            } else if (isBracketOpen && TextUtils.isDigitsOnly(prevChar)) {
                // [0-9]
                editable.insert(position, ")");
            } else if (isBracketOpen && KeyBoard.SPECIAL_CHAR_DOT.equals(prevChar)) {
                // ,
                editable.insert(position, ")");
            } else if (isBracketOpen && KeyBoard.SPECIAL_CHAR_END_BRACKET.equals(prevChar)) {
                // )
                editable.insert(position, ")");
            } else {
                return position;
            }
        } else {
            editable.insert(position, "(");
        }

        return position + 1;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(button, enabled);
    }
}
