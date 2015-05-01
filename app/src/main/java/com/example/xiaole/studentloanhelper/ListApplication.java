package com.example.xiaole.studentloanhelper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ListApplication extends ActionBarActivity {
    List<LoanApplication> data;
    DatabaseHandler db;
    ArrayList<String> list;
    ArrayList<String> list_info;
    ListView lv;
    ArrayAdapter aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_application);
        Button add = (Button)findViewById(R.id.addApp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getApplicationContext(),AddUpdateLoan.class);
                startActivity(myIntent);
            }
        });

        db = new DatabaseHandler(this);
        data = db.Get_Loans();
        list = new ArrayList<String>();
        list_info = new ArrayList<String>();

        for (LoanApplication item: data){
            list.add(item._time_period);
            String info = item._time_period + "\n"
                    + item._schoolName + ", "+ item._program + "\n"
                    + "Loan: " + item._loan + ", Grant: " + item._grant;
            list_info.add(info);
        }
        lv = (ListView) findViewById(R.id.listView);
        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list_info.get(position), Toast.LENGTH_SHORT).show();
            }

        });
        registerForContextMenu(lv);
    }

    public void clickToPayment(View v){
        Intent myIntent = new Intent(this,CalculatePayment.class);
        startActivity(myIntent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Manage Loan Application");
        menu.add(0, v.getId(), 0, "Delete the Application");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = 0;
        for (LoanApplication aa : data) {
            if (aa._time_period.equals(list.get(info.position)))
                id = aa._id;
        }
        if (item.getTitle() == "Delete the Application") {
                db.Delete_Loan(id);
                finish();
                startActivity(getIntent());
            } else {
                return false;
            }
            return true;
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
