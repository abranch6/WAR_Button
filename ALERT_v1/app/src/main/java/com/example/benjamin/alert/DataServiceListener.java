package com.example.benjamin.alert;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.widget.Toast;

import com.bluecreation.melodysmart.DataService;
import com.bluecreation.melodysmart.MelodySmartDevice;

/**
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

    @Override
    public void onReceived(final String data)
    {
        if (data.length() >= 6 && data.substring(0, 6).compareTo("BUTTON") == 0) {
            activity.deviceButtonPressed();
            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME);
            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

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
