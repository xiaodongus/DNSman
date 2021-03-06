package io.github.otakuchiyan.dnsman;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.BufferType;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;

import io.github.otakuchiyan.dnsman.DNSManager;

public class MainActivity extends ActionBarActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor sped;
    private EditText mDNS1, mDNS2, mDNS3;
    private EditText wDNS1, wDNS2, wDNS3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences("dnsconf", Context.MODE_PRIVATE);
        sped = sp.edit();

        mDNS1 = (EditText) findViewById(R.id.mDNS1);
        mDNS2 = (EditText) findViewById(R.id.mDNS2);
        mDNS3 = (EditText) findViewById(R.id.mDNS3);

        wDNS1 = (EditText) findViewById(R.id.wDNS1);
        wDNS2 = (EditText) findViewById(R.id.wDNS2);
        wDNS3 = (EditText) findViewById(R.id.wDNS3);
        CheckBox su_chkbox = (CheckBox) findViewById(R.id.su_checkbox);

        mDNS1.setText(sp.getString("mDNS1", ""));
        mDNS2.setText(sp.getString("mDNS2", ""));
        mDNS3.setText(sp.getString("mDNS3", ""));
        wDNS1.setText(sp.getString("wDNS1", ""));
        wDNS2.setText(sp.getString("wDNS2", ""));
        wDNS3.setText(sp.getString("wDNS3", ""));
        su_chkbox.setChecked(sp.getBoolean("use_su", false));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        sp  = this.getSharedPreferences("dnsconf", Context.MODE_PRIVATE);
        sped = sp.edit();

        mDNS1 = (EditText) findViewById(R.id.mDNS1);
        mDNS2 = (EditText) findViewById(R.id.mDNS2);
        mDNS3 = (EditText) findViewById(R.id.mDNS3);
        wDNS1 = (EditText) findViewById(R.id.wDNS1);
        wDNS2 = (EditText) findViewById(R.id.wDNS2);
        wDNS3 = (EditText) findViewById(R.id.wDNS3);
        CheckBox su_chkbox = (CheckBox) findViewById(R.id.su_checkbox);

        if (item.getItemId() == R.id.action_save) {
            sped.putString("mDNS1", mDNS1.getText().toString());
            sped.putString("mDNS2", mDNS2.getText().toString());
            sped.putString("mDNS3", mDNS3.getText().toString());

            sped.putString("wDNS1", wDNS1.getText().toString());
            sped.putString("wDNS2", wDNS2.getText().toString());
            sped.putString("wDNS3", wDNS3.getText().toString());
            sped.putBoolean("use_su", su_chkbox.isChecked());
            sped.commit();
            Toast.makeText(this, R.string.saved_toast, Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_samedns){
            wDNS1.setText(mDNS1.getText().toString());
            wDNS2.setText(mDNS2.getText().toString());
            wDNS3.setText(mDNS3.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }

}
