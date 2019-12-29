package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView text_input;

    private int[] numbers = {
            R.id.button_one,
            R.id.button_two,
            R.id.button_three,
            R.id.button_four,
            R.id.button_five,
            R.id.button_six,
            R.id.button_seven,
            R.id.button_eight,
            R.id.button_nine,
            R.id.button_zero,
    };

    private int[] operators = {
            R.id.button_sum,
            R.id.button_subtract,
            R.id.button_multiple,
            R.id.button_division,
            R.id.button_percentage,
            R.id.button_equal
    };

    private int[] commands = {
            R.id.button_c,
            R.id.button_clear,
            R.id.button_dot
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_input = findViewById(R.id.text_input);
    }
}
