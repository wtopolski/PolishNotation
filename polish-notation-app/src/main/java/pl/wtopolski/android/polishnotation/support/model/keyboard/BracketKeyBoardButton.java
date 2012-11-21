package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.widget.Button;

public class BracketKeyBoardButton extends KeyBoardButton {
    private Button button;

    public BracketKeyBoardButton(View parent, int buttonId) {
        this.buttonId = buttonId;
        button = (Button) parent.findViewById(buttonId);
    }

    @Override
    public int execute(Editable editable, int position) {
        String content = editable.toString();
        int length = content.length();

        if (length > 0) {
            char lastChar = content.charAt(length - 1);
            if (lastChar == '-' || lastChar == '+' || lastChar == '*' || lastChar == '/') {
                editable.insert(position, "(");
            } else {
                editable.insert(position, ")");
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
