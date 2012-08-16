package pl.wtopolski.android.ppn;

public class JniHelper {
    public static native String convertToPrefixNotation(String value);
    public static native double countValueFromPrefixNotation(String value);
}
