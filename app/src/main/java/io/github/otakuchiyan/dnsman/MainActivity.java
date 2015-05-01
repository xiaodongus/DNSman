package io.github.otakuchiyan.dnsman;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;

import io.github.otakuchiyan.dnsman.DNSManager;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setButtonOnClick(View view){
        EditText mobileNetDNS1 = (EditText)findViewById(R.id.mobileDNS1);
        EditText mobileNetDNS2 = (EditText)findViewById(R.id.mobileDNS2);
        final String mobileDNS1addr = new String(mobileNetDNS1.getText().toString());
        final String mobileDNS2addr = new String(mobileNetDNS2.getText().toString());

        Log.d("DNS1", mobileDNS1addr);
        Log.d("DNS2", mobileDNS2addr);

        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mDNS1", mobileDNS1addr);
        editor.putString("mDNS2", mobileDNS2addr);

        DNSManager dns = new DNSManager();
        dns.setMobileNetDNS(mobileDNS1addr, mobileDNS2addr);
        Toast.makeText(this, R.string.dnssetted, Toast.LENGTH_SHORT).show();
    }
}
