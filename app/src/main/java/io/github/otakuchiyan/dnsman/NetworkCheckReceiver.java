package io.github.otakuchiyan.dnsman;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;
import android.util.Log;

import java.util.UnknownFormatFlagsException;

import io.github.otakuchiyan.dnsman.DNSManager;
import java.net.UnknownHostException;

public class NetworkCheckReceiver extends BroadcastReceiver {
    final String CONNECTIVITY_CHANGE_ACTION = new String("android.net.conn.CONNECTIVITY_CHANGE");

    public void onReceive(Context context, Intent intent){
        DNSManager dns = new DNSManager();
        ConnectivityManager connmgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
            //FIXME: Use notification instead toast
            /*NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.abc_spinner_textfield_background_material)
                    .setContentTitle(R.string.app_name)
                    .setContentText(R.string.mobilenet_changed);
            */

            SharedPreferences sp = context.getSharedPreferences("dnsconf", Context.MODE_PRIVATE);
            //FIXME: return DNS value use setting's value.
            String mDNS1 = sp.getString("mDNS1", "8.8.8.8");
            String mDNS2 = sp.getString("mDNS2", "8.8.4.4");

            Log.d("NCR", mDNS1);
            Log.d("NCR", mDNS2);

            if (dns.setMobileNetDNS(mDNS1, mDNS2) == 1) {
                Toast.makeText(context, R.string.mobilenet_change_failed, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, R.string.mobilenet_changed, Toast.LENGTH_LONG).show();
            }
        }/* else if (connmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            SharedPreferences sp = context.getSharedPreferences("dnsconf", Context.MODE_PRIVATE);
            String wDNS1 = sp.getString("wDNS1", "8.8.8.8");
            String wDNS2 = sp.getString("wDNS2", "8.8.4.4");
            try {
                if (dns.setWiFiDNS(context, wDNS1, wDNS2) == 1) {
                    Toast.makeText(context, R.string.wifi_change_failed, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, R.string.wifi_changed, Toast.LENGTH_LONG).show();
                }
            } catch (NoSuchFieldException err){
                err.printStackTrace();

            } catch (IllegalAccessException err){
                err.printStackTrace();
            } catch (UnknownHostException err){
                err.printStackTrace();
            }
        }
        */
        }
    }

