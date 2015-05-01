package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class help extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        String help="Student Loan Helper app has three button 'Number of Full-Time Borrowers', " +
                "'List Student Loan Application' and 'Grants for student Calculator. \n\n" +
                "When click first button, you can list the number of Full-Time Borrowers by Jurisdiction " +
                "to get a number of borrowers by each province. \n\n" +
                "When click second button, it displays a list that is your student loan application. You can add and delete the applications" +
                " You also will calculate your payment each month and start date based on your recently application. \n\n" +
                "When click third button, it is a grants calculator depends on your situation";
        TextView text=(TextView)findViewById(R.id.help);
        text.setText(help);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                openHome();
                return true;
            case R.id.help:
                openHelp();
                return true;
            case R.id.action_contact:
                openContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void openHome(){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void openHelp(){
        startActivity(new Intent(this, help.class));
    }

    public void openContact(){
        startActivity(new Intent(this, Contact.class));
    }
}
