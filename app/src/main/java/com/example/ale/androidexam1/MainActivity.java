package com.example.ale.androidexam1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button toastBtn;
    private Button countBtn;
    private Button zeroBtn;
    private TextView view;
    private int count = 0;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Grab the button objects from the layout
        toastBtn = findViewById(R.id.toastBtn);
        countBtn = findViewById(R.id.countBtn);
        zeroBtn = findViewById(R.id.zeroBtn);
        view = findViewById(R.id.textView);

        settings = getSharedPreferences("CounterValue", MODE_PRIVATE);
        editor = settings.edit();
        count = settings.getInt("Counter", 0);
        view.setText(String.valueOf(count));

        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToastMessage();
            }
        });

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCounter();
            }
        });
    }

    protected void onPause(){
        super.onPause();
    }

    private void resetCounter() {
        count = 0;
        view.setText(String.valueOf(count));
        editor.putInt("Counter", count);
        editor.commit();
    }

    private void displayToastMessage(){
        Context context = getApplicationContext();
        CharSequence text = view.getText();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void count(){
        count++;
        view.setText(String.valueOf(count));
        editor.putInt("Counter", count);
        editor.commit();
    }
}
