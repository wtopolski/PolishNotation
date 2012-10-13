package pl.wtopolski.android.polishnotation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("polish-notation");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String request = "-1.1+3/2+5+6.5";
        String response = JniHelper.convertToNotation(request);
        double result = JniHelper.countValueFromNotation(response);

        TextView tv = (TextView) findViewById(R.id.test);
        tv.setTextSize(22);
        tv.setPadding(4, 4, 4, 4);
        tv.setTextScaleX(0.9f);
        tv.setText("request: " + request + "\nresponse: " + response + "\nresult: " + result);
    }
}
