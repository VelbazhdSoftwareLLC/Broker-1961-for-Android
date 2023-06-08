package eu.veldsoft.broker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Player report screen.
 */
public class PlayerReportActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_report);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        ((TextView) findViewById(R.id.playerReportText)).setText(getIntent().getStringExtra("report"));
    }
}
