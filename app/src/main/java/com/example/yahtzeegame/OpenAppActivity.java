package com.example.yahtzeegame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class OpenAppActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button proceed;
    public static String selectedbutton;

    public final static String selectedKey = "SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_app);

        radioGroup = findViewById(R.id.radiogroup);
//        radioButton = findViewById(R.id.radioButton);
        proceed = findViewById(R.id.proceed);
        selectedbutton = "";

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                selectedbutton = radioButton.getText().toString();
                Intent proceed_to_mainpage_intent = new Intent(OpenAppActivity.this, homepageactivity.class);
                proceed_to_mainpage_intent.putExtra(selectedKey, selectedbutton);
                startActivity(proceed_to_mainpage_intent);
                finish();
            }
        });
    }
}
