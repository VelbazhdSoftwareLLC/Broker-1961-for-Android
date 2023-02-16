package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Visualization of the cards hold by a particular player.
 */
public class PlayerCardsActivity extends AppCompatActivity {
    /**
     * The identifier for launching activity.
     */
    private final static int LAUNCH_COMPANY_SELECTION_ACTIVITY = 1;

    /**
     * Map of the card key and card image reference.
     */
    private static Map<String, Integer> CARDS_IMAGES = new HashMap<String, Integer>();

    /**
     * Array with cards' keys.
     */
    private String[] keys = new String[0];

    /**
     * Card image view reference.
     */
    private ImageView image = null;

    /**
     * Seek bar reference.
     */
    private SeekBar bar = null;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_cards);

        CARDS_IMAGES = GameActivity.CARDS_IMAGES;

        image = ((ImageView) findViewById(R.id.currentCard));
        bar = findViewById(R.id.cardSelector);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (keys != null && keys.length > 0) {
                    image.setImageResource(CARDS_IMAGES.get(keys[i]));
                } else {
                    image.setImageResource(R.drawable.back);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ((Button) findViewById(R.id.playIt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameActivity.board().needCompanySelection(bar.getProgress())) {
                    startActivityForResult((new Intent(PlayerCardsActivity.this, CompanySelectionActivity.class)).putExtra("card", bar.getProgress()), LAUNCH_COMPANY_SELECTION_ACTIVITY);
                } else {
                    setResult(AppCompatActivity.RESULT_OK, (new Intent()).putExtra("cardIndex", bar.getProgress()).putExtra("companyIndex", -1));
                    PlayerCardsActivity.this.finish();
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();

        keys = getIntent().getStringArrayExtra("keys");

        if (keys != null && keys.length > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                bar.setMin(0);
            }
            bar.setMax(keys.length - 1);
        } else {
            image.setImageResource(R.drawable.back);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                bar.setMin(0);
            }
            bar.setMax(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();

        keys = new String[0];

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bar.setMin(0);
        }
        bar.setMax(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_COMPANY_SELECTION_ACTIVITY) {
            setResult(AppCompatActivity.RESULT_OK, (new Intent()).putExtra("cardIndex", data.getIntExtra("cardIndex", -1)).putExtra("companyIndex", data.getIntExtra("companyIndex", -1)));
            PlayerCardsActivity.this.finish();
        }
    }
}
