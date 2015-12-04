package com.example.benjamin.alert;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Class that intercepts text messages that begin with [WAR-ALERT]
 * Created by Benjamin on 10/27/2015.
 */
public class interceptSMS extends BroadcastReceiver{

    private final String TAG = this.getClass().getSimpleName();
    final SmsManager sms = SmsManager.getDefault();

    private SharedPreferences preferences;

    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Context mContext;
    private Intent mIntent;

    // Legacy Code, can probably get rid of this soon
//    public void onReceive(Context context, Intent intent) {
//        mContext = context;
//        mIntent = intent;
//
//        String action = intent.getAction();
//
//        if(action.equals(ACTION_SMS_RECEIVED)){
//
//            String address, str = "";
//            int contactId = -1;
//
//            SmsMessage[] msgs = getMessagesFromIntent(mIntent);
//            if (msgs != null) {
//                for (int i = 0; i < msgs.length; i++) {
//                    address = msgs[i].getOriginatingAddress();
//                    //contactId = ContactsUtils.getContactId(mContext, address, "address");
//                    str += msgs[i].getMessageBody().toString();
//                    str += "\n";
//                }
//            }
//
//            Log.d("tag", "rec");
////            if(contactId != -1){
////                showNotification(contactId, str);
////            }
//
//            // ---send a broadcast intent to update the SMS received in the
//            // activity---
//            Intent broadcastIntent = new Intent();
//            broadcastIntent.setAction("SMS_RECEIVED_ACTION");
//            broadcastIntent.putExtra("sms", str);
//            context.sendBroadcast(broadcastIntent);
//        }
//
//    }
//
//    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
//        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
//        byte[][] pduObjs = new byte[messages.length][];
//
//        for (int i = 0; i < messages.length; i++) {
//            pduObjs[i] = (byte[]) messages[i];
//        }
//        byte[][] pdus = new byte[pduObjs.length][];
//        int pduCount = pdus.length;
//        SmsMessage[] msgs = new SmsMessage[pduCount];
//        for (int i = 0; i < pduCount; i++) {
//            pdus[i] = pduObjs[i];
//            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
//        }
//        return msgs;
//    }
//
//    /**
//     * The notification is the icon and associated expanded entry in the status
//     * bar.
//     */
//    protected void showNotification(int contactId, String message) {
//        //Display notification...
//    }

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // TODO Auto-generated method stub
//        Log.d("interCept", "in");
//        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
//            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
//            SmsMessage[] msgs = null;
//            String msg_from;
//            if (bundle != null){
//                //---retrieve the SMS message received---
//                try{
//                    Object[] pdus = (Object[]) bundle.get("pdus");
//                    msgs = new SmsMessage[pdus.length];
//                    for(int i=0; i<msgs.length; i++){
//                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
//                        msg_from = msgs[i].getOriginatingAddress();
//                        String msgBody = msgs[i].getMessageBody();
//                        Log.d("tag", msgBody);
//                    }
//                }catch(Exception e){
////                            Log.d("Exception caught",e.getMessage());
//                }
//            }
//        }
//    }
//

    /**
     * Intercept and process incoming text messages. Currently only displying incoming texts
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        String strMsg = "";

        boolean alertMsg = false;

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage smsMsg = SmsMessage.createFromPdu((byte[])pdusObj[i]);

                    String strMsgBody = smsMsg.getMessageBody().toString();
                    String strSrc = smsMsg.getOriginatingAddress();

                    strMsg += strMsgBody + ":" + strSrc;
                }
                Log.d("tag", strMsg);
                if (strMsg.startsWith("[War-ALERT]"))
                {
                    Log.d("bool", "true");
                }
                Toast.makeText(context,strMsg, Toast.LENGTH_SHORT).show();


            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}
