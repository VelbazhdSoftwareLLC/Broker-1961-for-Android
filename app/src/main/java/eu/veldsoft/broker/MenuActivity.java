package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Lobby menu screen.
 */
public class MenuActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
         * Open new game screen.
         */
        findViewById(R.id.single_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*
         *  Application exit.
         */
        findViewById(R.id.exit_game).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MenuActivity.this.finish();
                    }
                }
        );
    }
}