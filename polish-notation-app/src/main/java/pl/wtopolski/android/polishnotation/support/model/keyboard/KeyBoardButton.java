package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.text.Editable;
import android.view.View;
import android.view.ViewPropertyAnimator;

public abstract class KeyBoardButton {
    protected int buttonId;

    public int getButtonId() {
        return buttonId;
    }

    public void setEnabled(View view, boolean enabled) {
        int rotation = (enabled) ? 0 : 180;

        ViewPropertyAnimator animator = view.animate();
        animator.rotationX(rotation);
        animator.start();

        view.setEnabled(enabled);
    }

    public abstract int execute(Editable editable, int position);
    public abstract void setEnabled(boolean enabled);
}
