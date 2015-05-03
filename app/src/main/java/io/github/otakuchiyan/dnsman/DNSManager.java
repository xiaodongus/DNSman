package io.github.otakuchiyan.dnsman;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.content.Context;
import java.io.IOException;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import java.util.UnknownFormatConversionException;
import java.net.UnknownHostException;


/**
 * Created by nanafa on 2015/5/1.
 */
public class DNSManager {
    /*
    public int setWiFiDNS(Context context, String DNS1, String DNS2) throws NoSuchFieldException, IllegalAccessException, UnknownHostException {
        WifiConfiguration wc = null;
        WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        List<WifiConfiguration> configuredNetworks = wm.getConfiguredNetworks();
        for(WifiConfiguration w : configuredNetworks){
            if(w.networkId == wi.getNetworkId()){
                wc = w;
                break;
            }
        }

        Object lpf = wc.;
        if(lpf == null){
            return 1;
        }
        Object lpdf = wc.getDeclaredField("mDnses").get(wc);
        ArrayList<InetAddress> DNSes = (ArrayList<InetAddress>)lpdf;
        DNSes.clear();
        DNSes.add(InetAddress.getByName(DNS1));
        DNSes.add(InetAddress.getByName(DNS2));
        return 0;
    }
    */

    public int setMobileNetDNS(String DNS1, String DNS2){

        if(DNS1 != null && DNS2 != null) {
            Log.d("DNSManager", DNS1);
            Log.d("DNSManager", DNS2);

            DataOutputStream dos = null;

            try {
                Process proc = Runtime.getRuntime().exec("su");

                dos = new DataOutputStream(proc.getOutputStream());
                dos.writeBytes("setprop net.dns1 " + DNS1 + "\n");
                dos.flush();
                dos.writeBytes("setprop net.dns2 " + DNS2 + "\n");
                dos.flush();
                dos.writeBytes("exit\n");
                proc.waitFor();
            } catch (Exception err) {
                err.printStackTrace();
            } finally {
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }
            return 0;
        } else {
            return 1;
        }
    }
}
