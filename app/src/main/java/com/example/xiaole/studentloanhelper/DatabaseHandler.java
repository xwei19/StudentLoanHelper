package com.example.xiaole.studentloanhelper;

/**
 * Created by xiaole on 4/21/2015.
 */
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "loansManager.db";

    // Contacts table name
    private static final String TABLE_LOANS = "loans";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TIME_P = "time_period";
    private static final String KEY_SCHOOL_NAME = "school_name";
    private static final String KEY_PROGRAM = "program";
    private static final String KEY_LOAN = "loan";
    private static final String KEY_GRANT = "grant";
    private final ArrayList<LoanApplication> loan_list = new ArrayList<LoanApplication>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOAN_TABLE = "CREATE TABLE " + TABLE_LOANS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME_P + " TEXT,"
                + KEY_SCHOOL_NAME + " TEXT," + KEY_PROGRAM + " TEXT,"
                + KEY_LOAN + " INTEGER," + KEY_GRANT + " INTEGER" + ")";
        db.execSQL(CREATE_LOAN_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new loan
    public void Add_Loan(LoanApplication loan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TIME_P, loan.getTimePeriod());
        values.put(KEY_SCHOOL_NAME, loan.getSchoolName());
        values.put(KEY_PROGRAM, loan.getProgram());
        values.put(KEY_LOAN, loan.getLoan());
        values.put(KEY_GRANT, loan.getGrant());
        // Inserting Row
        db.insert(TABLE_LOANS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single loan
    LoanApplication Get_Loan(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOANS, new String[] { KEY_ID,
                        KEY_TIME_P, KEY_SCHOOL_NAME, KEY_PROGRAM, KEY_LOAN, KEY_GRANT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        LoanApplication loan = new LoanApplication(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
        // return loan
        cursor.close();
        db.close();

        return loan;
    }

    // Getting All Contacts
    public ArrayList<LoanApplication> Get_Loans() {
        try {
            loan_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_LOANS + " ORDER BY " + KEY_TIME_P + " DESC";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    LoanApplication loan = new LoanApplication();
                    loan.setID(Integer.parseInt(cursor.getString(0)));
                    loan.setTimePeriod(cursor.getString(1));
                    loan.setSchoolName(cursor.getString(2));
                    loan.setProgram(cursor.getString(3));
                    loan.setLoan(Integer.parseInt(cursor.getString(4)));
                    loan.setGrant(Integer.parseInt(cursor.getString(5)));
                    // Adding loan to list
                    loan_list.add(loan);
                } while (cursor.moveToNext());
            }

            // return loan list
            cursor.close();
            db.close();
            return loan_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_loan", "" + e);
        }

        return loan_list;
    }

    // Updating single loan
    public int Update_Loan(LoanApplication loan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME_P, loan.getTimePeriod());
        values.put(KEY_SCHOOL_NAME, loan.getSchoolName());
        values.put(KEY_PROGRAM, loan.getProgram());
        values.put(KEY_LOAN, loan.getLoan());
        values.put(KEY_GRANT, loan.getGrant());

        // updating row
        return db.update(TABLE_LOANS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(loan.getID()) });
    }

    // Deleting single loan
    public void Delete_Loan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOANS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    // Getting loans Count
    public int Get_Total_Loans() {
        String countQuery = "SELECT  * FROM " + TABLE_LOANS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
