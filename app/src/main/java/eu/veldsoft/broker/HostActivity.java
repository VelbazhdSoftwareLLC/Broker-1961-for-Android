package eu.veldsoft.broker;

import android.app.Activity;
import android.os.Bundle;

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
    }
}
