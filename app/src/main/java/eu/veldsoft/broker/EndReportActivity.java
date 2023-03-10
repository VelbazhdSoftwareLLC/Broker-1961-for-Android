package eu.veldsoft.broker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * End game report screen.
 */
public class EndReportActivity extends AppCompatActivity {

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