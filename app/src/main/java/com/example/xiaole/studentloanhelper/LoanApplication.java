package com.example.xiaole.studentloanhelper;

/**
 * Created by xiaole on 4/21/2015.
 */
public class LoanApplication {
    // private variables
    public int _id;
    public String _time_period;
    public String _schoolName;
    public String _program;
    public int _loan;
    public int _grant;

    public LoanApplication() {
    }

    // constructor
    public LoanApplication(int id, String timePeriod, String schoolName, String program, int loan, int grant) {
        this._id = id;
        this._time_period = timePeriod;
        this._schoolName = schoolName;
        this._program = program;
        this._loan = loan;
        this._grant = grant;
    }

    // constructor
    public LoanApplication(String timePeriod, String schoolName, String program, int loan, int grant) {
        this._time_period = timePeriod;
        this._schoolName = schoolName;
        this._program = program;
        this._loan = loan;
        this._grant = grant;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting time period
    public String getTimePeriod() {
        return this._time_period;
    }

    // setting time period
    public void setTimePeriod(String t) {
        this._time_period = t;
    }

    // getting school name
    public String getSchoolName() {
        return this._schoolName;
    }

    // setting school name
    public void setSchoolName(String s) {
        this._schoolName = s;
    }

    // getting program
    public String getProgram() {
        return this._program;
    }

    // setting program
    public void setProgram(String p) {
        this._program = p;
    }

    // getting loan
    public int getLoan() {
        return this._loan;
    }

    // setting loan
    public void setLoan(int l) {
        this._loan = l;
    }

    // getting grant
    public int getGrant() {
        return this._grant;
    }

    // setting grant
    public void setGrant(int g) {
        this._grant = g;
    }
}
