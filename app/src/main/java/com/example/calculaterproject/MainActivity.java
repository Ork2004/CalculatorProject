package com.example.calculaterproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText input1, input2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnReset = findViewById(R.id.btnReset);

        btnAdd.setOnClickListener(view -> performOperation('+'));
        btnSubtract.setOnClickListener(view -> performOperation('-'));
        btnMultiply.setOnClickListener(view -> performOperation('*'));
        btnDivide.setOnClickListener(view -> performOperation('/'));

        btnReset.setOnClickListener(view -> resetFields());
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_standard_calculator) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performOperation(char operator) {
        String num1Str = input1.getText().toString().trim();
        String num2Str = input2.getText().toString().trim();

        if (TextUtils.isEmpty(num1Str)) {
            input1.setError("Введите число");
            return;
        }

        if (TextUtils.isEmpty(num2Str)) {
            input2.setError("Введите число");
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        Toast.makeText(this, "Деление на ноль невозможно", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            Toast.makeText(this, "Результат: " + result, Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ошибка в формате чисел", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        input1.setText("");
        input2.setText("");
        input1.setError(null);
        input2.setError(null);
    }
}