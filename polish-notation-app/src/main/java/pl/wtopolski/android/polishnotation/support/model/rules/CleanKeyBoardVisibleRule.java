package pl.wtopolski.android.polishnotation.support.model.rules;

public class CleanKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        if (content.length() > 0) {
            return true;
        }

        return false;
    }
}
