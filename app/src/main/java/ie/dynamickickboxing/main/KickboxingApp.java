package ie.dynamickickboxing.main;

/**
 * Created by andrewcullen on 27/04/2018.
 */

import android.app.Application;
import android.util.Log;
import android.widget.Toast;


import java.sql.SQLException;

import ie.dynamickickboxing.kickboxing.database.DBManager;
import models.Paid;




public class KickboxingApp extends Application
{

    public final int       target       = 10000;
    public int             totalPaid = 0;
    public DBManager       dbManager;



    public boolean newPayments(Paid transactions)
    {
        boolean targetAchieved = totalPaid > target;
        if (!targetAchieved)
        {
            dbManager.add(transactions);
            totalPaid += transactions.amount;
        }
        else
        {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
        return targetAchieved;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
        dbManager = new DBManager(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.v("Donate", "Realm Database Created & Opened");
    }
}

