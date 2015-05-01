package io.github.otakuchiyan.dnsman;

import android.util.Log;
import java.io.IOException;
import java.io.DataOutputStream;

/**
 * Created by nanafa on 2015/5/1.
 */
public class DNSManager {
    public int setMobileNetDNS(String DNS1, String DNS2){

        if(DNS1 != null && DNS2 != null) {
            Log.d("DNS", DNS1);
            Log.d("DNS", DNS2);
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
