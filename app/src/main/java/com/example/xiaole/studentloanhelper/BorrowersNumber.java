package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;


public class BorrowersNumber extends ActionBarActivity {
    private String[] texts;
    private AutoCompleteTextView searchText;
    ListView list;
    private ArrayList<String> year = new ArrayList<String>();
    private ArrayList<String> Canada = new ArrayList<String>();
    private ArrayList<String> Newfoundland = new ArrayList<String>();
    private ArrayList<String> Edward = new ArrayList<String>();
    private ArrayList<String> Nova = new ArrayList<String>();
    private ArrayList<String> Brunswick = new ArrayList<String>();
    private ArrayList<String> Ontario = new ArrayList<String>();
    private ArrayList<String> Manitoba = new ArrayList<String>();
    private ArrayList<String> Saskatchewan = new ArrayList<String>();
    private ArrayList<String> Alberta = new ArrayList<String>();
    private ArrayList<String> British = new ArrayList<String>();
    private ArrayList<String> Yukon = new ArrayList<String>();
    private ArrayList<String> Northwest = new ArrayList<String>();
    private ArrayList<String> listText = new ArrayList<String>();
    private ArrayList<String> infoText = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowers);
        String title = "This list contains data for the period of 1964-65 to 2012-13 academic years on the number of full-time Canada Student Loan borrowers by jurisdiction. (academic year: August 1 to July 31). ";
        TextView titleText = (TextView) findViewById(R.id.titleText);
        titleText.setText(title);

        list = (ListView) findViewById(R.id.nationalSurvey);
        readFromXML();
        for (int i=(year.size()-1);i>=0;i--){
                listText.add(year.get(i) + ", Canada: " + Canada.get(i));
                String info = "Loan Year: " + year.get(i)
                    + "\n" + "Canda: " + Canada.get(i)
                    + "\n" + "Newfoundland and Labrador: " + Newfoundland.get(i)
                    + "\n" + "Prince Edward Island: " + Edward.get(i)
                    + "\n" + "Nova Scotia: " + Nova.get(i)
                    + "\n" + "New Brunswick: " + Brunswick.get(i)
                    + "\n" + "Ontario: " + Ontario.get(i)
                    + "\n" + "Manitoba: " + Manitoba.get(i)
                    + "\n" + "Saskatchewan: " + Saskatchewan.get(i)
                    + "\n" + "Alberta: " + Alberta.get(i)
                    + "\n" + "British Columbia: " + British.get(i)
                    + "\n" + "Yukon: " + Yukon.get(i)
                    + "\n" + "Northwest Territories: " + Northwest.get(i);
                infoText.add(info);
        }
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listText);
        list.setAdapter(aa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), infoText.get(position), Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void readFromXML(){

        XmlPullParser xpp = getResources().getXml(R.xml.full_time);
        try {
            // auto-detect the encoding from the stream

            while (xpp.getEventType()!=XmlPullParser.END_DOCUMENT) {
                    if (xpp.getEventType()==XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("year")) {
                            year.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Canada")) {
                            Canada.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Newfoundland")) {
                            Newfoundland.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Edward")) {
                            Edward.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Nova")) {
                            Nova.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Brunswick")) {
                            Brunswick.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Ontario")) {
                            Ontario.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Manitoba")) {
                            Manitoba.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Saskatchewan")) {
                            Saskatchewan.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Alberta")) {
                            Alberta.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("British")) {
                            British.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Yukon")) {
                            Yukon.add(xpp.nextText());
                        }
                        else if (xpp.getName().equals("Northwest")) {
                            Northwest.add(xpp.nextText());
                        }
                    }
                    xpp.next();
                }

        } catch (Exception e) {
            Log.d("PullFeedParser", e.getMessage(), e);
            throw new RuntimeException(e);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
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
