package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddUpdateLoan extends ActionBarActivity {

    RadioGroup group;
    EditText timeText, grant, loan, schoolName, program;
    String getTime, periodText, timePeriod;
    DatabaseHandler handler;
    Boolean valid;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_loan);
        group=(RadioGroup)findViewById(R.id.periodText);
        periodText = "Fall";
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.summer)
                    periodText = "Summer";
                else if(checkedId == R.id.fall)
                    periodText = "Fall";
                else if(checkedId == R.id.winter)
                    periodText = "Winter";
                else
                    periodText="Fall and Winter";
            }});
        Button save = (Button)findViewById(R.id.addButton);
        handler = new DatabaseHandler(this);
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dataSave();
            }
        });
        Button back = (Button)findViewById(R.id.backButton);
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getApplicationContext(), ListApplication.class);
                startActivity(myIntent);
            }
        });

    }
    public void dataSave(){
        timeText=(EditText)findViewById(R.id.timeText);
        schoolName=(EditText)findViewById(R.id.schoolText);
        program=(EditText)findViewById(R.id.programText);
        loan=(EditText)findViewById(R.id.loanText);
        grant=(EditText)findViewById(R.id.grantText);
        //test.setText(periodText);
        getTime = timeText.getText().toString();
        String getSchool = schoolName.getText().toString();
        String getProgram = program.getText().toString();
        int getLoan = Integer.parseInt(loan.getText().toString());
        int getGrant = Integer.parseInt(grant.getText().toString());

        timePeriod = getTime + ", " + periodText;
        //test.setText(timePeriod);
        checkTimePeriod();
        if (valid) {
            LoanApplication la = new LoanApplication(timePeriod, getSchool, getProgram, getLoan, getGrant);
            handler.Add_Loan(la);
            Toast.makeText(getBaseContext(), "data saved in database, " + timePeriod, Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getBaseContext(), "data not saved in database, " + timePeriod + " already exist in database", Toast.LENGTH_LONG).show();
    }

    public void checkTimePeriod(){
        valid = true;
        DatabaseHandler db = new DatabaseHandler(this);
        List<LoanApplication> data = db.Get_Loans();

        for (LoanApplication item: data){
            if (timePeriod.equals(item._time_period)){
                valid = false;
                break;
            }
        }
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
