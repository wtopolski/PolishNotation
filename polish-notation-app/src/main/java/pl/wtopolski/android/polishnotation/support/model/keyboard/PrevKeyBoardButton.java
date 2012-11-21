package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.widget.ImageButton;

public class PrevKeyBoardButton extends KeyBoardButton {
    private ImageButton button;

    public PrevKeyBoardButton(View parent, int buttonId) {
        this.buttonId = buttonId;
        button = (ImageButton) parent.findViewById(buttonId);
    }

    @Override
    public int execute(Editable editable, int position) {
        if (position > 0) {
            position--;
        }
        return position;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(button, enabled);
    }
}
