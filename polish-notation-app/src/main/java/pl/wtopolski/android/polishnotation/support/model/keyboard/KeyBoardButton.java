package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;

public interface KeyBoardButton {
    int getButtonId();
    int execute(Editable editable, int position);
    void setEnabled(boolean enabled);
}
