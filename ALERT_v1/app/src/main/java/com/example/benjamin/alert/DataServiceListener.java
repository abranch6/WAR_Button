package com.example.benjamin.alert;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.widget.Toast;

import com.bluecreation.melodysmart.DataService;
import com.bluecreation.melodysmart.MelodySmartDevice;

/**
 * Listener for BLE device.  Called when data is recieved or when device connects
 * Created by andrew on 10/28/15.
 */
public class DataServiceListener implements DataService.Listener
{
    private MelodySmartDevice device;
    private MainActivity activity;

    public DataServiceListener(MelodySmartDevice device, MainActivity activity)
    {
        this.device = device;
        this.activity = activity;
    }

    @Override
    public void onConnected(final boolean found)
    {
        if(found)
        {
            device.getDataService().enableNotifications(true);
        }
    }

    /**
     * Called when a message is recieved from the BLE device
     * @param data Data that is received
     */
    @Override
    public void onReceived(final String data)
    {
        //parse data and act on it
        if (data.length() >= 6 && data.substring(0, 6).compareTo("BUTTON") == 0) {
            //programmatically presses the help button
            activity.deviceButtonPressed();
            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

            //returns an ACK so the device knows that the message was recieved
            device.getDataService().send("ACK");
        } else if (data.compareTo("PAIR") == 0) {
            device.getDataService().send("ACK");
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
      //          Toast.makeText(activity, data, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
