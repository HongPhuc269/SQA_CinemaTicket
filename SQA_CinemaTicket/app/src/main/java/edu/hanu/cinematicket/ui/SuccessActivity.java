package edu.hanu.cinematicket.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.hanu.cinematicket.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        View viewSuccess = findViewById(R.id.viewSuccess);
        viewSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentContinue = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intentContinue);
            }
        });
    }
}