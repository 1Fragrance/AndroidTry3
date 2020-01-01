package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private double firstValue;
    private double secondValue;
    private Double result;
    private boolean isErrorState;
    private boolean isRepeatableOperation;

    private TextView textInput;
    private Operators operator;
    private Button dotButton;

    private enum Operators {
        SUM,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE
        // TODO: Add some
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.text_input);
        dotButton = findViewById(R.id.button_dot);
        resetCalc();
        setButtonHandlers();
    }

    private double getInputValue() {
        return Double.parseDouble((textInput.getText().toString()).replace(",", "."));
    }

    private void numberHandler(View view) {
        if(isErrorState) {
            return;
        }

        if (result != null) {
            resetCalc();
        }

        Button button = (Button) view;
        if (button.getText().equals(".") || textInput.getText().toString().contains(".")) {
            dotButton.setEnabled(false);
        }
        else {
            dotButton.setEnabled(true);
        }

        textInput.append(button.getText() + "");
    }

    public void operatorHandler(Operators inputOperator) {
        if(isErrorState) {
            return;
        }

        if (textInput.length() > 0) {
            firstValue = getInputValue();
            secondValue = 0;
            result = null;
            textInput.setText("");
        }
        isRepeatableOperation = false;
        operator = inputOperator;
    }

    public void equalHandler(View view) {
        if (textInput.length() > 0 && !isErrorState) {
            if (isRepeatableOperation) {
                firstValue = result;
            } else {
                secondValue = getInputValue();
            }
            dotButton.setEnabled(false);

            switch (operator) {
                case SUM:
                    result = firstValue + secondValue;
                    break;
                case SUBTRACT:
                    result = firstValue - secondValue;
                    break;
                case MULTIPLY:
                    result = firstValue * secondValue;
                    break;
                case DIVIDE:
                    if(secondValue == 0) {
                        resetCalc();
                        textInput.setText(getResources().getString(R.string.error_message));
                        isErrorState = true;
                        return;
                    }
                    result = firstValue / secondValue;
                    break;

                default:
                    result = firstValue;
            }
            isRepeatableOperation = true;

            Log.println(Log.INFO, "*** first: ", Double.toString(firstValue));
            Log.println(Log.INFO, "*** second: ", Double.toString(secondValue));
            Log.println(Log.INFO, "*** result: ", Double.toString(result));

            textInput.setText(Double.toString(result));
        }
    }

    private void resetCalc() {
        textInput.setText("");
        firstValue = 0;
        secondValue = 0;
        result = null;
        operator = Operators.NONE;
        isRepeatableOperation = false;
        isErrorState = false;
        dotButton.setEnabled(false);
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

        // NOTE: Add handler for "+-" button
        findViewById(R.id.button_negation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInput.length() > 0 && !isErrorState) {
                    String text = textInput.getText().toString();
                    if (text.startsWith("-")) {
                        textInput.setText(text.replace("-", ""));
                    } else {
                        textInput.setText("-" + text);
                    }
                }
            }
        });

        // NOTE: Set handler for "clear" button
        findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCalc();
                dotButton.setEnabled(false);
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


