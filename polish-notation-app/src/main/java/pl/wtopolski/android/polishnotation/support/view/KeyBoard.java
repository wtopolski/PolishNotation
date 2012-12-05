package pl.wtopolski.android.polishnotation.support.view;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import pl.wtopolski.android.polishnotation.R;
import pl.wtopolski.android.polishnotation.support.model.keyboard.*;
import pl.wtopolski.android.polishnotation.support.model.rules.*;

import java.util.LinkedList;
import java.util.List;

public class KeyBoard extends GridLayout {
    private List<KeyBoardButton> buttons;
    private List<KeyBoardGroup> groups;

    public static final String SPECIAL_CHAR_MINUS = "-";
    public static final String SPECIAL_CHAR_PLUS = "+";
    public static final String SPECIAL_CHAR_DIVISION = "/";
    public static final String SPECIAL_CHAR_MULTIPLICATION = "*";
    public static final String SPECIAL_CHAR_DOT = ".";
    public static final String SPECIAL_CHAR_START_BRACKET = "(";
    public static final String SPECIAL_CHAR_END_BRACKET = ")";

    public KeyBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }

    public KeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public KeyBoard(Context context) {
        super(context);
        initLayout();
    }

    private void initLayout() {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            view = inflater.inflate(R.layout.keyboard, this);
        }

        buttons = new LinkedList<KeyBoardButton>();
        groups = new LinkedList<KeyBoardGroup>();
        KeyBoardGroup group = null;

        // '(' ')'
        group = new KeyBoardGroup();
        group.addButton(new BracketKeyBoardButton(view, R.id.calcButtonBracket));
        // TODO rules
        groups.add(group);
        buttons.addAll(group.getButtons());

        // '+' '/' '*'
        group = new KeyBoardGroup();
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButtonDivision, SPECIAL_CHAR_DIVISION));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButtonMultiplication, SPECIAL_CHAR_MULTIPLICATION));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButtonPlus, SPECIAL_CHAR_PLUS));
        group.addRule(new OperationKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // '-'
        group = new KeyBoardGroup();
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButtonMinus, SPECIAL_CHAR_MINUS));
        group.addRule(new MinusPrevKeyBoardVisibleRule());
        group.addRule(new MinusMiddleKeyBoardVisibleRule());
        group.addRule(new MinusNextKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // Create number group.
        group = new KeyBoardGroup();
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton0, "0"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton1, "1"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton2, "2"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton3, "3"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton4, "4"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton5, "5"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton6, "6"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton7, "7"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton8, "8"));
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButton9, "9"));
        group.addRule(new NumberKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // '.'
        group = new KeyBoardGroup();
        group.addButton(new InsertKeyBoardButton(view, R.id.calcButtonDot, SPECIAL_CHAR_DOT));
        group.addRule(new DotNextKeyBoardVisibleRule());
        group.addRule(new DotPrevKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // '<-'
        group = new KeyBoardGroup();
        group.addButton(new PrevKeyBoardButton(view, R.id.calcButtonPrev));
        group.addRule(new PrevKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // '->'
        group = new KeyBoardGroup();
        group.addButton(new NextKeyBoardButton(view, R.id.calcButtonNext));
        group.addRule(new NextKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // Clean
        group = new KeyBoardGroup();
        group.addButton(new CleanKeyBoardButton(view, R.id.calcButtonClear));
        group.addRule(new CleanKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());

        // Back
        group = new KeyBoardGroup();
        group.addButton(new BackKeyBoardButton(view, R.id.calcButtonBack));
        group.addRule(new BackKeyBoardVisibleRule());
        groups.add(group);
        buttons.addAll(group.getButtons());
    }

    public void onEditSelection(int position, String content) {
        for (KeyBoardGroup group : groups) {
            group.valid(position, content);
        }
    }

    public void onClick(int buttonId, EditText edit) {
        int position = edit.getSelectionStart();
        Editable editable = edit.getText();

        for (KeyBoardButton button : buttons) {
            if (button.getButtonId() == buttonId) {
                position = button.execute(editable, position);
                edit.setSelection(position);
                onEditSelection(position, editable.toString());
                break;
            }
        }
    }
}
