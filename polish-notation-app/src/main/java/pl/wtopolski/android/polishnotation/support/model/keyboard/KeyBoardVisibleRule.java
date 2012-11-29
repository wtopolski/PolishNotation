package pl.wtopolski.android.polishnotation.support.model.keyboard;

public abstract class KeyBoardVisibleRule {
    public abstract boolean pass(int position, String content);

    protected String getPrevChar(int position, String content) {
        if (position > 0) {
            return String.valueOf(content.charAt(position - 1));
        }

        return null;
    }

    protected String getNextChar(int position, String content) {
        if (position < content.length()) {
            return String.valueOf(content.charAt(position));
        }

        return null;
    }
}
