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
        test.setTextSize(18);
        test.setPadding(4, 4, 4, 4);
        test.setTextScaleX(0.9f);
    }

    public void action(View view) {
        String request = edit.getText().toString();
        String responsePostfix = null;
        String responsePrefix = null;
        LOG.debug("Infix: {}", request);

        try {
            responsePostfix = NotationUtil.convertInfixToPostfix(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            responsePrefix = NotationUtil.convertInfixToPrefix(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOG.debug("Postfix request: {}", responsePostfix);
        LOG.debug("Prefix request: {}", responsePrefix);
        double resultPostfix = NotationUtil.countFromPostfixNotation(responsePostfix);
        double resultPrefix = NotationUtil.countFromPrefixNotation(responsePrefix);
        LOG.debug("Count result: {}", resultPostfix);
        LOG.debug("Count result: {}", resultPrefix);

        test.setText("request:\n" + request + "\npostfix:\n" + responsePostfix + "\nprefix:\n" + responsePrefix + "\nresultPostfix:\n" + resultPostfix + "\nresultPrefix:\n" + resultPrefix);
    }
}
