package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageButton;

public class BackKeyBoardButton implements KeyBoardButton {
    private int buttonId;
    private ImageButton button;

    public BackKeyBoardButton(View parent, int buttonId) {
        this.buttonId = buttonId;
        button = (ImageButton) parent.findViewById(buttonId);
    }

    @Override
    public int getButtonId() {
        return buttonId;
    }

    @Override
    public int execute(Editable editable, int position) {
        if (position > 0) {
            editable.delete(position - 1, position);
            position--;
        }

        return position;
    }

    @Override
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }
}
