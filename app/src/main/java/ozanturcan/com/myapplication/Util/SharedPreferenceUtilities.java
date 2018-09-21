package ozanturcan.com.myapplication.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ozanturcan.com.myapplication.Modal.TaskTodo;

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
    public void setTasksToSharedPrefs(List<TaskTodo> todoObervables) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(todoObervables);
        prefsEditor.putString("currentTasks", json);
        prefsEditor.commit();
    }
    public List<TaskTodo> getTasksFromSharedPrefs() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        Gson gson = new Gson();
        String json = appSharedPrefs.getString("currentTasks", "");
        List<TaskTodo> tasks = gson.fromJson(json, new TypeToken<ArrayList<TaskTodo>>() {
        }.getType());
        return tasks;
    }
}
