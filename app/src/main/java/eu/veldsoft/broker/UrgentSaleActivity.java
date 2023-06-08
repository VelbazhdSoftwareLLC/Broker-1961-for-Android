package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Urgent sale window.
 */
public class UrgentSaleActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_sale);

        /*
         * Calculate the risen money.
         */
        TextView.OnEditorActionListener listener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                int amount = 0;

                amount += Integer.valueOf(((EditText) findViewById(R.id.aAmountUrgent)).getText().toString()) * Integer.valueOf(((EditText) findViewById(R.id.aAmountPrice)).getText().toString());
                amount += Integer.valueOf(((EditText) findViewById(R.id.bAmountUrgent)).getText().toString()) * Integer.valueOf(((EditText) findViewById(R.id.bAmountPrice)).getText().toString());
                amount += Integer.valueOf(((EditText) findViewById(R.id.cAmountUrgent)).getText().toString()) * Integer.valueOf(((EditText) findViewById(R.id.cAmountPrice)).getText().toString());
                amount += Integer.valueOf(((EditText) findViewById(R.id.dAmountUrgent)).getText().toString()) * Integer.valueOf(((EditText) findViewById(R.id.dAmountPrice)).getText().toString());

                ((TextView) findViewById(R.id.risenMoney)).setText("" + amount);

                return true;
            }
        };

        ((EditText) findViewById(R.id.aAmountUrgent)).setOnEditorActionListener(listener);
        ((EditText) findViewById(R.id.bAmountUrgent)).setOnEditorActionListener(listener);
        ((EditText) findViewById(R.id.cAmountUrgent)).setOnEditorActionListener(listener);
        ((EditText) findViewById(R.id.dAmountUrgent)).setOnEditorActionListener(listener);

        ((Button) findViewById(R.id.urgentSell)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int prices[] = {
                        Integer.valueOf(((EditText) findViewById(R.id.aAmountPrice)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.bAmountPrice)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.cAmountPrice)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.dAmountPrice)).getText().toString()),
                };

                int sold[] = {
                        Integer.valueOf(((EditText) findViewById(R.id.aAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.bAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.cAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.dAmountUrgent)).getText().toString()),
                };

                int held[] = {
                        Integer.valueOf(((EditText) findViewById(R.id.aAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.bAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.cAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.dAmountAvailable)).getText().toString()),
                };

                int risen = 0;
                int difference = 0;
                for (int i = 0; i < sold.length && i < prices.length && i < held.length; i++) {
                    risen += prices[i] * prices[i];

                    if (held[i] > sold[i]) {
                        sold[i] = held[i];
                    }

                    difference += held[i] - sold[i];
                }

                ((TextView) findViewById(R.id.risenMoney)).setText("" + risen);

                if (Integer.valueOf(((TextView) findViewById(R.id.risenMoney)).getText().toString()) < Integer.valueOf(((TextView) findViewById(R.id.urgentMoney)).getText().toString()) && difference > 0) {
                    Toast.makeText(UrgentSaleActivity.this, R.string.sell_more_text, Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("soldShares", sold);
                intent.putExtra("playerIndex", getIntent().getIntExtra("playerIndex", -1));
                setResult(Activity.RESULT_OK, intent);

                UrgentSaleActivity.this.finish();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        setTitle(getIntent().getStringExtra("name"));

        ((TextView) findViewById(R.id.urgentMoney)).setText("" + getIntent().getIntExtra("shortage", 0));

        /*
         * First text is about quantity available.
         */
        int quantities[] = getIntent().getIntArrayExtra("quantities");
        if (quantities != null) {
            ((EditText) findViewById(R.id.aAmountAvailable)).setText("" + quantities[0]);
            ((EditText) findViewById(R.id.bAmountAvailable)).setText("" + quantities[1]);
            ((EditText) findViewById(R.id.cAmountAvailable)).setText("" + quantities[2]);
            ((EditText) findViewById(R.id.dAmountAvailable)).setText("" + quantities[3]);
        }

        /*
         * Second text is about prices for the companies.
         */
        int prices[] = getIntent().getIntArrayExtra("prices");
        if (prices != null) {
            ((EditText) findViewById(R.id.aAmountPrice)).setText("" + prices[0]);
            ((EditText) findViewById(R.id.bAmountPrice)).setText("" + prices[1]);
            ((EditText) findViewById(R.id.cAmountPrice)).setText("" + prices[2]);
            ((EditText) findViewById(R.id.dAmountPrice)).setText("" + prices[3]);
        }
    }
}
