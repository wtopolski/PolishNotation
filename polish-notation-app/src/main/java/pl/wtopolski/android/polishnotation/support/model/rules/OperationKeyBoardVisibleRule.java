package pl.wtopolski.android.polishnotation.support.model.rules;

public class OperationKeyBoardVisibleRule extends KeyBoardVisibleRule {
    @Override
    public boolean pass(int position, String content) {
        if (position > 0) {
            String prev = getPrevChar(position, content);
            String next = getNextChar(position, content);

            if (prev != null && prev.matches("[\\+*/]")) {
                return false;
            }

            if (next != null && next.matches("[\\+*/]")) {
                return false;
            }

            return true;
        }

        return false;
    }
}
