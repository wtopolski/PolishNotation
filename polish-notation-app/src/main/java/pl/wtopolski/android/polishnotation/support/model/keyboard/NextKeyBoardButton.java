package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.widget.ImageButton;

public class NextKeyBoardButton extends KeyBoardButton {
    private ImageButton button;

    public NextKeyBoardButton(View parent, int buttonId, int widthInPixels, int heightInPixels) {
        this.buttonId = buttonId;
        button = (ImageButton) parent.findViewById(buttonId);
        setViewSize(button, widthInPixels, heightInPixels);
    }

    @Override
    public int execute(Editable editable, int position) {
        if (position < editable.length()) {
            position++;
        }
        return position;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(button, enabled);
    }
}
