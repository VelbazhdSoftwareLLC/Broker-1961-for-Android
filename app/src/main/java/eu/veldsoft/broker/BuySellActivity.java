package eu.veldsoft.broker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Buy or sell shares of the companies.
 */
public class BuySellActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

        ((Button) findViewById(R.id.tradeFinish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuySellActivity.this.finish();
            }
        });
    }
}