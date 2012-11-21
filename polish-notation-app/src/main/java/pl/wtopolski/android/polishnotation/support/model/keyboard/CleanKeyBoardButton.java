package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.widget.Button;

public class CleanKeyBoardButton implements KeyBoardButton {
    private int buttonId;
    private Button button;

    public CleanKeyBoardButton(View parent, int buttonId) {
        this.buttonId = buttonId;
        button = (Button) parent.findViewById(buttonId);
    }

    @Override
    public int getButtonId() {
        return buttonId;
    }

    @Override
    public int execute(Editable editable, int position) {
        editable.clear();
        return 0;
    }

    @Override
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }
}
