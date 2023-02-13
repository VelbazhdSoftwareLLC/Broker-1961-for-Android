package eu.veldsoft.broker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Number of players ane their names.
 */
public class NumberOfPlayersActivity extends AppCompatActivity {

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
                        Intent intent = new Intent();
                        intent.putExtra("player1Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer1)).isChecked());
                        intent.putExtra("player1Name", ((EditText) findViewById(R.id.textNamePlayer1)).getText().toString());
                        intent.putExtra("player2Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer2)).isChecked());
                        intent.putExtra("player2Name", ((EditText) findViewById(R.id.textNamePlayer2)).getText().toString());
                        intent.putExtra("player3Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer3)).isChecked());
                        intent.putExtra("player3Name", ((EditText) findViewById(R.id.textNamePlayer3)).getText().toString());
                        intent.putExtra("player4Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer4)).isChecked());
                        intent.putExtra("player4Name", ((EditText) findViewById(R.id.textNamePlayer4)).getText().toString());
                        intent.putExtra("player5Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer5)).isChecked());
                        intent.putExtra("player5Name", ((EditText) findViewById(R.id.textNamePlayer5)).getText().toString());
                        intent.putExtra("player6Enabled", ((CheckBox) findViewById(R.id.ckeckPlayer6)).isChecked());
                        intent.putExtra("player6Name", ((EditText) findViewById(R.id.textNamePlayer6)).getText().toString());
                        setResult(AppCompatActivity.RESULT_OK, intent);

                        NumberOfPlayersActivity.this.finish();
                    }
                });
    }
}
