package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
                int shares[] = {0, 0, 0, 0};

                shares[0] = Integer.valueOf(((EditText) findViewById(R.id.aAmountNumber)).getText().toString());
                shares[1] = Integer.valueOf(((EditText) findViewById(R.id.bAmountNumber)).getText().toString());
                shares[2] = Integer.valueOf(((EditText) findViewById(R.id.cAmountNumber)).getText().toString());
                shares[3] = Integer.valueOf(((EditText) findViewById(R.id.dAmountNumber)).getText().toString());

                if (((RadioButton) findViewById(R.id.aBuyButton)).isChecked() == true) {
                    shares[0] = Math.abs(shares[0]);
                }
                if (((RadioButton) findViewById(R.id.aSellButton)).isChecked() == true) {
                    shares[0] = -Math.abs(shares[0]);
                }
                if (((RadioButton) findViewById(R.id.bBuyButton)).isChecked() == true) {
                    shares[1] = Math.abs(shares[1]);
                }
                if (((RadioButton) findViewById(R.id.bSellButton)).isChecked() == true) {
                    shares[1] = -Math.abs(shares[1]);
                }
                if (((RadioButton) findViewById(R.id.cBuyButton)).isChecked() == true) {
                    shares[2] = Math.abs(shares[2]);
                }
                if (((RadioButton) findViewById(R.id.cSellButton)).isChecked() == true) {
                    shares[2] = -Math.abs(shares[2]);
                }
                if (((RadioButton) findViewById(R.id.dBuyButton)).isChecked() == true) {
                    shares[3] = Math.abs(shares[3]);
                }
                if (((RadioButton) findViewById(R.id.dSellButton)).isChecked() == true) {
                    shares[3] = -Math.abs(shares[3]);
                }

                Intent intent = new Intent();
                intent.putExtra("buySellShares", shares);
                setResult(AppCompatActivity.RESULT_OK, intent);

                BuySellActivity.this.finish();
            }
        });
    }
}