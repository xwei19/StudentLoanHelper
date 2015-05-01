package com.example.xiaole.studentloanhelper;

/**
 * Created by xiaole on 4/30/2015.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String payment =intent.getStringExtra("payment");
        // For our recurring task, we'll just display a message
        Toast.makeText(context, "You have a loan payment " + payment, Toast.LENGTH_SHORT).show();
    }
}
