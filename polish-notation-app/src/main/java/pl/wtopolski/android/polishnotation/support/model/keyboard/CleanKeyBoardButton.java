package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CleanKeyBoardButton extends KeyBoardButton {
    private ImageButton button;

    public CleanKeyBoardButton(View parent, int buttonId, int widthInPixels, int heightInPixels) {
        this.buttonId = buttonId;
        button = (ImageButton) parent.findViewById(buttonId);
        setViewSize(button, widthInPixels, heightInPixels);
    }

    @Override
    public int execute(Editable editable, int position) {
        editable.clear();
        return 0;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(button, enabled);
    }
}
