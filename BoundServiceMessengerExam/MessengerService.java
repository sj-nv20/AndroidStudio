package com.example.boundservicemessengerexam;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {
    static final int MSG_1 = 1;
    static final int MSG_2 = 2;

    class IncomingHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_1:
                    Toast.makeText(getApplicationContext(), "First Message!", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_2:
                    Toast.makeText(getApplicationContext(), "Second Message!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }
}
