package com.example.xiaole.studentloanhelper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


public class CalculatePayment extends ActionBarActivity {
    Double paymentPerMonth;
    int year=0;
    int startMonth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_payment);
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getApplicationContext(), ListApplication.class);
                startActivity(myIntent);
            }
        });
        int totalLoan = 0;
        int totalGrant = 0;

        DatabaseHandler db = new DatabaseHandler(this);
        List<LoanApplication> data = db.Get_Loans();
        String lastDate = data.get(0)._time_period;

        for (LoanApplication item: data){
            totalLoan = totalLoan + item._loan;
            totalGrant = totalGrant + item._grant;
        }
        int totalPayment = totalLoan - totalGrant;
        TextView lastText = (TextView)findViewById(R.id.lastTime);
        TextView loanText = (TextView)findViewById(R.id.totalLoan);
        TextView grantText = (TextView)findViewById(R.id.totalGrant);
        TextView paymentText = (TextView)findViewById(R.id.totalPayment);
        TextView payment = (TextView)findViewById(R.id.payment);
        TextView dateText = (TextView)findViewById(R.id.startDay);
        loanText.setText(Integer.toString(totalLoan));
        grantText.setText(Integer.toString(totalGrant));
        lastText.setText(lastDate);
        paymentText.setText(Integer.toString(totalPayment));
        String[] parts = lastDate.split(",");
        String [] yearParts = parts[0].split("-");
        String period = parts[1];
        //dateText.setText(Integer.toString(year));
        String startDate;
        int finishMonth=0;
        if (period.equals(" Winter") || period.equals(" Fall and Winter"))
            finishMonth = 5;
        else if(period.equals(" Summer"))
            finishMonth = 9;
        else if(period.equals(" Fall")) {
            finishMonth = 1;
        }
        year = Integer.parseInt(yearParts[1]);
        if ((finishMonth + 6) > 12){
            year++;
            startMonth = finishMonth + 6 - 12;
        }
        else
            startMonth = finishMonth + 6;
        startDate = year + "-" + startMonth + "-1";
        dateText.setText(startDate);
        paymentPerMonth = totalPayment * (1+0.035) / (5*12);
        payment.setText(Double.toString(paymentPerMonth));

        findViewById(R.id.alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        //alarmStart();
    }

    public void start(){

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            //cal.clear();

            //cal.set(year,startMonth,1,0,0,0);
            cal.add(Calendar.SECOND, 30);

             AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("payment", Double.toString(paymentPerMonth));
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            cal.add(Calendar.MONTH, 1);
            alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
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
