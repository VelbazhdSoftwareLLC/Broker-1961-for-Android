package eu.veldsoft.broker;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;

/**
 * Host game screen.
 */
public class HostActivity extends Activity {
    private WifiP2pManager manager = null;
    private WifiP2pManager.Channel channel = null;
    private WifiP2pManager.ActionListener listener = null;
    private BroadcastReceiver receiver = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

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
                        //TODO Wi-Fi is busy.
                        break;
                    case WifiP2pManager.ERROR:
                        //TODO Wi-Fi internal error.
                        break;
                    case WifiP2pManager.P2P_UNSUPPORTED:
                        //TODO Wi-Fi peer-to-peer is not supported.
                        break;
                    default:
                        //TODO Wi-Fi unknown error.
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
                    int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                    if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                        /*
                         * Wi-Fi Direct is enabled.
                         */
                    } else {
                        // Wi-Fi Direct is not enabled
                    }
                } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
                    if (manager != null) {
                        if (ActivityCompat.checkSelfPermission(HostActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        manager.requestPeers(channel, HostActivity.this::onPeersAvailable);
                    }
                } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                    if (manager == null) {
                        return;
                    }

                    WifiP2pInfo wifiP2pInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);
                    manager.requestConnectionInfo(channel, HostActivity.this::onConnectionInfoAvailable);
                } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                    // Code to handle changes in this device's details
                }
            }
        };
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

    public void onPeersAvailable(WifiP2pDeviceList peerList) {
        // Code to handle available peers
    }

    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        // Code to handle connection information (group owner, IP address, etc.)
    }
}
