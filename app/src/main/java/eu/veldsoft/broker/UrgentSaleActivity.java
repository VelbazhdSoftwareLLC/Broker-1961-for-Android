package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Urgent sale window.
 */
public class UrgentSaleActivity extends Activity {

    /**
     * Quantities available for sell.
     */
    private int quantities[] = {0, 0, 0, 0};

    /**
     * Companies shares prices.
     */
    private int prices[] = {0, 0, 0, 0};

    /**
     * Numbers of sold share.
     */
    private int sold[] = {0, 0, 0, 0};

    /**
     * Number of not sold shares.
     */
    private int held[] = {0, 0, 0, 0};

    /**
     * Try to change according to available shares.
     *
     * @param index      Index of the company.
     * @param difference Difference in the number of shares.
     */
    private void tryToChange(int index, int difference) {
        if (difference == -1 && held[index] > 0) {
            held[index]--;
            sold[index]++;
        }

        if (difference == +1 && sold[index] > 0) {
            held[index]++;
            sold[index]--;
        }
    }

    /**
     * Update user interface.
     */
    private void updateViews() {
        ((EditText) findViewById(R.id.aAmountAvailable)).setText("" + held[0]);
        ((EditText) findViewById(R.id.aAmountUrgent)).setText("" + sold[0]);
        ((EditText) findViewById(R.id.bAmountAvailable)).setText("" + held[1]);
        ((EditText) findViewById(R.id.bAmountUrgent)).setText("" + sold[1]);
        ((EditText) findViewById(R.id.cAmountAvailable)).setText("" + held[2]);
        ((EditText) findViewById(R.id.cAmountUrgent)).setText("" + sold[2]);
        ((EditText) findViewById(R.id.dAmountAvailable)).setText("" + held[3]);
        ((EditText) findViewById(R.id.dAmountUrgent)).setText("" + sold[3]);

        /*
         * Calculate the risen money.
         */
        int risen = 0;
        for (int i = 0; i < sold.length && i < prices.length && i < held.length; i++) {
            risen += sold[i] * prices[i];
        }

        ((TextView) findViewById(R.id.risenMoney)).setText("" + risen);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urgent_sale);

        findViewById(R.id.aUrgentMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(0, -1);
                updateViews();
            }
        });
        findViewById(R.id.bUrgentMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(1, -1);
                updateViews();
            }
        });
        findViewById(R.id.cUrgentMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(2, -1);
                updateViews();
            }
        });
        findViewById(R.id.dUrgentMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(3, -1);
                updateViews();
            }
        });

        findViewById(R.id.aUrgentPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(0, +1);
                updateViews();
            }
        });
        findViewById(R.id.bUrgentPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(1, +1);
                updateViews();
            }
        });
        findViewById(R.id.cUrgentPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(2, +1);
                updateViews();
            }
        });
        findViewById(R.id.dUrgentPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToChange(3, +1);
                updateViews();
            }
        });

        ((Button) findViewById(R.id.urgentSell)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sold = new int[]{
                        Integer.valueOf(((EditText) findViewById(R.id.aAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.bAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.cAmountUrgent)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.dAmountUrgent)).getText().toString()),
                };

                held = new int[]{
                        Integer.valueOf(((EditText) findViewById(R.id.aAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.bAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.cAmountAvailable)).getText().toString()),
                        Integer.valueOf(((EditText) findViewById(R.id.dAmountAvailable)).getText().toString()),
                };

                int risen = 0;
                int difference = 0;
                for (int i = 0; i < sold.length && i < prices.length && i < held.length; i++) {
                    risen += sold[i] * prices[i];

                    if (held[i] > sold[i]) {
                        sold[i] = held[i];
                    }

                    difference += held[i] - sold[i];

                    /*
                     * In the object model sales are marked with negative numbers.
                     */
                    sold[i] = -sold[i];
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
        quantities = getIntent().getIntArrayExtra("quantities");
        if (quantities != null) {
            ((EditText) findViewById(R.id.aAmountAvailable)).setText("" + quantities[0]);
            ((EditText) findViewById(R.id.bAmountAvailable)).setText("" + quantities[1]);
            ((EditText) findViewById(R.id.cAmountAvailable)).setText("" + quantities[2]);
            ((EditText) findViewById(R.id.dAmountAvailable)).setText("" + quantities[3]);

            held = Arrays.copyOf(quantities, quantities.length);
        }

        /*
         * Second text is about prices for the companies.
         */
        prices = getIntent().getIntArrayExtra("prices");
        if (prices != null) {
            ((EditText) findViewById(R.id.aAmountPrice)).setText("" + prices[0]);
            ((EditText) findViewById(R.id.bAmountPrice)).setText("" + prices[1]);
            ((EditText) findViewById(R.id.cAmountPrice)).setText("" + prices[2]);
            ((EditText) findViewById(R.id.dAmountPrice)).setText("" + prices[3]);
        }
    }
}
