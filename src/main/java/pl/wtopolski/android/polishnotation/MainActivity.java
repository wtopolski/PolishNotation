package pl.wtopolski.android.polishnotation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText edit;
    private TextView test;

    static {
        System.loadLibrary("polish-notation");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edit = (EditText) findViewById(R.id.edit);
        edit.setText("1*0.5/3/8-4+3*10-5");

        test = (TextView) findViewById(R.id.test);
        test.setTextSize(22);
        test.setPadding(4, 4, 4, 4);
        test.setTextScaleX(0.9f);
    }

    public void action(View view) {
        String request = edit.getText().toString();
        String response = JniHelper.convertToNotation(request);
        double result = JniHelper.countValueFromNotation(response);

        test.setText("request: " + request + "\nresponse: " + response + "\nresult: " + result);
    }
}
