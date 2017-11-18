package com.hansung.teamproject.homework1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ResHelper extends SQLiteOpenHelper {
    final static String TAG="ResHelper";

    public ResHelper(Context context) {
        super(context, RestaurantRegistration.DB_NAME, null, RestaurantRegistration.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(RestaurantRegistration.Res.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(RestaurantRegistration.Res.DELETE_TABLE);
        onCreate(db);
    }

    public void insertUserBySQL(String image, String title, String address, String phone) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (NULL, '%s', '%s', '%s', '%s')",
                    RestaurantRegistration.Res.TABLE_NAME,
                    RestaurantRegistration.Res._ID,
                    RestaurantRegistration.Res.KEY_IMAGE,
                    RestaurantRegistration.Res.KEY_TITLE,
                    RestaurantRegistration.Res.KEY_ADDRESS,
                    RestaurantRegistration.Res.KEY_PHONE,
                    image, title, address, phone);

            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + RestaurantRegistration.Res.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }

    public void deleteUserBySQL(String _id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s",
                    RestaurantRegistration.Res.TABLE_NAME,
                    RestaurantRegistration.Res._ID,
                    _id);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }

    public void updateUserBySQL(String _id, String image, String title, String address, String phone) {
        try {
            String sql = String.format (
                    "UPDATE  %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = %s",
                    RestaurantRegistration.Res.TABLE_NAME,
                    RestaurantRegistration.Res.KEY_IMAGE, image,
                    RestaurantRegistration.Res.KEY_TITLE, phone,
                    RestaurantRegistration.Res.KEY_ADDRESS, address,
                    RestaurantRegistration.Res.KEY_PHONE, phone,
                    RestaurantRegistration.Res._ID, _id) ;
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in updating recodes");
        }
    }

    public long insertUserByMethod(String image, String title, String address, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RestaurantRegistration.Res.KEY_IMAGE, image);
        values.put(RestaurantRegistration.Res.KEY_TITLE, title);
        values.put(RestaurantRegistration.Res.KEY_ADDRESS, address);
        values.put(RestaurantRegistration.Res.KEY_PHONE, phone);

        return db.insert(RestaurantRegistration.Res.TABLE_NAME,null,values);
    }

    public Cursor getAllUsersByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(RestaurantRegistration.Res.TABLE_NAME,
                null,null,null,null,null,null);
    }

    public long deleteUserByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = RestaurantRegistration.Res._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(RestaurantRegistration.Res.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateUserByMethod(String _id, String image, String title, String address, String phone) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RestaurantRegistration.Res.KEY_IMAGE, image);
        values.put(RestaurantRegistration.Res.KEY_TITLE, title);
        values.put(RestaurantRegistration.Res.KEY_ADDRESS, address);
        values.put(RestaurantRegistration.Res.KEY_PHONE, phone);

        String whereClause = RestaurantRegistration.Res._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(RestaurantRegistration.Res.TABLE_NAME, values, whereClause, whereArgs);
    }
}
