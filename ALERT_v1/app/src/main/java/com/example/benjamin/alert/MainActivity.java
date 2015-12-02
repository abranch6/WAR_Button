package com.example.benjamin.alert;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bluecreation.melodysmart.MelodySmartDevice;

import java.util.List;
import java.util.Locale;

/**
 * Main activity of app, HELP/I AM SAFE Screen
 */
public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "SwearPairPrefs";

    protected ToggleButton safeHelpButton;
    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //BLE stuff
        initBLE();

        boolean flag = true;

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Intent intlogin = new Intent(this, LoginActivity.class);
            startActivityForResult(intlogin, 1);

//            String value = extras.getString("contactBack");
//            if (value != "BACK"){
//
//            }
//            Log.d("valuePassed", value);

        }

        MySQLiteHelper db = new MySQLiteHelper(this);

        List<Contact> contacts = db.getAllContacts();

        //db.deleteAll(contacts);

//        Intent intlogin = new Intent(this, LoginActivity.class);
//        startActivityForResult(intlogin, 1);

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
                    boolean sent = sendHelpSMSMessage();
                    if (!sent) {safeHelpButton.setChecked(false);}
                    
                } else {
                    // logic when button is SAFE
                    sendSafeSMSMessage();
                }
            }
        });
    }


    // "Exit" the application
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("inResult", Integer.toString(resultCode));
        Log.d("inResultCode", Integer.toString(requestCode));
        Log.d("ActivityResultOk", Integer.toString(Activity.RESULT_OK));
        //Log.d("data", data.getStringExtra("loginResult"));
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {


//                Intent intent = new Intent(this, MainActivity.class);
//                Log.d("StartMain","");
//                startActivity(intent);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    /**
     * Sends help message on button press. Contacts are hard coded for now. "[War-ALERT]" is currently
     * text message tags. Sends two text message. The first includes the translated address the second
     * includes the physical long/lat.
     */
    protected boolean sendHelpSMSMessage() {

        // Location
        GPS gps = new GPS(this);
        double lat = gps.getLatitude();
        double longg = gps.getLongitude();

        Log.i("Send SMS", "");
        //String phoneNum = phoneNumber.getText().toString();
        //String[] phoneNum = {"2085300606", "4702633691"};
        MySQLiteHelper db = new MySQLiteHelper(this);

        List<Contact> contacts = db.getAllContacts();

        if (contacts.size() == 0){
            Toast.makeText(getApplicationContext(), "No contacts saved. Text not sent", Toast.LENGTH_LONG).show();
            return false;
        }


        String[] phoneNum = new String[contacts.size()];

        int k = 0;

        for(Contact contact: contacts)
        {
            phoneNum[k++] = contact.getPhone();
        }

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
        return true;
    }

    /**
     * Send a safe message to contacts
     */
    protected void sendSafeSMSMessage() {

        //String phoneNum = phoneNumber.getText().toString();
        MySQLiteHelper db = new MySQLiteHelper(this);

        List<Contact> contacts = db.getAllContacts();

        if (contacts.size() == 0) {return;}

        String[] phoneNum = new String[contacts.size()];

        int k = 0;

        for(Contact contact: contacts)
        {
            phoneNum[k++] = contact.getPhone();
        }

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
                // If no contacts saved got to add contacts screen
                MySQLiteHelper db = new MySQLiteHelper(this);
                List<Contact> contacts = db.getAllContacts();
                String[] contactsArray = new String[contacts.size()];
                Log.d("contact", "contact selected");
                if (contactsArray.length > 0)
                {
                    Intent intent = new Intent(this, Contacts.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(this, AddContactsLocation.class);
                    startActivity(intent);
                }

                return true;
            case R.id.Pair:
                // Go to ANDREW Pair Screen
                Intent intent = new Intent(this, PairDevice.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    //////////////////////////////BLE Stuff/////////////////////////////////////////////
    //This should all really run independent of the activity

    static boolean scanning = false;
    static boolean connected = false;

    private MelodySmartDevice melodySmartDevice;
    private SmartListener smartListener;
    private DataServiceListener dataServiceListener;

    private int mScanInterval = 5000;
    private int mScanTime = 10000;
    private Handler mScanHandler;

    private void initBLE()
    {
        melodySmartDevice = MelodySmartDevice.getInstance();
        melodySmartDevice.init(getApplicationContext());

        smartListener = new SmartListener(melodySmartDevice, this);
        dataServiceListener = new DataServiceListener(melodySmartDevice, this);

        melodySmartDevice.registerListener(smartListener);
        melodySmartDevice.getDataService().registerListener(dataServiceListener);

        mScanHandler = new Handler();
        setConnected(false);
    }
    public void setConnected(boolean c)
    {
        connected = c;
        if(!connected)
        {
            startScanTaskDelay();
        }
        else
        {
            stopScanTask();
        }
    }

    Runnable mScanTask = new Runnable() {
        @Override
        public void run() {
            if(scanning) {
                scanLeDevice(false);

                if (!connected)
                {
                    mScanHandler.postDelayed(mScanTask, mScanInterval);
                }
            }
            else
            {
                if (!connected)
                {
                    scanLeDevice(true);
                    mScanHandler.postDelayed(mScanTask, mScanTime);
                }
            }

        }
    };
    //starts the scanning task
    public void startScanTaskDelay()
    {
        mScanHandler.postDelayed(mScanTask, mScanInterval);
    }

    //stops the scannning task
    public void stopScanTask()
    {
        mScanHandler.removeCallbacks(mScanTask);
        scanLeDevice(false);
    }

    //starts and stops the bluetooth scanning
    private synchronized void scanLeDevice(final boolean enable) {
        scanning = enable;
        if (enable)
        {
            melodySmartDevice.startLeScan(mLeScanCallback);
        }
        else
        {
            melodySmartDevice.stopLeScan(mLeScanCallback);
        }
    }

    // Device scan callback.
    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback()
    {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord)
        {
            //connect to device if it has been seen already
            SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);

            String deviceName = settings.getString("deviceName", "");
            String deviceAddress = settings.getString("deviceAddress", "");

            if(device != null && !deviceAddress.equals("") && deviceAddress.compareTo(device.getAddress().toString()) == 0 &&
                    !deviceName.equals("") && deviceName.compareTo(device.getName().toString()) == 0) {
                connectToDevice(deviceAddress, deviceName);
            }
        }
    };

    public void deviceButtonPressed()
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!safeHelpButton.isChecked()) {
                    safeHelpButton.setChecked(true);
                }
            }
        });
    }

    //connects to the device
    protected void connectToDevice(String address, String name)
    {
        try
        {
            melodySmartDevice.connect(address);
        }
        catch (Exception e)
        {
            Log.d("ERROR",e.toString());
        }
    }
}
