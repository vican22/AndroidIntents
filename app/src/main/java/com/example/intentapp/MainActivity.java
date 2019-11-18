package com.example.intentapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    @BindView(R.id.etA)
    EditText etA;

    @BindView(R.id.etB)
    EditText etB;

    @BindView(R.id.spOp)
    Spinner spOp;

    @BindView(R.id.btnGo)
    Button btnGo;

    @BindView(R.id.tvResult)
    TextView tvResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnTextChanged({R.id.etA, R.id.etB})
    void onTextChanged(CharSequence text) {
        btnGo.setEnabled(!TextUtils.isEmpty(etA.getText()) && !TextUtils.isEmpty(etB.getText()));
    }

    @OnClick(R.id.btnGo)
    void onClick() { sendIntent(); }

    private void sendIntent() {
       // Intent intent = new Intent(this, CalculatorActivity.class);

        Intent intent = new Intent(CalculatorActivity.INTENT_NAME);

        intent.putExtra(CalculatorActivity.PARAM_A, Integer.valueOf(etA.getText().toString()));
        intent.putExtra(CalculatorActivity.PARAM_B, Integer.valueOf(etB.getText().toString()));
        intent.putExtra(CalculatorActivity.PARAM_OP, spOp.getSelectedItem().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            int result = data.getIntExtra(CalculatorActivity.PARAM_RESULT, -1);
            tvResult.setText(String.valueOf(result));
        }
    }
}
