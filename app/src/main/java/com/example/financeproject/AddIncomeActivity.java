package com.example.financeproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.financeproject.models.Expense;
import com.example.financeproject.models.Income;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddIncomeActivity extends AppCompatActivity {

    private EditText contentEditText;
    private EditText amountEditText;
    private EditText dateEditText;
    private Button btn;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        btn = findViewById(R.id.button);
        contentEditText = findViewById(R.id.income_content_edittext);
        amountEditText = findViewById(R.id.income_amount_edittext);
        dateEditText = findViewById(R.id.income_date_edittext);

        getSupportActionBar().setTitle(R.string.add_item_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final AppDatabase db = AppDatabase.getAppDatabase(AddIncomeActivity.this);

        updateDateText();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Income income = new Income();
                income.setAmount(Double.parseDouble(amountEditText.getText().toString()));
                income.setDate(myCalendar.getTime());
                income.setName(contentEditText.getText().toString());
                db.incomeDao().insert(income);

                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateText();
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddIncomeActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void updateDateText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateEditText.setText(dateFormat.format(myCalendar.getTime()));
    }

}
