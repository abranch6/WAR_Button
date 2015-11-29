package com.example.benjamin.alert;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by Benjamin on 10/28/2015.
 */
public class ServiceComm extends Service {

    private interceptSMS mSMSreceiver;
    private IntentFilter mIntentFilter;
    

    @Override
    public void onCreate()
    {
        super.onCreate();

        //SMS event receiver
        mSMSreceiver = new interceptSMS();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        // Unregister the SMS receiver
        unregisterReceiver(mSMSreceiver);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

}
