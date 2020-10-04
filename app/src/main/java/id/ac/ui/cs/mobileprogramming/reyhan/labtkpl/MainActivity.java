package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Source: https://medium.com/@muhzanard25/membuat-stopwatch-dengan-android-studio-8902e592fb6a

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Stopwatch");

        TextView timeCounter = findViewById(R.id.timeCounter);
        Button primary = findViewById(R.id.primaryButton);
        Button secondary = findViewById(R.id.secondaryButton);
        final Stopwatch stopwatch = new Stopwatch(timeCounter, primary, secondary);

        primary.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (stopwatch.isRunning) {
                    stopwatch.pause();
                } else {
                    stopwatch.start();
                }
            }
        });

        secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.reset();
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Source: https://stackoverflow.com/a/7240268/10316220
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
