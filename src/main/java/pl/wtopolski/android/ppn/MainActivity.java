package pl.wtopolski.android.ppn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("polish-prefix-notation");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String o = JniHelper.convertToPrefixNotation("1 + ( 3 / 2 ) + 5");
        double  result = JniHelper.countValueFromPrefixNotation(o);

        TextView tv = (TextView) findViewById(R.id.test);
        tv.setText("result: " + result + " [" + o + "]");
    }
}
