package models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by andrewcullen on 27/04/2018.
 */

public class PaidSerializer
{
    private Context mContext;
    private String mFilename;
    private int totalPaid;

    private Type objType;


    public PaidSerializer(Context c, String f)
    {
        mContext = c;
        mFilename = f;
        objType = new TypeToken<List<Paid>>() {
        }.getType();
    }


    public void savePayments(List<Paid> transactions) throws JSONException, IOException
    {
        String result = new Gson().toJson(transactions, objType);
        Writer writer = null;
        try
        {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(result);
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<Paid> loadPayments() throws IOException, JSONException
    {
        ArrayList<Paid> transactions = new ArrayList<>();
        BufferedReader reader = null;
        totalPaid = 0;

        try
        {
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            transactions = new Gson().fromJson(reader.readLine(), objType);

            for(Paid d : transactions)
                totalPaid += d.amount;
        }
        catch (FileNotFoundException e)
        {
            // we will ignore this one, since it happens when we start fresh
        }
        finally
        {
            if (reader != null)
                reader.close();
        }
        return transactions;
    }

    public int getTotalPaid()
    {
        return totalPaid;
    }
}