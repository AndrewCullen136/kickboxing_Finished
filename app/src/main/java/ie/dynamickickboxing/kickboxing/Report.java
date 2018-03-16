package ie.dynamickickboxing.kickboxing;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import models.Paid;


public class Report extends Base
{
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.ls_report);
        PaidAdapter adapter = new PaidAdapter(this,  payments);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuPaid : startActivity (new Intent(this, PayScreen.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}


class PaidAdapter extends ArrayAdapter<Paid>
{
    private Context context;
    public List<Paid> payments;

    public PaidAdapter(Context context, List<Paid> payments)
    {
        super(context, R.layout.row_pay, payments);
        this.context   = context;
        this.payments = payments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View     view       = inflater.inflate(R.layout.row_pay, parent, false);
        Paid paid   = payments.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_amount);
        TextView methodView = (TextView) view.findViewById(R.id.row_method);

        amountView.setText("$" + paid.amount);
        methodView.setText(paid.method);

        return view;
    }

    @Override
    public int getCount()
    {
        return payments.size();
    }
}