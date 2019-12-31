package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private double firstValue = Double.NaN;
    private double secondValue = Double.NaN;
    private TextView textInput;
    private Operators operator;
    private Button dotButton;

    private enum Operators  {
            SUM,
            SUBTRACT,
            MULTIPLY,
            DIVIDE,
            PERCENTAGE,
        // TODO: Add some
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.text_input);
        dotButton = findViewById(R.id.button_dot);
        setButtonHandlers();
    }

    private void numberHandler(View view) {
        Button button = (Button) view;

        if (button.getText().equals(".") || textInput.getText().toString().contains(".")) {
            dotButton.setEnabled(false);
        }
        textInput.append(button.getText() + "");
    }

    public void operatorHandler(Operators inputOperator) {
        firstValue = Double.parseDouble(textInput.getText().toString());
        textInput.setText("");
        dotButton.setEnabled(true);
        operator = inputOperator;
    }

    public void equalHandler(View view) {
        secondValue = Double.parseDouble(textInput.getText().toString());
        dotButton.setEnabled(true);

        switch (operator) {
            case SUM:
                firstValue += secondValue;
                break;
            case SUBTRACT:
                firstValue -= secondValue;
                break;
            case MULTIPLY:
                firstValue *= secondValue;
                break;
            case DIVIDE:
                firstValue /= secondValue;
                break;

            // TODO : ADD SINCOS% etc
            default:
                firstValue = secondValue;
        }
        textInput.setText(firstValue + "");
    }

    private void setButtonHandlers() {
        // NOTE: Numbers
        findViewById(R.id.button_zero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });
        findViewById(R.id.button_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberHandler(v);
            }
        });

        // NOTE: Equal
        findViewById(R.id.button_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalHandler(v);
            }
        });

        // NOTE: Commands
        // Set handler for "c" button
        findViewById(R.id.button_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textInput.length() > 0) {
                    textInput.setText(textInput.getText().toString().substring(0, textInput.length() - 1));
                }
            }
        });

        // NOTE: Set handler for "clear" button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInput.setText("");

                firstValue = Double.NaN;
                secondValue = Double.NaN;
            }
        });

        // NOTE: Operators
        findViewById(R.id.button_sum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorHandler(Operators.SUM);
            }
        });

        findViewById(R.id.button_subtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorHandler(Operators.SUBTRACT);
            }
        });

        findViewById(R.id.button_multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorHandler(Operators.MULTIPLY);
            }
        });

        findViewById(R.id.button_division).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorHandler(Operators.DIVIDE);
            }
        });
    }
}


