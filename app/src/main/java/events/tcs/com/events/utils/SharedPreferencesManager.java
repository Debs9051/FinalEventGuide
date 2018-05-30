package events.tcs.com.events.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arun on 7/30/2017.
 */

public class SharedPreferencesManager {

    public static void saveSharedPref(Activity activity, String key, String value){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readSharedPref(Activity activity, String key){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }
}
