package eu.veldsoft.broker;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Visualization of the cards played during the gameplay.
 */
public class PlayedCardsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_played_cards);

        CARDS_IMAGES = GameActivity.CARDS_IMAGES;

        image = ((ImageView) findViewById(R.id.playedCard));
        bar = findViewById(R.id.cardsScroller);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (keys != null && keys.length > 0) {
                    image.setImageResource(CARDS_IMAGES.get(keys[keys.length - i - 1]));
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
}
