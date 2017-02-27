package com.thatcherbm.gasmileagetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bthatcher on 1/24/2017.
 */

public class VehicleRepo {
    private DBHelper dbHelper;

    public VehicleRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Vehicle vehicle) {
        // Open the db connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Vehicle.KEY_NAME, vehicle.name);

        //Inserting Row
        long vehicle_Id = db.insert(Vehicle.TABLE, null, values);
        db.close();
        return (int) vehicle_Id;
    }

    public void delete(Vehicle vehicle) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Vehicle.TABLE, Vehicle.KEY_NAME + "= ?", new String[] { String.valueOf(vehicle.name) });
        db.close(); // Closing database connection
    }

    public void cleanup() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Vehicle.TABLE, Vehicle.KEY_NAME + "= ?", null);
        db.close(); // Closing database connection
    }

    public void update(Vehicle oldvehicle, Vehicle newvehicle) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Vehicle.KEY_NAME, newvehicle.name);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Vehicle.TABLE, values, Vehicle.KEY_NAME + "= ?", new String[] { String.valueOf(oldvehicle.name) });
        db.close(); // Closing database connection
    }

    public ArrayList<String> getVehicleList(String loc) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT * FROM " + Vehicle.TABLE;

        // Vehicle vehicle = new Vehicle();
        ArrayList<String> vehicleList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if(!loc.equals("AddEdit")) {
            vehicleList.add("All");
        }

        if (cursor.moveToFirst()) {
            do {
                vehicleList.add(cursor.getString(cursor.getColumnIndex(Vehicle.KEY_NAME)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return vehicleList;
    }


}
