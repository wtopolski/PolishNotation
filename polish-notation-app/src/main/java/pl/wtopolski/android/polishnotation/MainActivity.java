package pl.wtopolski.android.polishnotation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wtopolski.android.polishnotation.support.JniHelper;
import pl.wtopolski.android.polishnotation.support.NotationUtil;
import pl.wtopolski.android.polishnotation.support.exception.BracketException;
import pl.wtopolski.android.polishnotation.support.model.CountResult;
import pl.wtopolski.android.polishnotation.support.task.CountListener;

public class MainActivity extends Activity implements CountListener {
    private static final Logger LOG = LoggerFactory.getLogger(NotationUtil.class);

    private NotationApplication app;

    private EditText edit;
    private TextView requestText;
    private TextView prefixText;
    private TextView postfixText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        app = (NotationApplication) getApplication();

        edit = (EditText) findViewById(R.id.edit);
        edit.setText("");

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            }
        });

        requestText = (TextView) findViewById(R.id.request);
        prefixText = (TextView) findViewById(R.id.prefix);
        postfixText = (TextView) findViewById(R.id.postfix);
    }

    @Override
    protected void onStart() {
        super.onStart();
        app.setListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        app.setListener(null);
    }

    public void calcButton(View view) {
        int position = edit.getSelectionStart();

        switch (view.getId()) {
            case R.id.calcButton0:
                edit.getText().insert(position, "0");
                count();
                break;
            case R.id.calcButton1:
                edit.getText().insert(position, "1");
                count();
                break;
            case R.id.calcButton2:
                edit.getText().insert(position, "2");
                count();
                break;
            case R.id.calcButton3:
                edit.getText().insert(position, "3");
                count();
                break;
            case R.id.calcButton4:
                edit.getText().insert(position, "4");
                count();
                break;
            case R.id.calcButton5:
                edit.getText().insert(position, "5");
                count();
                break;
            case R.id.calcButton6:
                edit.getText().insert(position, "6");
                count();
                break;
            case R.id.calcButton7:
                edit.getText().insert(position, "7");
                count();
                break;
            case R.id.calcButton8:
                edit.getText().insert(position, "8");
                count();
                break;
            case R.id.calcButton9:
                edit.getText().insert(position, "9");
                count();
                break;
            case R.id.calcButtonDivision:
                edit.getText().insert(position, "/");
                count();
                break;
            case R.id.calcButtonDot:
                edit.getText().insert(position, ".");
                count();
                break;
            case R.id.calcButtonMinus:
                edit.getText().insert(position, "-");
                count();
                break;
            case R.id.calcButtonMultiplication:
                edit.getText().insert(position, "*");
                count();
                break;
            case R.id.calcButtonPlus:
                edit.getText().insert(position, "+");
                count();
                break;
            case R.id.calcButtonBracket:
                String content = edit.getText().toString();
                int length = content.length();

                if (length > 0) {
                    char lastChar = edit.getText().toString().charAt(length - 1);
                    if (lastChar == '-' || lastChar == '+' || lastChar == '*' || lastChar == '/') {
                        edit.getText().insert(position, "(");
                    } else {
                        edit.getText().insert(position, ")");
                    }
                } else {
                    edit.getText().insert(position, "(");
                }
                count();
                break;
            case R.id.calcButtonClear:
                edit.getText().clear();
                count();
                break;
            case R.id.calcButtonBack:
                if (position > 0) {
                    edit.getText().delete(position - 1, position);
                    count();
                }
                break;
        }
    }

    private void count() {
        String request = edit.getText().toString();
        app.makeRequest(request);
    }

    @Override
    public void onResolve(CountResult result) {
        if (result == null) {
            requestText.setText("");
            prefixText.setText("");
            postfixText.setText("");
        } else {
            String request = result.getRequest();
            String postfix = result.getPostfix();
            String prefix = result.getPrefix();
            double resultValue = result.getResult();

            requestText.setText(request + " = " + String.format("%.3f", resultValue));
            prefixText.setText(prefix);
            postfixText.setText(postfix);
        }
    }
}
