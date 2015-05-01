package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Loan_weblist extends ActionBarActivity {
    private String[] webList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_weblist);
        ListView ls = (ListView)findViewById(R.id.webList);
        webList = getResources().getStringArray(R.array.websiteList);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, webList);
        ls.setAdapter(adapter);
        // ListView Item Click Listener
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String urlLink = view.getContext().getResources().getStringArray(R.array.url)[position];
                //opens the url in one of the Android Browsers.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlLink));
                view.getContext().startActivity(intent);
            }
        });

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
