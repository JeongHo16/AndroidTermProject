package com.hansung.teamproject.homework1;


import android.provider.BaseColumns;

public final class MenuRegistration { //9주차 실습과제의 참고코드
    public static final String DB_NAME="menu.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String IMAGE_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MenuRegistration() {}

    /* Inner class that defines the table contents */
    public static class Menu implements BaseColumns {
        public static final String TABLE_NAME="Menu";
        public static final String KEY_RESTITLE = "Restitle";
        public static final String KEY_IMAGE = "Image";
        public static final String KEY_TITLE = "Title";
        public static final String KEY_PRICE = "Price";
        public static final String KEY_DESCRIPTION = "Description";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_RESTITLE + TEXT_TYPE + COMMA_SEP +
                KEY_IMAGE + IMAGE_TYPE + COMMA_SEP +
                KEY_TITLE + TEXT_TYPE + COMMA_SEP +
                KEY_PRICE + TEXT_TYPE + COMMA_SEP +
                KEY_DESCRIPTION + TEXT_TYPE +  " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
