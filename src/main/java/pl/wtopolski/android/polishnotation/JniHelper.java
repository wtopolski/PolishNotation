package pl.wtopolski.android.polishnotation;

public class JniHelper {
    public static native String convertToNotation(String value);
    public static native double countValueFromNotation(String value);
}
