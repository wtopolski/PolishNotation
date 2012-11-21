package pl.wtopolski.android.polishnotation.support.model.rules;

public class PrevKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        if (position > 0) {
            return true;
        }

        return false;
    }
}
