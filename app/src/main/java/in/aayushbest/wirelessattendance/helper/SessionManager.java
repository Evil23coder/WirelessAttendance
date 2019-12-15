package in.aayushbest.wirelessattendance.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String TAG=SessionManager.class.getSimpleName();
    public static final String PREF_NAME="Attedance_dashboard";
    public static final String KEY_NAME="isRegistered";
    public static final String KEY_TYPE="authMethod";
    private Context mContext;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;
    public SessionManager(Context context){
        mContext=context;
        mPreference=context.getSharedPreferences(PREF_NAME,context.MODE_PRIVATE);
        mEditor=mPreference.edit();

    }
    public void setRegistered(boolean isRegistered){
        mEditor.putBoolean(KEY_NAME,isRegistered);
        mEditor.commit();
    }
    public boolean isRegistered(){
        return mPreference.getBoolean(KEY_NAME,false);
    }
    public void setAuthenticatedType(String authType){
        mEditor.putString(KEY_TYPE,authType);
        mEditor.commit();
    }
    public  String getAuthenticationType(){
        return  mPreference.getString(KEY_TYPE,"fingerprint");
    }









































}
