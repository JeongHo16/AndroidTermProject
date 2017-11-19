package com.hansung.teamproject.homework1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MenuHelper extends SQLiteOpenHelper {
    final static String TAG="MenuHelper";

    public MenuHelper(Context context) {
        super(context, MenuRegistration.DB_NAME, null, MenuRegistration.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(MenuRegistration.Menu.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(MenuRegistration.Menu.DELETE_TABLE);
        onCreate(db);
    }

    public void insertUserBySQL(String restitle, String image, String title, String price, String description) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (NULL,'%s', '%s', '%s', '%s', '%s')",
                    MenuRegistration.Menu.TABLE_NAME,
                    MenuRegistration.Menu._ID,
                    MenuRegistration.Menu.KEY_RESTITLE,
                    MenuRegistration.Menu.KEY_IMAGE,
                    MenuRegistration.Menu.KEY_TITLE,
                    MenuRegistration.Menu.KEY_PRICE,
                    MenuRegistration.Menu.KEY_DESCRIPTION,
                    restitle, image, title, price, description);

            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + MenuRegistration.Menu.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }

    public void deleteUserBySQL(String _id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s",
                    MenuRegistration.Menu.TABLE_NAME,
                    MenuRegistration.Menu._ID,
                    _id);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }

    public void updateUserBySQL(String _id, String restitle, String image, String title, String price, String description) {
        try {
            String sql = String.format (
                    "UPDATE  %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = %s",
                    MenuRegistration.Menu.TABLE_NAME,
                    MenuRegistration.Menu.KEY_RESTITLE, restitle,
                    MenuRegistration.Menu.KEY_IMAGE, image,
                    MenuRegistration.Menu.KEY_TITLE, title,
                    MenuRegistration.Menu.KEY_PRICE, price,
                    MenuRegistration.Menu.KEY_DESCRIPTION, description,
                    MenuRegistration.Menu._ID, _id) ;
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in updating recodes");
        }
    }

    public long insertUserByMethod(String restitle, String image, String title, String price, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MenuRegistration.Menu.KEY_RESTITLE, restitle);
        values.put(MenuRegistration.Menu.KEY_IMAGE, image);
        values.put(MenuRegistration.Menu.KEY_TITLE, title);
        values.put(MenuRegistration.Menu.KEY_PRICE, price);
        values.put(MenuRegistration.Menu.KEY_DESCRIPTION, description);

        return db.insert(MenuRegistration.Menu.TABLE_NAME,null,values);
    }

    public Cursor getAllUsersByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(MenuRegistration.Menu.TABLE_NAME,
                null,null,null,null,null,null);
    }

    public long deleteUserByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = MenuRegistration.Menu._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(MenuRegistration.Menu.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateUserByMethod(String restitle, String _id, String image, String title, String price, String description) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MenuRegistration.Menu.KEY_RESTITLE, restitle);
        values.put(MenuRegistration.Menu.KEY_IMAGE, image);
        values.put(MenuRegistration.Menu.KEY_TITLE, title);
        values.put(MenuRegistration.Menu.KEY_PRICE, price);
        values.put(MenuRegistration.Menu.KEY_DESCRIPTION, description);

        String whereClause = MenuRegistration.Menu._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(MenuRegistration.Menu.TABLE_NAME, values, whereClause, whereArgs);
    }
}
