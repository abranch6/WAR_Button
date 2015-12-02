package com.example.benjamin.alert;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bluecreation.melodysmart.MelodySmartDevice;

public class PairDevice extends AppCompatActivity {

    private MelodySmartDevice melodySmartDevice;

    private BLEDeviceListAdapter listAdapter;

    private boolean scanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanLeDevice(!scanning);
            }
        });

        //create preferences


        //init the list adapter for the avalible devices
        ListView deviceView = (ListView) findViewById(R.id.pair_device_list_view);
        listAdapter = new BLEDeviceListAdapter(getLayoutInflater());
        deviceView.setAdapter(listAdapter);
        deviceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                BLEDeviceListAdapter.ViewHolder viewHolder = (BLEDeviceListAdapter.ViewHolder) view.getTag();

                SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                //save the name and address for later use
                editor.putString("deviceName", viewHolder.deviceName.getText().toString());
                editor.putString("deviceAddress", viewHolder.deviceAddress.getText().toString());
                editor.commit();
            }
        });

        //init the BLE device
        melodySmartDevice = MelodySmartDevice.getInstance();
        melodySmartDevice.init(getApplicationContext());
    }

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
        invalidateOptionsMenu();
    }

    // Device scan callback.
    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback()
    {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord)
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    listAdapter.addDevice(device);
                }
            });
        }
    };

}
