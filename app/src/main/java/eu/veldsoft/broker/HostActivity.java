package eu.veldsoft.broker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

/**
 * Host game screen.
 */
public class HostActivity extends Activity {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        String mac = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                    mac = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
                }

                if (mac == null || mac.equals("02:00:00:00:00:00")) {
                    String device = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    if (device != null) {
                        mac = device;
                    } else {
                        mac = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    }
                }
            } else {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                    mac = (((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo()).getMacAddress();
                }
            }
        } catch (Exception e) {
            Toast.makeText(HostActivity.this, R.string.mac_address_of_the_device_is_unknown_text, Toast.LENGTH_LONG).show();
        }

        ((EditText) findViewById(R.id.textDeviceMac1)).setText(mac);
    }
}
