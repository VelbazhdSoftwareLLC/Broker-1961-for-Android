package eu.veldsoft.broker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * End game report screen.
 */
public class EndReportActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_report);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        ((TextView) findViewById(R.id.endReportText)).setText(getIntent().getStringExtra("report"));
    }
}
