package com.example.navigatio_learn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(Tour_id INTEGER primary key AUTOINCREMENT, tour_name TEXT, destination TEXT, datetour TEXT, risk TEXT, description TEXT)");
        DB.execSQL("CREATE TABLE Expense (" +
                "expense_id              INTEGER PRIMARY KEY AUTOINCREMENT," +
                "expense_name        TEXT," +
                "expense_time          TEXT," +
                "expense_price         TEXT," +
                "expense_note            TEXT," +
                "expense_type            TEXT," +
                "expense_trip_id          INTEGER REFERENCES Userdetails(Tour_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists Userdetails");
        DB.execSQL("drop table if exists Expense");

    }

    public Boolean InsertTripData(String nametour, String destination, String datetour, String risk, String description) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //listFragment traverse through 0 to 4.

        contentValues.put("tour_name", nametour); // 0
        contentValues.put("destination", destination); // 1
        contentValues.put("datetour", datetour); // 2
        contentValues.put("risk", risk); // 3
        contentValues.put("description", description); // 4

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Boolean UpdateTripData(String Tour_id, String nametour, String destination, String datetour, String risk, String description) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tour_id", Tour_id);
        contentValues.put("tour_name", nametour);
        contentValues.put("datetour", datetour);
        contentValues.put("risk", risk);
        contentValues.put("description", description);
        contentValues.put("destination", destination);

        Cursor cursor = DB.rawQuery("Select * from Userdetails where Tour_id = ?", new String[]{Tour_id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "Tour_id=?", new String[]{Tour_id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }


    public Boolean DeleteData(String Tour_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where Tour_id = ?", new String[]{Tour_id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "Tour_id=?", new String[]{Tour_id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }


    public Cursor DisplayData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

    public Boolean InsertExpenseData(String expense_name, String expense_note, String expense_price, String expense_type, String expense_time, String expense_trip_id) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //listFragment traverse through 0 to 4.// 0
        contentValues.put("expense_name", expense_name); // 1
        contentValues.put("expense_note", expense_note); // 2
        contentValues.put("expense_type", expense_type); // 3
        contentValues.put("expense_time", expense_time);
        contentValues.put("expense_price", expense_price);
        contentValues.put("expense_trip_id", expense_trip_id);
        // 4


        long result = DB.insert("Expense", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Cursor DisplayExpense(int id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String selection = "expense_trip_id" + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = DB.query("Expense", null, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public ArrayList<String> get_trip_nameData() {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<String> _name = new ArrayList<String>();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        while (cursor.moveToNext()) {
            _name.add(cursor.getString(cursor.getColumnIndexOrThrow("tour_name")));

        }

        return _name;

    }

    public ArrayList<String> get_trip_destination() {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<String> destination = new ArrayList<String>();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        while (cursor.moveToNext()) {
            destination.add(cursor.getString(cursor.getColumnIndexOrThrow("destination")));


        }

        return destination;

    }
    public ArrayList<String> get_trip_Date() {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<String> date = new ArrayList<String>();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        while (cursor.moveToNext()) {
            date.add(cursor.getString(cursor.getColumnIndexOrThrow("datetour")));


        }

        return date;

    }

    public ArrayList<String> get_trip_Risk() {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<String> risk = new ArrayList<String>();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        while (cursor.moveToNext()) {
            risk.add(cursor.getString(cursor.getColumnIndexOrThrow("risk")));


        }

        return risk;

    }

    public ArrayList<String> get_trip_Validation() {
        SQLiteDatabase DB = this.getReadableDatabase();

        ArrayList<String> validate = new ArrayList<String>();


        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        while (cursor.moveToNext()) {
            validate.add(cursor.getString(cursor.getColumnIndexOrThrow("description")));


        }

        return validate;

    }

    //public Boolean InsertExpenseData(   String expense_name,  String expense_note, String expense_price, String expense_type, String expense_time, String expense_trip_id){

    // SQLiteDatabase DB = this.getWritableDatabase();
    //ContentValues contentValues = new ContentValues();
    //listFragment traverse through 0 to 4.// 0
    //contentValues.put("expense_name", expense_name); // 1
    //contentValues.put("expense_note", expense_note); // 2
    //contentValues.put("expense_type", expense_type); // 3
    //contentValues.put("expense_time",  expense_time);
    //contentValues.put("expense_price", expense_price);// 4
    //contentValues.put("expense_trip_id", expense_trip_id);

    // long result=DB.insert("Expense", null, contentValues);
    //if (result==-1) {
    //     return false;
    // }else{
    //    return true;
    // }

    //}


}

