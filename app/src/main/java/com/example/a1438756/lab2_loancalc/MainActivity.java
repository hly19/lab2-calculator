package com.example.a1438756.lab2_loancalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText loanAmount;
    EditText loanTerm;
    EditText interestRate;
    double monthlyPayment;
    double totalPayment;
    double totalInterest;

    double monthlyInterest;
    double numberOfPayments;
    double totalLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loanAmount = (EditText) findViewById(R.id.loanAmountET);
        loanTerm = (EditText) findViewById(R.id.termLoanET);
        interestRate = (EditText) findViewById(R.id.interestRateET);
    }

    public void calMonthlyPayment(View v){
        monthlyInterest = Double.parseDouble(interestRate.getText().toString()) / 1200;
        numberOfPayments = Double.parseDouble(loanTerm.getText().toString()) * 12;
        totalLoan = Double.parseDouble(loanAmount.getText().toString());
        monthlyPayment = (totalLoan * monthlyInterest) / (1 - ( 1 / Math.pow((1 + monthlyInterest), numberOfPayments)));

        monthlyPayment = Math.round(monthlyPayment * 100) / 100.0;
    }

    public void calTotalPayment(View v){
        numberOfPayments = Double.parseDouble(loanTerm.getText().toString()) * 12;

        totalPayment = monthlyPayment * numberOfPayments;
    }

    public void calTotalInterest(View v){
        totalInterest = totalPayment - totalLoan;
    }
}
