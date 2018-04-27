package ie.dynamickickboxing.kickboxing;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ie.dynamickickboxing.main.KickboxingApp;
import models.Paid;


public class Report extends Base implements AdapterView.OnItemClickListener
{
    ListView listView;
    private KickboxingApp app;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = findViewById(R.id.ls_report);
        PaidAdapter adapter = new PaidAdapter(this,app.dbManager.getAll(), app);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {

        Toast.makeText(this, "You Selected Row [ " + (pos + 1) + "]\n" +
                "For Donation Data [ " + app.dbManager.get(arg1.getTag().toString()) + "]\n " +
                "With ID of [" + id + "]", Toast.LENGTH_LONG).show();

    }
}



class PaidAdapter extends ArrayAdapter<Paid>
{
    private Context context;
    public List<Paid> transactions;

    public PaidAdapter(Context context, List<Paid> transactions, KickboxingApp app)
    {
        super(context, R.layout.row_pay, transactions);
        this.context   = context;
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View     view       = inflater.inflate(R.layout.row_pay, parent, false);
        Paid paid   = transactions.get(position);
        TextView amountView = view.findViewById(R.id.row_amount);
        TextView methodView =  view.findViewById(R.id.row_method);

        amountView.setText("$" + paid.amount);
        methodView.setText(paid.method);

        return view;
    }

    @Override
    public int getCount()
    {
        return transactions.size();
    }

}