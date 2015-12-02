package com.example.benjamin.alert;

import android.widget.Toast;

import com.bluecreation.melodysmart.MelodySmartDevice;
import com.bluecreation.melodysmart.MelodySmartListener;

/**
 * Created by andrew on 10/28/15.
 */
public class SmartListener implements MelodySmartListener
{

    private MelodySmartDevice device;
    private MainActivity activity;

    public SmartListener(MelodySmartDevice device, MainActivity activity)
    {
        this.device = device;
        this.activity = activity;
    }
    @Override
    public void onDeviceConnected()
    {
        activity.setConnected(true);
    }

    @Override
    public void onDeviceDisconnected()
    {
        activity.setConnected(false);
    }

    @Override
    public void onOtauAvailable() {}
}
