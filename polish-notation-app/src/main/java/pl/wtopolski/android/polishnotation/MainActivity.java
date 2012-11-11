package pl.wtopolski.android.polishnotation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.wtopolski.android.polishnotation.support.JniHelper;
import pl.wtopolski.android.polishnotation.support.NotationUtil;
import pl.wtopolski.android.polishnotation.support.exception.BracketException;

public class MainActivity extends Activity {
    private static final Logger LOG = LoggerFactory.getLogger(NotationUtil.class);

    private EditText edit;
    private TextView test;

    static {
        System.loadLibrary("polish-notation-app");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edit = (EditText) findViewById(R.id.edit);
        edit.setText("(1*0.5)/(3+11-(9*1))/8-4+3*(10-5)");

        test = (TextView) findViewById(R.id.test);
    }

    public void action(View view) {
        String request = edit.getText().toString();
        String responsePostfix = null;
        String responsePrefix = null;
        double resultPostfix = 0f;
        double resultPrefix = 0f;

        LOG.debug("Infix: {}", request);

        try {
            responsePostfix = NotationUtil.convertInfixToPostfix(request);
            LOG.debug("Postfix request: {}", responsePostfix);
            resultPostfix = NotationUtil.countFromPostfixNotation(responsePostfix);
            LOG.debug("Count result: {}", resultPostfix);
        } catch (BracketException e) {
            e.printStackTrace();
        }

        try {
            responsePrefix = NotationUtil.convertInfixToPrefix(request);
            LOG.debug("Prefix request: {}", responsePrefix);
            resultPrefix = NotationUtil.countFromPrefixNotation(responsePrefix);
            LOG.debug("Count result: {}", resultPrefix);
        } catch (BracketException e) {
            e.printStackTrace();
        }

        test.setText("request:\n" + request + "\npostfix:\n" + responsePostfix + "\nprefix:\n" + responsePrefix + "\nresultPostfix:\n" + resultPostfix + "\nresultPrefix:\n" + resultPrefix);
    }
}
