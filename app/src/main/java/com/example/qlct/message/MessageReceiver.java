package com.example.qlct.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.qlct.model.Mess;

public class MessageReceiver extends BroadcastReceiver {

    public static String LIST_BANK = "Techcombank, BaoVietBank, MB Bank, BIDV, Maritime Bank, VP Bank, Vietinbank, Agribank";

    private int type;

    private static MessageListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            //bank = sender: smsMessage.getDisplayOriginatingAddress()
            //body: smsMessage.getMessageBody()

//            String bank = getBank(smsMessage.getDisplayOriginatingAddress());
//            String bank = smsMessage.getDisplayOriginatingAddress();
//            String amount = getAmount(smsMessage.getMessageBody());
//            Mess mess = null;
//            if(bank != null && amount != null){
//                mess = new Mess(bank, type, amount);
//            }
//
//            if(mess != null){
//                mListener.messageReceived(mess);
//            }
            mListener.messageReceived(smsMessage.getDisplayOriginatingAddress(), smsMessage.getMessageBody());
        }
    }

//    private String getBank(String sender){
//        if(LIST_BANK.contains(sender)){
//            return sender;
//        }
//        return null;
//    }
//
//    private String getAmount(String bodyMess){
//        String a = bodyMess.split("VND")[0];
//        String amount = "";
//        if(a != null){
//            if(a.contains("+")){
//                type = 1;
//                amount = a.split("\\+")[1];
//            } else if(a.contains("-")){
//                type = 2;
//                amount = a.split("-")[1];
//            }
//            return amount;
//        }
//        return null;
//    }

    public static void bindListener(MessageListener listener){
        mListener = listener;
    }
}
