package pl.wtopolski.android.polishnotation.support.model.keyboard;

import java.util.LinkedList;
import java.util.List;

public class KeyBoardGroup {
    private List<KeyBoardButton> buttons;
    private List<KeyBoardVisibleRule> rules;

    public KeyBoardGroup() {
        buttons = new LinkedList<KeyBoardButton>();
        rules = new LinkedList<KeyBoardVisibleRule>();
    }

    public void addButton(KeyBoardButton button) {
        buttons.add(button);
    }

    public void addRule(KeyBoardVisibleRule rule) {
        rules.add(rule);
    }

    public void valid(int position, String content) {
        boolean enabled = true;
        for (KeyBoardVisibleRule rule : rules) {
            if (!rule.pass(position, content)) {
                enabled = false;
                break;
            }
        }
        setEnabled(enabled);
    }

    private void setEnabled(boolean enabled) {
        for (KeyBoardButton button : buttons) {
            button.setEnabled(enabled);
        }
    }

    public List<KeyBoardButton> getButtons() {
        return buttons;
    }
}
