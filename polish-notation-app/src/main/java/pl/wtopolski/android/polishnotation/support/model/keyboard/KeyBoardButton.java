package pl.wtopolski.android.polishnotation.support.model.keyboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.text.Editable;
import android.view.View;
import android.view.ViewPropertyAnimator;

public abstract class KeyBoardButton {
    protected int buttonId;

    public int getButtonId() {
        return buttonId;
    }

    public void setEnabled(final View view, boolean enabled) {
        ViewPropertyAnimator animator = view.animate();
        animator.setDuration(300);

        if (enabled) {
            animator.alpha(1f);
            animator.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animator) {
                    view.setEnabled(true);
                }
            });
        } else {
            animator.alpha(0.7f);
            animator.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animator) {
                    view.setEnabled(false);
                }
            });
        }
        animator.start();
    }

    public abstract int execute(Editable editable, int position);
    public abstract void setEnabled(boolean enabled);
}
