package com.numad.numadsu_alangrinberg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        Button btn1 = findViewById(R.id.button4);
        Button btn2 = findViewById(R.id.button5);
        Button btn3 = findViewById(R.id.button6);
        Button btn4 = findViewById(R.id.button7);
        Button btn5 = findViewById(R.id.button8);
        Button btn6 = findViewById(R.id.button9);

        btn1.setOnClickListener(btn1Listener);
        btn2.setOnClickListener(btn2Listener);
        btn3.setOnClickListener(btn3Listener);
        btn4.setOnClickListener(btn4Listener);
        btn5.setOnClickListener(btn5Listener);
        btn6.setOnClickListener(btn6Listener);

//  TODO: May not need this
//  ArrayList<Button> buttons = new ArrayList<>();
//
//        buttons.add(btn1);
//        buttons.add(btn2);
//        buttons.add(btn3);
//        buttons.add(btn4);
//        buttons.add(btn5);
//        buttons.add(btn6);

    }

    private View.OnClickListener btn1Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressA);
        }
    };
    private View.OnClickListener btn2Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressB);
        }
    };
    private View.OnClickListener btn3Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressD);
        }
    };
    private View.OnClickListener btn4Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressC);
        }
    };
    private View.OnClickListener btn5Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressE);
        }
    };
    private View.OnClickListener btn6Listener = new View.OnClickListener() {
        public void onClick(View v) {
            TextView tv = findViewById(R.id.textView3);
            tv.setText(R.string.pressF);
        }
    };

}
