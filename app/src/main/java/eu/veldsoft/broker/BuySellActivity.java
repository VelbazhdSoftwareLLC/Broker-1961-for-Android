package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Buy or sell shares of the companies.
 */
public class BuySellActivity extends Activity {

    /**
     * Trading possibilities of the player - money, shares, prices.
     */
    private int possibilities[] = {};

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
                int[] shares = {0, 0, 0, 0};

                if (((RadioButton) findViewById(R.id.aBuyButton)).isChecked()) {
                    shares[0] = Math.abs(Integer.valueOf(((EditText) findViewById(R.id.aAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.aSellButton)).isChecked()) {
                    shares[0] = -Math.abs(Integer.valueOf(((EditText) findViewById(R.id.aAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.bBuyButton)).isChecked()) {
                    shares[1] = Math.abs(Integer.valueOf(((EditText) findViewById(R.id.bAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.bSellButton)).isChecked()) {
                    shares[1] = -Math.abs(Integer.valueOf(((EditText) findViewById(R.id.bAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.cBuyButton)).isChecked()) {
                    shares[2] = Math.abs(Integer.valueOf(((EditText) findViewById(R.id.cAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.cSellButton)).isChecked()) {
                    shares[2] = -Math.abs(Integer.valueOf(((EditText) findViewById(R.id.cAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.dBuyButton)).isChecked()) {
                    shares[3] = Math.abs(Integer.valueOf(((EditText) findViewById(R.id.dAmountNumber)).getText().toString()));
                }
                if (((RadioButton) findViewById(R.id.dSellButton)).isChecked()) {
                    shares[3] = -Math.abs(Integer.valueOf(((EditText) findViewById(R.id.dAmountNumber)).getText().toString()));
                }

                Intent intent = new Intent();
                intent.putExtra("buySellShares", shares);
                setResult(Activity.RESULT_OK, intent);

                BuySellActivity.this.finish();
            }
        });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        possibilities = getIntent().getIntArrayExtra("possibilities");

        if (possibilities == null) {
            Toast.makeText(BuySellActivity.this, R.string.incorrect_trading_information_text, Toast.LENGTH_LONG).show();
            return;
        }

        ((TextView) findViewById(R.id.availableCash)).setText("" + possibilities[0]);

        ((EditText) findViewById(R.id.aAmountNumber)).setText("" + possibilities[1]);
        ((EditText) findViewById(R.id.bAmountNumber)).setText("" + possibilities[2]);
        ((EditText) findViewById(R.id.cAmountNumber)).setText("" + possibilities[3]);
        ((EditText) findViewById(R.id.dAmountNumber)).setText("" + possibilities[4]);

        ((TextView) findViewById(R.id.aCurrentPrice)).setText("" + possibilities[5]);
        ((TextView) findViewById(R.id.bCurrentPrice)).setText("" + possibilities[6]);
        ((TextView) findViewById(R.id.cCurrentPrice)).setText("" + possibilities[7]);
        ((TextView) findViewById(R.id.dCurrentPrice)).setText("" + possibilities[8]);
    }
}
