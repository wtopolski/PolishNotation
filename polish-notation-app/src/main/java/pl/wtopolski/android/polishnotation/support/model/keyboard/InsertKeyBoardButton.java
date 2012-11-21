package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;

public class InsertKeyBoardButton implements KeyBoardButton {
    private int buttonId;
    private String content;
    private Button button;

    public InsertKeyBoardButton(View parent, int buttonId, String content) {
        this.buttonId = buttonId;
        this.content = content;
        button = (Button) parent.findViewById(buttonId);
    }

    @Override
    public int getButtonId() {
        return buttonId;
    }

    @Override
    public int execute(Editable editable, int position) {
        editable.insert(position, content);
        return position + 1;
    }

    @Override
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }
}
