package ie.dynamickickboxing.kickboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;

import ie.dynamickickboxing.main.KickboxingApp;
import models.Paid;

public class PayScreen extends Base {



    private Button payButton;
    private RadioGroup paymentMethod;

    private KickboxingApp app;

    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private EditText amountText;
    private TextView amountTotal;

    private int             totalPaid = 0;
    private boolean         targetAchieved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        payButton = findViewById(R.id.btn_pay);


        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar   = findViewById(R.id.progressBar);
        amountPicker  = findViewById(R.id.amountPicker);
        amountText    = findViewById(R.id.paymentAmount);
        amountTotal   = findViewById(R.id.totalSoFar);

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(15);
        progressBar.setMax(15);
        amountTotal.setText("$0");
    }







    public void payButtonPressed (View view) throws JSONException {
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "PayPal" : "Direct";

        int paidAmount =  amountPicker.getValue();
        if (paidAmount == 0)
        {
            String text = amountText.getText().toString();
            if (!text.equals(""))
                paidAmount = Integer.parseInt(text);
        }

        if (paidAmount > 0)
        {
            app.newPayments(new Paid(paidAmount, method));
            progressBar.setProgress(paidAmount);
            String totalDonatedStr = "$" + paidAmount;
            amountTotal.setText(totalDonatedStr);
        }

        Log.v("Pay", amountPicker.getValue() + " Paid by " +  method + "\nCurrent total " + totalPaid);
    }

    @Override
    public void reset(MenuItem item)
    {
        app.dbManager.reset();
        app.totalPaid = 0;
        amountTotal.setText("$" + app.totalPaid);
    }



}
