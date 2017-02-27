package com.thatcherbm.gasmileagetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bthatcher on 1/24/2017.
 */

public class FillupRepo {
    private DBHelper dbHelper;

    public FillupRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Fillup fillup) {
        // Open the db connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Fillup.KEY_VEHICLE_NAME, fillup.vehicle_name);
        values.put(Fillup.KEY_DATE, fillup.date);
        values.put(Fillup.KEY_MILES, fillup.miles);
        values.put(Fillup.KEY_GALLONS, fillup.gallons);
        values.put(Fillup.KEY_COST, fillup.cost);

        //Inserting Row
        long fillup_Id = db.insert(Fillup.TABLE, null, values);
        db.close();
        return (int) fillup_Id;
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Fillup.TABLE, Fillup.KEY_ID + "= ?", new String[] { String.valueOf(id) });
        db.close(); // Closing database connection
    }

    public void update(Fillup fillup) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Fillup.KEY_VEHICLE_NAME, fillup.vehicle_name);
        values.put(Fillup.KEY_DATE, fillup.date);
        values.put(Fillup.KEY_MILES, fillup.miles);
        values.put(Fillup.KEY_GALLONS, fillup.gallons);
        values.put(Fillup.KEY_COST, fillup.cost);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Fillup.TABLE, values, Fillup.KEY_ID + "= ?", new String[] { String.valueOf(fillup.id) });
        db.close(); // Closing database connection
    }

    public Fillup getFillupById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Fillup.KEY_ID + "," +
                Fillup.KEY_VEHICLE_NAME + "," +
                Fillup.KEY_DATE + "," +
                Fillup.KEY_MILES + "," +
                Fillup.KEY_GALLONS + "," +
                Fillup.KEY_COST +
                " FROM " + Fillup.TABLE
                + " WHERE " +
                Fillup.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Fillup fillup = new Fillup();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id) } );

        if (cursor.moveToFirst()) {
            do {
                fillup.id =cursor.getInt(cursor.getColumnIndex(Fillup.KEY_ID));
                fillup.date =cursor.getString(cursor.getColumnIndex(Fillup.KEY_DATE));
                fillup.miles =cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_MILES));
                fillup.gallons =cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_MILES));
                fillup.cost =cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_MILES));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fillup;
    }

    /**
     * Returns the full list of all fillups
     * @return
     */

    public ArrayList<HashMap<String, String>> getFillupListAll() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Fillup.KEY_ID + "," +
                Fillup.KEY_VEHICLE_NAME + "," +
                Fillup.KEY_DATE + "," +
                Fillup.KEY_MILES + "," +
                Fillup.KEY_GALLONS + "," +
                Fillup.KEY_COST +
                " FROM " + Fillup.TABLE +
                " ORDER BY " + Fillup.KEY_DATE;

        // Fillup fillup = new Fillup();
        ArrayList<HashMap<String, String>> fillupList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        DecimalFormat dist = new DecimalFormat("###.0");
        DecimalFormat vol = new DecimalFormat("###.000");
        NumberFormat cash = NumberFormat.getCurrencyInstance();
        DecimalFormat mpg = new DecimalFormat("###.0");

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> fillup = new HashMap<String, String>();
                fillup.put(Fillup.KEY_ID, cursor.getString(cursor.getColumnIndex(Fillup.KEY_ID)));
                fillup.put(Fillup.KEY_VEHICLE_NAME, cursor.getString(cursor.getColumnIndex(Fillup.KEY_VEHICLE_NAME)));
                fillup.put(Fillup.KEY_DATE, cursor.getString(cursor.getColumnIndex(Fillup.KEY_DATE)));
                double miles = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_MILES));
                fillup.put(Fillup.KEY_MILES, dist.format(miles));
                double gallons = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_GALLONS));
                fillup.put(Fillup.KEY_GALLONS, vol.format(gallons));
                double cost = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_COST));
                fillup.put(Fillup.KEY_COST, cash.format(cost));
                double mileage = miles / gallons;
                fillup.put(Fillup.KEY_MILEAGE, mpg.format(mileage));
                fillupList.add(fillup);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fillupList;

    }

    /**
     * Returns all the fillups for a given Vehicle
     * @param name
     * @return
     */

    public ArrayList<HashMap<String, String>> getFillupListVehicle(String name) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Fillup.KEY_ID + "," +
                Fillup.KEY_VEHICLE_NAME + "," +
                Fillup.KEY_DATE + "," +
                Fillup.KEY_MILES + "," +
                Fillup.KEY_GALLONS + "," +
                Fillup.KEY_COST +
                " FROM " + Fillup.TABLE +
                " WHERE " + Fillup.KEY_VEHICLE_NAME + " =?" +
                " ORDER BY " + Fillup.KEY_DATE;

        // Fillup fillup = new Fillup();
        ArrayList<HashMap<String, String>> fillupList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { name } );
        // looping through all rows and adding to list

        DecimalFormat dist = new DecimalFormat("###.0");
        DecimalFormat vol = new DecimalFormat("###.000");
        NumberFormat cash = NumberFormat.getCurrencyInstance();
        DecimalFormat mpg = new DecimalFormat("###.0");

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> fillup = new HashMap<String, String>();
                fillup.put(Fillup.KEY_ID, cursor.getString(cursor.getColumnIndex(Fillup.KEY_ID)));
                fillup.put(Fillup.KEY_VEHICLE_NAME, cursor.getString(cursor.getColumnIndex(Fillup.KEY_VEHICLE_NAME)));
                fillup.put(Fillup.KEY_DATE, cursor.getString(cursor.getColumnIndex(Fillup.KEY_DATE)));
                double miles = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_MILES));
                fillup.put(Fillup.KEY_MILES, dist.format(miles));
                double gallons = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_GALLONS));
                fillup.put(Fillup.KEY_GALLONS, vol.format(gallons));
                double cost = cursor.getDouble(cursor.getColumnIndex(Fillup.KEY_COST));
                fillup.put(Fillup.KEY_COST, cash.format(cost));
                double mileage = miles / gallons;
                fillup.put(Fillup.KEY_MILEAGE, mpg.format(mileage));
                fillupList.add(fillup);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fillupList;

    }

    /**
     * Retrieves Monthly data for the Reports screen
     * @return
     */

    public ArrayList<HashMap<String, String>> getFillupListMonthReport() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                Fillup.KEY_ID + ", " +
                Fillup.KEY_VEHICLE_NAME + ", " +
                "substr(" + Fillup.KEY_DATE + ", 1, 7) AS month, " +
                "sum(" + Fillup.KEY_MILES + ") AS totmiles, " +
                "sum(" + Fillup.KEY_GALLONS + ") AS totgallons, " +
                "sum(" + Fillup.KEY_COST + ") AS totcost" +
                " FROM " + Fillup.TABLE +
                " GROUP BY month, " + Fillup.KEY_VEHICLE_NAME;

        // Fillup fillup = new Fillup();
        ArrayList<HashMap<String, String>> fillupList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        DecimalFormat dist = new DecimalFormat("###.0");
        DecimalFormat vol = new DecimalFormat("###.000");
        NumberFormat cash = NumberFormat.getCurrencyInstance();
        DecimalFormat mpg = new DecimalFormat("###.0");

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> fillup = new HashMap<String, String>();
                fillup.put(Fillup.KEY_ID, cursor.getString(cursor.getColumnIndex(Fillup.KEY_ID)));
                fillup.put(Fillup.KEY_VEHICLE_NAME, cursor.getString(cursor.getColumnIndex(Fillup.KEY_VEHICLE_NAME)));
                fillup.put(Fillup.KEY_DATE, cursor.getString(cursor.getColumnIndex("month")));
                double miles = cursor.getDouble(cursor.getColumnIndex("totmiles"));
                fillup.put(Fillup.KEY_MILES, dist.format(miles));
                double gallons = cursor.getDouble(cursor.getColumnIndex("totgallons"));
                fillup.put(Fillup.KEY_GALLONS, vol.format(gallons));
                double cost = cursor.getDouble(cursor.getColumnIndex("totcost"));
                fillup.put(Fillup.KEY_COST, cash.format(cost));
                double mileage = miles / gallons;
                fillup.put(Fillup.KEY_MILEAGE, mpg.format(mileage));
                fillupList.add(fillup);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fillupList;

    }

    /**
     * Retrieves Yearly data for the Reports Screen
     * @return
     */

    public ArrayList<HashMap<String, String>> getFillupListYearReport() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                Fillup.KEY_ID + ", " +
                Fillup.KEY_VEHICLE_NAME + ", " +
                "substr(" + Fillup.KEY_DATE + ", 1, 4) AS year, " +
                "sum(" + Fillup.KEY_MILES + ") AS totmiles, " +
                "sum(" + Fillup.KEY_GALLONS + ") AS totgallons, " +
                "sum(" + Fillup.KEY_COST + ") AS totcost" +
                " FROM " + Fillup.TABLE +
                " GROUP BY year, " + Fillup.KEY_VEHICLE_NAME;

        // Fillup fillup = new Fillup();
        ArrayList<HashMap<String, String>> fillupList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        DecimalFormat dist = new DecimalFormat("###.0");
        DecimalFormat vol = new DecimalFormat("###.000");
        NumberFormat cash = NumberFormat.getCurrencyInstance();
        DecimalFormat mpg = new DecimalFormat("###.0");

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> fillup = new HashMap<String, String>();
                fillup.put(Fillup.KEY_ID, cursor.getString(cursor.getColumnIndex(Fillup.KEY_ID)));
                fillup.put(Fillup.KEY_VEHICLE_NAME, cursor.getString(cursor.getColumnIndex(Fillup.KEY_VEHICLE_NAME)));
                fillup.put(Fillup.KEY_DATE, cursor.getString(cursor.getColumnIndex("year")));
                double miles = cursor.getDouble(cursor.getColumnIndex("totmiles"));
                fillup.put(Fillup.KEY_MILES, dist.format(miles));
                double gallons = cursor.getDouble(cursor.getColumnIndex("totgallons"));
                fillup.put(Fillup.KEY_GALLONS, vol.format(gallons));
                double cost = cursor.getDouble(cursor.getColumnIndex("totcost"));
                fillup.put(Fillup.KEY_COST, cash.format(cost));
                double mileage = miles / gallons;
                fillup.put(Fillup.KEY_MILEAGE, mpg.format(mileage));
                fillupList.add(fillup);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fillupList;

    }
}
