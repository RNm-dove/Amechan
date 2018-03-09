package org.cord4handai.amechan.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.cord4handai.amechan.R;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay);

        Button sendButton = findViewById(R.id.sendingbutton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getApplicationContext())
                        .setMessage("投稿しました。")
                        .setPositiveButton("OK", null)
                        .create();

                finish();

            }

        });
    }
}
