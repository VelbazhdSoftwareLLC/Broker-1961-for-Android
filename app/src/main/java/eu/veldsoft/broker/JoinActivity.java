package eu.veldsoft.broker;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

/**
 * Join game screen.
 */
public class JoinActivity extends Activity {
    /**
     *
     */
    private WifiP2pManager manager = null;

    //TODO Many be channel object should be global.
    /**
     *
     */
    private WifiP2pManager.Channel channel = null;

    /**
     *
     */
    private WifiP2pManager.ActionListener listener = null;

    /**
     *
     */
    private BroadcastReceiver receiver = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);

        channel = manager.initialize(this, getMainLooper(), new WifiP2pManager.ChannelListener() {
            @Override
            public void onChannelDisconnected() {
                //TODO Handle disconnect.
            }
        });

        listener = new WifiP2pManager.ActionListener() {
            public void onFailure(int reason) {
                switch (reason) {
                    case WifiP2pManager.BUSY:
                        Toast.makeText(JoinActivity.this, R.string.wi_fi_is_busy_text, Toast.LENGTH_LONG).show();
                        break;
                    case WifiP2pManager.ERROR:
                        Toast.makeText(JoinActivity.this, R.string.wi_fi_internal_error_text, Toast.LENGTH_LONG).show();
                        break;
                    case WifiP2pManager.P2P_UNSUPPORTED:
                        Toast.makeText(JoinActivity.this, R.string.wi_fi_peer_to_peer_is_not_supported_text, Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(JoinActivity.this, R.string.wi_fi_unknown_error_text, Toast.LENGTH_LONG).show();
                        break;
                }
            }

            public void onSuccess() {
            }
        };

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                    if (intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1) != WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        Toast.makeText(JoinActivity.this, R.string.wi_fi_is_not_enabled_text, Toast.LENGTH_LONG).show();
                    }
                } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                    if (manager != null) {
                        if (ActivityCompat.checkSelfPermission(JoinActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            //TODO Ask for permissions.
                            return;
                        }
                        manager.requestPeers(channel, JoinActivity.this::onPeersAvailable);
                    }
                } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                    if (manager == null) {
                        return;
                    }

                    intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);
                    manager.requestConnectionInfo(channel, JoinActivity.this::onConnectionInfoAvailable);
                } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                }
            }
        };

        manager.discoverPeers(channel, listener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
         * Monitor Wi-Fi peer-to-peer status.
         */
        IntentFilter filter = new IntentFilter();

        filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(receiver);
    }

    public void onPeersAvailable(WifiP2pDeviceList list) {
    }

    public void onConnectionInfoAvailable(WifiP2pInfo info) {
    }
}
