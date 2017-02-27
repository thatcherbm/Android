package com.thatcherbm.gasmileagetracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bthatcher on 1/24/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "gasmileage.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_VEHICLE = "CREATE TABLE " + Vehicle.TABLE  + "("
                + Vehicle.KEY_NAME + " TEXT PRIMARY KEY)";

        db.execSQL(CREATE_TABLE_VEHICLE);

        String CREATE_FILLUP_TABLE = "CREATE TABLE "
                + Fillup.TABLE + "("
                + Fillup.KEY_ID + " INTEGER PRIMARY KEY, "
                + Fillup.KEY_VEHICLE_NAME + " TEXT, "
                + Fillup.KEY_DATE + " TEXT, "
                + Fillup.KEY_MILES + " DECIMAL, "
                + Fillup.KEY_GALLONS + " DECIMAL, "
                + Fillup.KEY_COST + " DECIMAL, "
                + "FOREIGN KEY(" + Fillup.KEY_VEHICLE_NAME + ") REFERENCES "
                + Fillup.TABLE + "(" + Fillup.KEY_VEHICLE_NAME + ")"
                + ")";

        db.execSQL(CREATE_FILLUP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Vehicle.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Fillup.TABLE);

        // Create tables again
        onCreate(db);

    }
}
