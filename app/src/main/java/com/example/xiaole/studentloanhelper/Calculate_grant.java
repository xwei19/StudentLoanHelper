package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Calculate_grant extends ActionBarActivity {
    TextView result;
    String student_status;
    String displayText;
    int totalGrants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_grant);
        RadioGroup status = (RadioGroup)findViewById(R.id.status);
        student_status = "Full-Time";
        status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.fullTime)
                    student_status = "Full-Time";
                else
                    student_status = "Part-Time";
            }});

        //calculateGrants();
        result = (TextView)findViewById(R.id.resultDisplay);
        Button cal = (Button)findViewById(R.id.calculate);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrants();
                result.setText(displayText);
            }
        });
    }

    public void calculateGrants(){
        EditText incomeText = (EditText)findViewById(R.id.income);
        EditText peopleText = (EditText)findViewById(R.id.persons);
        EditText kidsText = (EditText)findViewById(R.id.children);
        EditText semesterText = (EditText)findViewById(R.id.semester);
        int people = Integer.parseInt(peopleText.getText().toString());
        int kids = Integer.parseInt(kidsText.getText().toString());
        int semester = Integer.parseInt(semesterText.getText().toString());
        int income = Integer.parseInt(incomeText.getText().toString());

        int grantsPerYear = 0;
        if (student_status.equals("Part-Time")){
            if ((people == 1 && income < 23647) || (people == 2 && income < 29439)) {
                grantsPerYear = 1200;
                if (kids == 0)
                    displayText = "you are in low-income family";
                else if (kids > 0 && kids <= 2) {
                    grantsPerYear = 40 * 52;
                    displayText = "you are in low-income family with " + kids + " Dependants";
                }
                else if (kids >= 3) {
                    grantsPerYear = 60 * 52;
                    displayText = "you are in low-income family with " + kids + " Dependants";
                }
            }
            if (semester > 3 && grantsPerYear > 1920){
                grantsPerYear = 1920;
            }
            totalGrants = grantsPerYear/3 * semester;
            if(semester < 3 && totalGrants >1920)
                totalGrants = 1920;
        }

        if (student_status.equals("Full-Time")){
            if ((people == 1 && income < 23883) || (people == 2 && income < 29734)) {
                grantsPerYear = 250*12;
                if (kids == 0)
                    displayText = "you are in low-income family";
                else if (kids > 0) {
                    grantsPerYear = grantsPerYear + 200*kids*12;
                    displayText = "you are in low-income family with " + kids + " Dependants";
                }
            }
            else if ((people == 1 && income < 43184) || (people == 2 && income < 60458)){
                grantsPerYear = 100*12;
                displayText = "you are in middle-income family";
            }
            totalGrants = grantsPerYear/3 * semester;
        }

        if (displayText.isEmpty())
            displayText = "you are not eligibility for Canada Student Grants";
        else
            displayText = "You are studying in " + student_status
                + ", " + displayText + " and your grants expected to " + totalGrants;
    }

    public void clickToHome(View v){
        Intent myIntent = new Intent(this,MainActivity.class);
        startActivity(myIntent);
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
