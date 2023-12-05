package eu.veldsoft.broker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Number of players ane their names.
 */
public class NumberOfPlayersActivity extends Activity {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_players);

        ((Button) findViewById(R.id.playersDone))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*
                         * Store players' information into shared preferences.
                         */
                        SharedPreferences.Editor editor = getSharedPreferences("eu.veldsoft.broker.NumberOfPlayersActivity", Context.MODE_PRIVATE).edit();

                        editor.putBoolean("player1Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer1)).isChecked());
                        editor.putString("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
                        editor.putBoolean("player1Computer", ((CheckBox) findViewById(R.id.computerPlayer1)).isChecked());

                        editor.putBoolean("player2Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer2)).isChecked());
                        editor.putString("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
                        editor.putBoolean("player2Computer", ((CheckBox) findViewById(R.id.computerPlayer2)).isChecked());

                        editor.putBoolean("player3Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer3)).isChecked());
                        editor.putString("player3Name", ((EditText) findViewById(R.id.textNamePlayer3)).getText().toString());
                        editor.putBoolean("player3Computer", ((CheckBox) findViewById(R.id.computerPlayer3)).isChecked());

                        editor.putBoolean("player4Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer4)).isChecked());
                        editor.putString("player4Name", ((EditText) findViewById(R.id.textNamePlayer4)).getText().toString());
                        editor.putBoolean("player4Computer", ((CheckBox) findViewById(R.id.computerPlayer4)).isChecked());

                        editor.putBoolean("player5Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer5)).isChecked());
                        editor.putString("player5Name", ((EditText) findViewById(R.id.textNamePlayer5)).getText().toString());
                        editor.putBoolean("player5Computer", ((CheckBox) findViewById(R.id.computerPlayer5)).isChecked());

                        editor.putBoolean("player6Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer6)).isChecked());
                        editor.putString("player6Name", ((EditText) findViewById(R.id.textNamePlayer6)).getText().toString());
                        editor.putBoolean("player6Computer", ((CheckBox) findViewById(R.id.computerPlayer6)).isChecked());

                        editor.apply();

                        /*
                         * Return players information to the caller.
                         */
                        Intent intent = new Intent();

                        intent.putExtra("player1Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer1)).isChecked());
                        intent.putExtra("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
                        intent.putExtra("player1Computer", ((CheckBox) findViewById(R.id.computerPlayer1)).isChecked());

                        intent.putExtra("player2Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer2)).isChecked());
                        intent.putExtra("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
                        intent.putExtra("player2Computer", ((CheckBox) findViewById(R.id.computerPlayer2)).isChecked());

                        intent.putExtra("player3Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer3)).isChecked());
                        intent.putExtra("player3Name", ((EditText) findViewById(R.id.textNamePlayer3)).getText().toString());
                        intent.putExtra("player3Computer", ((CheckBox) findViewById(R.id.computerPlayer3)).isChecked());

                        intent.putExtra("player4Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer4)).isChecked());
                        intent.putExtra("player4Name", ((EditText) findViewById(R.id.textNamePlayer4)).getText().toString());
                        intent.putExtra("player4Computer", ((CheckBox) findViewById(R.id.computerPlayer4)).isChecked());

                        intent.putExtra("player5Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer5)).isChecked());
                        intent.putExtra("player5Name", ((EditText) findViewById(R.id.textNamePlayer5)).getText().toString());
                        intent.putExtra("player5Computer", ((CheckBox) findViewById(R.id.computerPlayer5)).isChecked());

                        intent.putExtra("player6Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer6)).isChecked());
                        intent.putExtra("player6Name", ((EditText) findViewById(R.id.textNamePlayer6)).getText().toString());
                        intent.putExtra("player6Computer", ((CheckBox) findViewById(R.id.computerPlayer6)).isChecked());

                        setResult(Activity.RESULT_OK, intent);

                        NumberOfPlayersActivity.this.finish();
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        super.onResume();

        /*
         * Load players' information from the shared preferences.
         */
        SharedPreferences preferences = getSharedPreferences("eu.veldsoft.broker.NumberOfPlayersActivity", Context.MODE_PRIVATE);

        ((CheckBox) findViewById(R.id.ckeckPlayer1)).setChecked(preferences.getBoolean("player1Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer1)).setText(preferences.getString("player1Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer1)).setChecked(preferences.getBoolean("player1Computer", false));

        ((CheckBox) findViewById(R.id.ckeckPlayer2)).setChecked(preferences.getBoolean("player2Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer2)).setText(preferences.getString("player2Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer2)).setChecked(preferences.getBoolean("player2Computer", false));

        ((CheckBox) findViewById(R.id.ckeckPlayer3)).setChecked(preferences.getBoolean("player3Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer3)).setText(preferences.getString("player3Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer3)).setChecked(preferences.getBoolean("player3Computer", false));

        ((CheckBox) findViewById(R.id.ckeckPlayer4)).setChecked(preferences.getBoolean("player4Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer4)).setText(preferences.getString("player4Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer4)).setChecked(preferences.getBoolean("player4Computer", false));

        ((CheckBox) findViewById(R.id.ckeckPlayer5)).setChecked(preferences.getBoolean("player5Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer5)).setText(preferences.getString("player5Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer5)).setChecked(preferences.getBoolean("player5Computer", false));

        ((CheckBox) findViewById(R.id.ckeckPlayer6)).setChecked(preferences.getBoolean("player6Enabled", false));
        ((EditText) findViewById(R.id.textNamePlayer6)).setText(preferences.getString("player6Name", ""));
        ((CheckBox) findViewById(R.id.computerPlayer6)).setChecked(preferences.getBoolean("player6Computer", false));
    }
}
