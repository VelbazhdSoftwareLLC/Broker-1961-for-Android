package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Company selection screen.
 */
public class CompanySelectionActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_selection);

        ((Button) findViewById(R.id.companySelected)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = -1;
                switch (((RadioGroup) findViewById(R.id.companies)).getCheckedRadioButtonId()) {
                    case R.id.radioButtonA:
                        index = 0;
                        break;
                    case R.id.radioButtonB:
                        index = 1;
                        break;
                    case R.id.radioButtonC:
                        index = 2;
                        break;
                    case R.id.radioButtonD:
                        index = 3;
                        break;
                }

                if (index == -1) {
                    Toast.makeText(CompanySelectionActivity.this, R.string.no_selected_company_text,
                            Toast.LENGTH_LONG).show();
                } else {
                    if (false) {
                        //TODO Check is the company selection valid.
                    } else {
                        int card = CompanySelectionActivity.this.getIntent().getIntExtra("card", -1);
                        setResult(AppCompatActivity.RESULT_OK, (new Intent()).putExtra("cardIndex", card).putExtra("companyIndex", index));
                        CompanySelectionActivity.this.finish();
                    }
                }
            }
        });
    }
}
