package ie.dynamickickboxing.kickboxing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andrewcullen on 16/03/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "schedule.db";
    public static final String TABLE_NAME= "schedule_data";

    public static final String COL_1= "Group";
    public static final String COL_2= "Mon";
    public static final String COL_3= "Tue";
    public static final String COL_4= "Wed";
    public static final String COL_5= "Thur";
    public static final String COL_6= "Fri";
    public static final String COL_7= "Sat/Sun";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (COL_1 TEXT PRIMARY KEY, COL_2 TEXT, COL_3 TEXT  ,COL_4 TEXT  ,COL_5 TEXT ,COL_6 TEXT , COL_7 TEXT   ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME );
      onCreate(db);
    }


    public boolean insertData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
      return(true);
    }

}
