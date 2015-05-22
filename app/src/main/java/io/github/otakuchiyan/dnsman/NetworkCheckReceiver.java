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

    private void setDNS(Context context, int mode) {
        DNSManager dns = new DNSManager();

        SharedPreferences sp = context.getSharedPreferences("dnsconf", Context.MODE_PRIVATE);
        String dns1 = null;
        String dns2 = null;
        Boolean use_su = sp.getBoolean("use_su", false);
        //Mobile network
        if(mode == 0){
            dns1 = sp.getString("mDNS1", null);
            dns2 = sp.getString("mDNS2", null);
        }else{
            dns1 = sp.getString("wDNS1", null);
            dns2 = sp.getString("wDNS2", null);
        }

        Log.d("NCR", "DNS1 " + dns1);
        Log.d("NCR", "DNS2 " + dns2);

        if (dns.setDNSViaSetprop(dns1, dns2, use_su) == 1) {
                Toast.makeText(context, R.string.set_failed, Toast.LENGTH_LONG).show();
        } else {
                Toast.makeText(context, R.string.set_succeed, Toast.LENGTH_LONG).show();
            }
    }

    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connmgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connmgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
            Log.d("NCR", "onReceive TYPE_MOBILE");
            setDNS(context, 0);
        } else if (connmgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            Log.d("NCR", "onReceive TYPE_WIFI");
            setDNS(context, 1);
        }
    }
}

