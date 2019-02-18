package com.example.yahtzeegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OpenAppActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button proceed;
    public static String selectedButton;

    public final static String selectedKey = "SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_app);

        radioGroup = findViewById(R.id.radiogroup);
        proceed = findViewById(R.id.proceed);
        selectedButton = "";

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                selectedButton = radioButton.getText().toString();
                Intent proceed_to_mainpage_intent = new Intent(OpenAppActivity.this, HomepageActivity.class);
                proceed_to_mainpage_intent.putExtra(selectedKey, selectedButton);
                startActivity(proceed_to_mainpage_intent);
                finish();
            }
        });
    }
}
