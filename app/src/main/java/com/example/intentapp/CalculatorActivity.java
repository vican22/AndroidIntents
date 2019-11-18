package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    public static final String PARAM_A = "a";
    public static final String PARAM_B = "b";
    public static final String PARAM_OP = "op";
    public static final String PARAM_RESULT = "result";

    public static final String INTENT_NAME = "com.example.intentapp.Calculator.action";

    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();

        if (intent.hasExtra(PARAM_A) && intent.hasExtra(PARAM_B) && intent.hasExtra(PARAM_OP)) {
            int a = intent.getIntExtra(PARAM_A, -1);
            int b = intent.getIntExtra(PARAM_B, -1);
            String op = intent.getStringExtra(PARAM_OP);

            switch (op) {
                case "+":
                    result = a + b;
                    break;

                case "-":
                    result = a - b;
                    break;

                case "*":
                    result = a * b;
                    break;

                case "/":
                    result = b == 0 ? 0 : a / b;
                    break;

                case "%":
                    result = b == 0 ? 0 : a % b;
                    break;
            }

            Toast.makeText(this, "The result is set. Please use back button", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void finish(){
        returnResult();

        super.finish();
    }

    private void returnResult() {
        Intent data = new Intent();

        data.putExtra(CalculatorActivity.PARAM_RESULT, result);
        setResult(RESULT_OK, data);
    }
}
