package pl.wtopolski.android.polishnotation.support.model.rules;

public class NextKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        if (position < content.length()) {
            return true;
        }

        return false;
    }
}
