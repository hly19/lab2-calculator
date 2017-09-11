package com.example.a1438756.lab2_loancalc;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

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

    TextView monthlyPaymentTV;
    TextView totalPaymentTV;
    TextView totalInterestTV;

    boolean valid;
    String loan;
    String term;
    String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loanAmount = (EditText) findViewById(R.id.loanAmountET);
        loanTerm = (EditText) findViewById(R.id.termLoanET);
        interestRate = (EditText) findViewById(R.id.interestRateET);

        monthlyPaymentTV = (TextView) findViewById(R.id.mPaymentResultTV);
        totalPaymentTV = (TextView) findViewById(R.id.totalPaymentResultTV);
        totalInterestTV = (TextView) findViewById(R.id.totalInterestResultTV);
    }

    public void calculateAll(View v){
        loan = loanAmount.getText().toString();
        term = loanTerm.getText().toString();
        rate = interestRate.getText().toString();

        valid = validateInput(loan, term, rate, v);
        if(valid){
            calMonthlyPayment(v);
            calTotalPayment(v);
            calTotalInterest(v);
        }
    }

    public void calMonthlyPayment(View v){
        monthlyInterest = Double.parseDouble(interestRate.getText().toString()) / 1200;
        numberOfPayments = Double.parseDouble(loanTerm.getText().toString()) * 12;
        totalLoan = Double.parseDouble(loanAmount.getText().toString());
        monthlyPayment = (totalLoan * monthlyInterest) / (1 - ( 1 / Math.pow((1 + monthlyInterest), numberOfPayments)));

        monthlyPayment = Math.round(monthlyPayment * 100) / 100.0;

        monthlyPaymentTV.setText(Double.toString(monthlyPayment));
    }

    public void calTotalPayment(View v){
        numberOfPayments = Double.parseDouble(loanTerm.getText().toString()) * 12;

        totalPayment = monthlyPayment * numberOfPayments;

        totalPaymentTV.setText(Double.toString(totalPayment));
    }

    public void calTotalInterest(View v){
        totalInterest = totalPayment - totalLoan;

        totalInterestTV.setText(Double.toString(totalInterest));
    }

    public void clearAll(View v){
        loanAmount.setText("");
        loanTerm.setText("");
        interestRate.setText("");

        monthlyPaymentTV.setText("");
        totalPaymentTV.setText("");
        totalInterestTV.setText("");
    }

    public boolean validateInput(String loan, String term, String rate, View v){
        if(loan.matches("[0-9]+") && term.matches("[0-9]+") && rate.matches("[0-9]+")) {
            if (Double.parseDouble(loan) < 1 || Double.parseDouble(term) < 1 || Double.parseDouble(rate) < 1) {
                clearAll(v);
                loanAmount.setHint("Value cannot be 0.");
                loanTerm.setHint("Value cannot be 0.");
                interestRate.setHint("Value cannot be 0.");
                return false;
            } else {
                return true;
            }
        }
        else {
            clearAll(v);
            loanAmount.setHint("Numbers only.");
            loanTerm.setHint("Numbers only.");
            interestRate.setHint("Numbers only.");
            return false;
        }
    }
}
