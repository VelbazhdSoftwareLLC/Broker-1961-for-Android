package eu.veldsoft.broker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Urgent sale window.
 */
public class UrgentSaleActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_sale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        setTitle(">>> Player Name <<<");
    }
}