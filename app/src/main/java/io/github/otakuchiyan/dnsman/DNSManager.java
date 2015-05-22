package io.github.otakuchiyan.dnsman;

import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Created by otakuchiyan on 2015/5/1.
 */
public class DNSManager {
    String cmd_dns1 = new String("setprop net.dns1 ");
    String cmd_dns2 = new String("setprop net.dns2 ");


    public int setDNSViaSetprop(String DNS1, String DNS2, boolean use_su) {
        Process p = null;
        DataOutputStream dos = null;
        try{
            if(use_su == true){
                p = Runtime.getRuntime().exec("su");
            }else{
                p = Runtime.getRuntime().exec("su");
            }

            dos = new DataOutputStream(p.getOutputStream());
            dos.writeBytes(cmd_dns1 + DNS1 + "\n");
            dos.flush();
            dos.writeBytes(cmd_dns2 + DNS2 + "\n");
            dos.flush();
            if(use_su == true){
                dos.writeBytes("exit\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (dos != null){
                try {
                    dos.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d("DNSManager", "Set DNS.");
        return 0;
    }
}
