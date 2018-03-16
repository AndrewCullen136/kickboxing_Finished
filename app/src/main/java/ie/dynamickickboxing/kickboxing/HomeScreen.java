package ie.dynamickickboxing.kickboxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class HomeScreen extends AppCompatActivity {

    public ImageButton  button_schedule;
    public ImageButton  button_pay;





    public  void openSchedule(){
        button_schedule = (ImageButton)findViewById(R.id.button_schedule);
        button_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VS = new Intent(HomeScreen.this , ViewSchedule.class);

                startActivity(VS);
            }
        });

    };

    public  void payPressed(){
        button_pay = (ImageButton)findViewById(R.id.button_pay);
        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pay = new Intent(HomeScreen.this , PayScreen.class);

                startActivity(pay);
            }
        });

    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
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
        openSchedule();
        payPressed();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
