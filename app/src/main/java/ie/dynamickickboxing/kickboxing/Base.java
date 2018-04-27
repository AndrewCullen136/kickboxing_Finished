package ie.dynamickickboxing.kickboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;

import ie.dynamickickboxing.main.KickboxingApp;
import models.Paid;


/**
 * Created by andrewcullen on 16/03/2018.
 */

public class Base extends AppCompatActivity {

    public KickboxingApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (KickboxingApp) getApplication();

        try {
            app.dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        app.dbManager.setTotalDonated(this);
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

        MenuItem reset = menu.findItem(R.id.menuReset);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem pay = menu.findItem(R.id.menuPaid);


        if(app.dbManager.getAll().isEmpty())
        {
            report.setEnabled(false);
            reset.setEnabled(false);
        }
        else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof PayScreen){
            pay.setVisible(false);
            if(!app.dbManager.getAll().isEmpty())
            {
                report.setVisible(true);
                reset.setEnabled(true);
            }
        }
        else {
            report.setVisible(false);
            pay.setVisible(true);
            reset.setVisible(false);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.dbManager.close();
    }

    public void homescreen(MenuItem item)
    {
        startActivity (new Intent(this, HomeScreen.class));
    }

    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }

    public void pay(MenuItem item) { startActivity (new Intent(this, PayScreen.class));}

    public void reset(MenuItem item) {}
}





