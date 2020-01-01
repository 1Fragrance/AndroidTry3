package com.example.androidtry3;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private double firstValue;
    private double secondValue;
    private Double result;
    private boolean isErrorState;
    private boolean isRepeatableOperation;

    private TextView textInput;
    private Operators operator;
    private Button dotButton;

    private ArrayList<String> historyList;
    private ArrayAdapter<String> historyAdapter;

    // NOTE: List of available operators
    private enum Operators {
        SUM,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE,
        THIRD_ROOT,
        SECOND_ROOT,
        THIRD_GRADE,
        SECOND_GRADE,
        SIN,
        COS,
        TG,
        CTG;

        @Override
        public String toString() {
            return this.name();
        }
    }

    // NOTE: Activity initialize logic
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.text_input);
        dotButton = findViewById(R.id.button_dot);

        historyList = new ArrayList<>();
        historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        ListView listView = findViewById(R.id.history);
        listView.setAdapter(historyAdapter);

        resetCalc();
        setButtonHandlers();
    }

    // NOTE: Save activity state before it was destroyed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("firstValue", firstValue);
        outState.putDouble("secondValue", secondValue);
        if(result != null) {
            outState.putDouble("result", result);
        }
        outState.putBoolean("isErrorState", isErrorState);
        outState.putBoolean("isRepeatableOperation", isRepeatableOperation);

        ArrayList<String> tempList = new ArrayList<>(historyList);
        outState.putStringArrayList("historyList", tempList);

        outState.putString("textInput", textInput.getText().toString());
        outState.putString("operator", operator.toString());
    }

    // NOTE: Restore activity state from bundle
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        firstValue = savedInstanceState.getDouble("firstValue");
        secondValue = savedInstanceState.getDouble("secondValue");
        result = savedInstanceState.getDouble("result");
        isErrorState = savedInstanceState.getBoolean("isErrorState");
        isRepeatableOperation = savedInstanceState.getBoolean("isRepeatableOperation");

        historyList = savedInstanceState.getStringArrayList("historyList");
        if(historyList != null) {
            historyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
            ListView listView = findViewById(R.id.history);
            listView.setAdapter(historyAdapter);
        }

        textInput.setText(savedInstanceState.getString("textInput"));
        operator = Operators.valueOf(savedInstanceState.getString("operator"));
    }

    // NOTE: log result to the history list
    private void logHistory() {
        historyList.add(Double.toString(result));
        historyAdapter.notifyDataSetChanged();
    }

    // NOTE: Convert TextView string value to double
    private double getInputValue() {
        return Double.parseDouble((textInput.getText().toString()).replace(",", "."));
    }

    // NOTE: Handler for number buttons
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

    // NOTE: Handler for operations
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

    // NOTE: Handler for operations which is not needed in second value
    public void instantOperationHandler() {
        if(isErrorState) {
            return;
        }

        if (textInput.length() > 0) {
            firstValue = getInputValue();
            secondValue = 0;

            switch (operator) {
                case THIRD_GRADE:
                    result = Math.pow(firstValue, 3);
                    break;
                case SECOND_GRADE:
                    result = Math.pow(firstValue, 2);
                    break;
                case THIRD_ROOT:
                    result = Math.pow(firstValue, 1 / 3);
                    break;
                case SECOND_ROOT:
                    result = Math.pow(firstValue, 1 / 2);
                    break;
                case SIN:
                    result = Math.sin(firstValue);
                    break;
                case COS:
                    result = Math.cos(firstValue);
                    break;
                case TG:
                    result = Math.tan(firstValue);
                    break;
                case CTG:
                    result = 1.0 / Math.tan(firstValue);
                    break;
            }
            isRepeatableOperation = true;
            textInput.setText(Double.toString(result));
        }
    }

    // Note: Equal button handler
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

            logHistory();
            textInput.setText(Double.toString(result));
        }
    }

    // Note: Reset calc state
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

    // Note: Set buttons handlers
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

        TextView secondGradeButton = findViewById(R.id.button_second_grade);
        if(secondGradeButton != null) {
            secondGradeButton.setText(Html.fromHtml(getString(R.string.button_second_grade)));
            secondGradeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.SECOND_GRADE;
                    instantOperationHandler();
                }
            });
        }

        TextView thirdGradeButton = findViewById(R.id.button_third_grade);
        if(thirdGradeButton != null) {
            thirdGradeButton.setText(Html.fromHtml(getString(R.string.button_third_grade)));
            thirdGradeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.THIRD_GRADE;
                    instantOperationHandler();
                }
            });
        }

        if(findViewById(R.id.button_second_root) != null) {
            findViewById(R.id.button_second_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.SECOND_ROOT;
                    instantOperationHandler();
                }
            });
        }

        TextView thirdRootButton = findViewById(R.id.button_third_root);
        if(thirdRootButton != null) {
            thirdRootButton.setText(Html.fromHtml(getString(R.string.button_third_root)));
            thirdRootButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.THIRD_ROOT;
                    instantOperationHandler();
                }
            });
        }

        if(findViewById(R.id.button_sin) != null) {
            findViewById(R.id.button_sin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.SIN;
                    instantOperationHandler();
                }
            });
        }

        if(findViewById(R.id.button_cos) != null) {
            findViewById(R.id.button_cos).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.COS;
                    instantOperationHandler();
                }
            });
        }

        if(findViewById(R.id.button_tg) != null) {
            findViewById(R.id.button_tg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.TG;
                    instantOperationHandler();
                }
            });
        }

        if(findViewById(R.id.button_ctg) != null) {
            findViewById(R.id.button_ctg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator = Operators.CTG;
                    instantOperationHandler();
                }
            });
        }
    }
}