package in.aayushbest.wirelessattendance.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import in.aayushbest.wirelessattendance.R;

public class StaffDbHelper extends SQLiteOpenHelper {
    public static final String TAG="in.aayushbest.wirelessattendance.StaffDbHelper";
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="WirelessAttendance.db";
    public static final String SQL_CREATE_ENTRIES="CREATE TABLE "+ StaffContract.StaffEntry.TABLE_NAME
            +"("+ StaffContract.StaffEntry._ID+" TEXT PRIMARY KEY,"+
            StaffContract.StaffEntry.COLUMN_NAME_NAME+" TEXT,"+
            StaffContract.StaffEntry.COLUMN_NAME_DESIGNATION+" TEXT,"+
            StaffContract.StaffEntry.COLUMN_NAME_DEPARTMENT+" TEXT,"+
            StaffContract.StaffEntry.COLUMN_NAME_COLLEGE+" TEXT,"+
            StaffContract.StaffEntry.COLUMN_NAME_MOBILE+" BIGINT(10),"+
            StaffContract.StaffEntry.COLUMN_NAME_AUTH_METHOD+" TEXT)";
    public static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXIST"+ StaffContract.StaffEntry.TABLE_NAME;


    private StaffDbHelper mDbHelper;
    private Context mCtx;
    public StaffDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mCtx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

    }
    public Context getContext(){
        return mCtx;
    }
    public void addStaffDetails(String id,String name,String designation,String department,String college,String mobile,String auth_method){
        mDbHelper=new StaffDbHelper(getContext());
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(StaffContract.StaffEntry._ID,id);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_NAME,name);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_DESIGNATION,designation);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_DEPARTMENT,department);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_COLLEGE,college);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_MOBILE,mobile);
        values.put(StaffContract.StaffEntry.COLUMN_NAME_AUTH_METHOD,auth_method);
        long uniqueId=db.insert(StaffContract.StaffEntry.TABLE_NAME,null,values);

    }

    public HashMap<String,String> getStaffDetails(){
        mDbHelper=new StaffDbHelper(getContext());
        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        HashMap<String,String> member=new HashMap<String,String>();
        String query="SELECT * FROM "+ StaffContract.StaffEntry.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.getCount()>0){
            member.put("hardware_id",cursor.getString(1));
            member.put("name",cursor.getString(2));
            member.put("designation",cursor.getString(3));
            member.put("department",cursor.getString(4));
            member.put("college",cursor.getString(5));
            member.put("mobile",cursor.getString(6));
            member.put("auth_method",cursor.getString(7));
        }
        cursor.close();
        db.close();
        return member;
    }


}
