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


    public boolean playerChoiceIsselected(){
        return !selectedbutton.equals("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_app);

        radioGroup = findViewById(R.id.radiogroup);
//        radioButton = findViewById(R.id.radioButton);
        proceed = findViewById(R.id.proceede);
        selectedbutton = "";

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                selectedbutton = radioButton.getText().toString();

                if(playerChoiceIsselected()){
                    Intent proceed_to_mainpage_intent = new Intent(OpenAppActivity.this, homepageactivity.class);
                    startActivity(proceed_to_mainpage_intent);
                    finish();
                }else{

//                    Intent proceed_to_mainpage_intent = new Intent(OpenAppActivity.this, OpenAppActivity.class);
//                    startActivity(proceed_to_mainpage_intent);
                    Context context = getApplicationContext();
                    CharSequence text = "Please Select Player Number Option!!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }
}
