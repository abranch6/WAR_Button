package com.example.benjamin.alert;

import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Locale;

/**
 * Main activity of app, HELP/I AM SAFE Screen
 */
public class MainActivity extends AppCompatActivity {

    protected ToggleButton safeHelpButton;
    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        safeHelpButton = (ToggleButton) findViewById(R.id.toggleButton);
        Toolbar tlBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(tlBar);

        // Calls logic on press of HELP/I AM SAFE button
        safeHelpButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // logic when Help is needed
                    sendHelpSMSMessage();

                } else {
                    // logic when button is SAFE
                    sendSafeSMSMessage();
                }
            }
        });
    }

    /**
     * Sends help message on button press. Contacts are hard coded for now. "[War-ALERT]" is currently
     * text message tags. Sends two text message. The first includes the translated address the second
     * includes the physical long/lat.
     */
    protected void sendHelpSMSMessage() {

        // Location
        GPS gps = new GPS(this);
        double lat = gps.getLatitude();
        double longg = gps.getLongitude();

        Log.i("Send SMS", "");
        //String phoneNum = phoneNumber.getText().toString();
        String[] phoneNum = {"2085300606", "4702633691"};
        String address = getAddress(lat, longg);
        //String msg = txtMessage.getText().toString();
        String locMsg = "[War-ALERT]: Location is: " + address
                + System.getProperty("line.separator")
                + " Latitude is: " + lat
                + System.getProperty("line.separator")
                + " Longitude is: " + longg ;
        String gMap = "http://maps.google.com?q=" + lat + "," + longg;
        StringBuffer smsLoc = new StringBuffer();

        smsLoc.append(Uri.parse(locMsg));

        try {
            SmsManager smsMgm = SmsManager.getDefault();
            for (int i = 0; i < phoneNum.length; i++)
            {
                smsMgm.sendTextMessage(phoneNum[i], null, smsLoc.toString(), null, null);
                smsMgm.sendTextMessage(phoneNum[i] , null, gMap, null, null);
            }

            Toast.makeText(getApplicationContext(), "Help SMS Sent.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS Failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
    }

    /**
     * Send a safe message to contacts
     */
    protected void sendSafeSMSMessage() {

        //String phoneNum = phoneNumber.getText().toString();
        String[] phoneNum = {"2085300606", "4702633691"};
        String locMsg = "[War-ALERT]: User is Safe";

        try {
            SmsManager smsMgm = SmsManager.getDefault();
            for (int i = 0; i < phoneNum.length; i++)
            {
                smsMgm.sendTextMessage(phoneNum[i], null, locMsg, null, null);
            }

            Toast.makeText(getApplicationContext(), "Safe SMS Sent.", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS Failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
    }

    /**
     * Function to translate coordinates to an address.
     * @param lat
     * @param longg
     * @return
     */
    private String getAddress(double lat, double longg) {
        String address = "";

        try {
            Geocoder geoLoc = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geoLoc.getFromLocation(lat, longg, 1);
            for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
            {
                address += addresses.get(0).getAddressLine(i) + ",";
            }
            address = address.substring(0, address.length() - 1);
            Log.d("add", address);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Contact:
                // select the contact icon
                Log.d("contact", "contact selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
