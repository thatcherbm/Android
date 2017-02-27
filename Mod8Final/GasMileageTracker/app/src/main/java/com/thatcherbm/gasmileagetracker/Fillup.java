package com.thatcherbm.gasmileagetracker;

/**
 * Created by bthatcher on 1/5/2017.
 * Attributes: id, name, odometer
 * Constructors: empty, two parameter
 * Getters and Setters: all
 */

public class Fillup {

    // Labels table name
    public static final String TABLE = "Fillups";

    // Labels Table Columns Names
    public static final String KEY_ID = "id";
    public static final String KEY_VEHICLE_NAME = "vehicleName";
    public static final String KEY_DATE = "date";
    public static final String KEY_MILES = "miles";
    public static final String KEY_GALLONS = "gallons";
    public static final String KEY_COST = "cost";
    public static final String KEY_MILEAGE = "mileage";

    // Properties
    public int id;
    public String vehicle_name;
    public String date;
    public double miles;
    public double gallons;
    public double cost;
    public double mileage;

}
