package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Contact extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        String contact1= "If you have questions about the Canada Education Savings Grant, the Canada Learning Bond or Registered Education Savings Plans, you can contact the Canada Education Savings Program by:\n" +
                "\n" +
                "Telephone\n" +
                "    1 800 O-CANADA (1-800-622-6232)\n" +
                "TTY\n" +
                "    1 800-926-9105\n" +
                "Mailing address\n" +
                "    The Canada Education Savings Program\n" +
                "    Employment and Social Development Canada\n" +
                "    140 Promenade du Portage, Phase IV, Mailstop: Bag 4\n" +
                "    Gatineau, Quebec K1A 0J9\n" +
                "    Please remember to include your return address if you would like us to reply by mail. ";
        TextView text1 = (TextView)findViewById(R.id.contact1);
        text1.setText(contact1);

        String contact2 = "For inquiries relating to student loans, you must contact the National Student Loans Service Centre (NSLSC). Here is how you can get in touch with them:\n" +
                "\n" +
                "Phone\n" +
                "    Within North America: 1-888-815-4514\n" +
                "    Outside of North America: Dial the international access code, followed by 800 2 225-2501 (toll free)" +
                "    Pre-Consolidation Contact Number: 1-866-303-3503" +
                "Fax\n" +
                "    Within North America: 1-888-815-4657\n" +
                "    Outside of North America: Dial the international access code followed by 1-905-306-2414." +
                "Mailing address\n" +
                "    National Student Loans Service Centre\n" +
                "    P.O. Box 4030\n" +
                "    Mississauga, Ontario L5A 4M4";
        TextView text2 = (TextView)findViewById(R.id.contact2);
        text2.setText(contact2);
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
