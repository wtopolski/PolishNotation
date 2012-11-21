package pl.wtopolski.android.polishnotation.support.storage;

import android.content.Context;
import android.content.SharedPreferences;
import pl.wtopolski.android.polishnotation.NotationApplication;

public class Properties {
    private static final String EDIT_VALUE = "edit_value";
    private static final String EDIT_VALUE_POSITION = "edit_value_position";
    public static final String PREFS_NAME = "PolishNotationFile";

    public static String getEditValue() {
        Context context = NotationApplication.getContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(EDIT_VALUE, "");
    }

    public static void setEditValue(String value) {
        Context context = NotationApplication.getContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putString(EDIT_VALUE, value).commit();
    }

    public static int getEditValuePosition() {
        Context context = NotationApplication.getContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt(EDIT_VALUE_POSITION, 0);
    }

    public static void setEditValuePosition(int value) {
        Context context = NotationApplication.getContext();
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        settings.edit().putInt(EDIT_VALUE_POSITION, value).commit();
    }
}
