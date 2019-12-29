package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private int actionTypeId;
    private TextView textInput;
    private boolean isErrorRised = false;

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
    };

    private int[] commands = {
            R.id.button_c,
            R.id.button_clear,
            R.id.button_dot,
            R.id.button_equal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeHandlers();
    }

    private void setNumberHandler(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isErrorRised) {
                    textInput.append(button.getText());
                }
            }
        });
    }

    public void initializeHandlers() {

        textInput = findViewById(R.id.text_input);

        // NOTE: Set handlers for numbers pressing
        for (int i = 0; i < numbers.length; i++) {
            setNumberHandler((Button) findViewById(numbers[i]));
        }

        // NOTE: Set handlers for operators
        for (int v = 0; v < operators.length; v++) {
            final int finalV = v;
            findViewById(operators[v]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleClick(finalV);
                }
            });
        }

        // NOTE: Set handler for "c" button
        findViewById(commands[0]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInput.length() > 0 && !isErrorRised) {
                    textInput.setText(textInput.getText().toString().substring(0, textInput.length() - 1));
                }
            }
        });

        // NOTE: Set handler for "clear" button
        findViewById(commands[1]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isErrorRised = false;
                textInput.setText("");

                firstValue = Double.NaN;
                secondValue = Double.NaN;
            }
        });

        // NOTE: Set handler for "dot" button
        findViewById(commands[2]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = ((Button) v).getText().toString();
                String text = textInput.getText().toString();

                if (!text.isEmpty() && !text.substring(textInput.length() - 1).equals(buttonText) && !isErrorRised) {
                    textInput.append(buttonText);
                }
            }
        });

        // NOTE: Set handler for "equal" button
        findViewById(commands[3]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isErrorRised) {
                    performCalculating();
                    //binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                    //       decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                    firstValue = Double.NaN;
                    actionTypeId = 5;
                }
            }
        });
    }

    private void handleClick(int actionTypeId) {
        performCalculating();
        this.actionTypeId = actionTypeId;
        // TODO: log
        //binding.infoTextView.setText(binding.infoTextView.getText().toString() + decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
        textInput.setText("");
    }

    private void performCalculating() {
        try {
            if (!Double.isNaN(firstValue)) {
                secondValue = Double.parseDouble(textInput.getText().toString());
                textInput.setText(null);

                switch (actionTypeId) {
                    // NOTE: Sum
                    case 0: {
                        firstValue += secondValue;
                        break;
                    }
                    // NOTE: Subtract
                    case 1: {
                        firstValue -= secondValue;
                        break;
                    }
                    // NOTE: Multiple
                    case 2: {
                        firstValue *= secondValue;
                        break;
                    }
                    // NOTE: Division
                    case 3: {
                        firstValue /= secondValue;
                        break;
                    }
                    case 4: {
                        firstValue /= 100;
                        break;
                    }
                }
            } else {

                firstValue = Double.parseDouble(textInput.getText().toString());
            }
        } catch (Exception ex) {
            textInput.setText(getResources().getString(R.string.error_message));
            isErrorRised = true;
        }
    }
}



