package io.github.otakuchiyan.dnsman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;
import android.util.Log;


public class NetworkCheckReceiver extends BroadcastReceiver {
    final String CONNECTIVITY_CHANGE_ACTION = new String("android.net.conn.CONNECTIVITY_CHANGE");

    @Override
    public void onReceive(Context context, Intent intent) {
        if(TextUtils.equals(new String(intent.getAction()), CONNECTIVITY_CHANGE_ACTION)) {
            //FIXME: Use notification instead toast
            /*NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.abc_spinner_textfield_background_material)
                    .setContentTitle(R.string.app_name)
                    .setContentText(R.string.mobilenet_changed);
            */
            Log.d("DNS", "onReceive");
            Toast.makeText(context, R.string.app_name + R.string.mobilenet_changed, Toast.LENGTH_SHORT);
        }
    }
}
