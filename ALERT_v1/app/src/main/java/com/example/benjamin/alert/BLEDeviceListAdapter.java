package com.example.benjamin.alert;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrew on 10/28/15.
 */

// Adapter for holding devices found through scanning.
public class BLEDeviceListAdapter extends BaseAdapter
{
    private final ArrayList<BluetoothDevice> mLeDevices;
    private final LayoutInflater mInflater;

    static class ViewHolder
    {
        TextView deviceName;
        TextView deviceAddress;
    }

    public BLEDeviceListAdapter(LayoutInflater layoutInflater)
    {
        super();
        mLeDevices = new ArrayList<>();
        mInflater = layoutInflater;
    }

    public void addDevice(BluetoothDevice device)
    {
        if (!mLeDevices.contains(device))
        {
            mLeDevices.add(device);
            notifyDataSetChanged();
        }
    }

    public void clear()
    {
        mLeDevices.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mLeDevices.size();
    }

    @Override
    public Object getItem(int i)
    {
        return mLeDevices.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    /**
     * Creates and initalizes the view for a entry in the list
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        // General ListView optimization code.
        if (view == null)
        {
            view = mInflater.inflate(R.layout.listitem_device, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        BluetoothDevice device = mLeDevices.get(i);
        final String deviceName = device.getName();

        if (deviceName != null && deviceName.length() > 0)
        {
            viewHolder.deviceName.setText(deviceName);
        }
        else
        {
            viewHolder.deviceName.setText("Unknown device");
        }
        viewHolder.deviceAddress.setText(device.getAddress());

        return view;
    }
}
