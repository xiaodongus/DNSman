package io.github.otakuchiyan.dnsman;

import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Created by otakuchiyan on 2015/5/1.
 */
public class DNSManager {
    String net_dns_prop = "net.dns";


    public int setDNSViaSetprop(String[] dnss, boolean use_su) {
        Process p = null;
        DataOutputStream dos = null;
        String cmd = null;
        try{
            if(use_su == true){
                p = Runtime.getRuntime().exec("su");
            }else{
                p = Runtime.getRuntime().exec("su");
            }

            dos = new DataOutputStream(p.getOutputStream());
            for(int i = 1; i != dnss.length; i++) {
                if(dnss[i].isEmpty()){
                    continue;
                }
                cmd = "setprop " + net_dns_prop + Integer.toString(i) + " " + dnss[i] + "\n";
                Log.d("DNSManager", "CMD = " + cmd);
                dos.writeBytes(cmd);
                dos.flush();
            }
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
