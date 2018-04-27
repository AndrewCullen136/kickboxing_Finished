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
import android.widget.Toast;

public class HomeScreen extends Base {

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




}
