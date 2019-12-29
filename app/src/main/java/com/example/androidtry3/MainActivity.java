package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private void setNumberHandler(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_input.append(button.getText());
            }
        });
    }

    // TODO: make it works
    private void setOperatorHandler(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_input.append(button.getText());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_input = findViewById(R.id.text_input);
        initializeHandlers();
    }

    public void initializeHandlers() {

        // NOTE: Set handlers for numbers pressing
        for(int i = 0; i < numbers.length; i++) {
            setNumberHandler((Button) findViewById(numbers[i]));
        }

        // NOTE: Set handlers for operators pressing
        for(int i = 0; i < operators.length; i++) {
            setOperatorHandler((Button) findViewById(operators[i]));
        }

        // NOTE: Set handler for "c" button
        findViewById(commands[0]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_input.length() > 0) {
                    text_input.setText(text_input.getText().toString().substring(0, text_input.length() - 1));
                }
            }
        });

        // NOTE: Set handler for "clear" button
        findViewById(commands[1]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_input.setText("");
            }
        });

        // NOTE: Set handlers for "dot" button
        findViewById(commands[2]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = ((Button) v).getText().toString();
                String textInput = text_input.getText().toString();
                if(textInput != null && !textInput.isEmpty() && !textInput.substring(textInput.length() - 1).equals(buttonText)) {
                    text_input.append(buttonText);
                }
            }
        });
    }

}
