package ozanturcan.com.myapplication.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class SharedPreferenceUtilities {
    private SharedPreferences preferences;
    private Context context;
    private List<String> sharedValueList;
    private String SHARED_LIST_KEY = "SHARED_LIST_KEY";
    public SharedPreferenceUtilities(Context context) {
        this.context = context;
    }

    public void setBooleanShared(String SharedKey, Boolean SharedValue) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SharedKey, SharedValue);
        editor.commit();
    }

    public void setStringShared(String SharedKey, String SharedValue) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SharedKey, SharedValue);
        editor.commit();
    }

    public boolean getBoolenShared(String stringKey) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean booleanValue = preferences.getBoolean(stringKey, false);
        return booleanValue;
    }

    public String getStringShared(String stringKey) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String stringValue = preferences.getString(stringKey, null);
        return stringValue;
    }

    public List<String> getSharedValueList() {
        return sharedValueList;
    }

    public void saveSharedValueList() {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
    }
}
