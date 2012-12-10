package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;

public class InsertKeyBoardButton extends KeyBoardButton {
    private String content;
    private Button button;

    public InsertKeyBoardButton(View parent, int buttonId, String content, int widthInPixels, int heightInPixels) {
        this.buttonId = buttonId;
        this.content = content;
        button = (Button) parent.findViewById(buttonId);
        setViewSize(button, widthInPixels, heightInPixels);
    }

    @Override
    public int execute(Editable editable, int position) {
        editable.insert(position, content);
        return position + 1;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(button, enabled);
    }
}
