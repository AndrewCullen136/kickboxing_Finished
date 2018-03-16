package ie.dynamickickboxing.kickboxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.Paid;

/**
 * Created by andrewcullen on 16/03/2018.
 */

public class Base extends AppCompatActivity
{
    public final  int            target       = 15;
    public        int            totalPaid = 0;
    public static List<Paid> payments    = new ArrayList<Paid>();

    public boolean newPayment(Paid payment)
    {
        boolean targetAchieved = totalPaid > target;
        if (!targetAchieved)
        {
            payments.add(payment);
            totalPaid += payment.amount;
        }
        else {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
        return targetAchieved;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem payment = menu.findItem(R.id.menuPaid);

        if(payments.isEmpty())
            report.setEnabled(false);
        else
            report.setEnabled(true);

        if(this instanceof Base)
        {
            payment.setVisible(false);
            if(!payments.isEmpty()) {
                report.setVisible(true);
            }
        }
        else {
            report.setVisible(false);
            payment.setVisible(true);
        }

        return true;
    }

    public void settings(MenuItem item)
    {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
    }

    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }

    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, PayScreen.class));
    }
}