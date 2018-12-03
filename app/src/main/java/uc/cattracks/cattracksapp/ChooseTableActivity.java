package uc.cattracks.cattracksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//Displaying all clickable/viewable tables. Jumps to SelectStopActivity with particular table data.
public class ChooseTableActivity extends AppCompatActivity implements View.OnClickListener
{
    String busChoice; //Variable that is placed into busSelected

 @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_table);

        //Init button for Heritage
        Button heritageButton = findViewById(R.id.heritageBtn);
        heritageButton.setOnClickListener(this);

        //Init button for Heritage Weekend
        Button heritageWeekendButton = findViewById(R.id.heritageWBtn);
        heritageWeekendButton.setOnClickListener(this);

        //Init button for FastCat
        Button fastCatButton = findViewById(R.id.fastCatBtn);
        fastCatButton.setOnClickListener(this);

        //Init button for C1
        Button c1Button = findViewById(R.id.c1Btn);
        c1Button.setOnClickListener(this);

        //Init button for C2
        Button c2Button = findViewById(R.id.c2Btn);
        c2Button.setOnClickListener(this);

        //Init button for E1
        Button e1button = findViewById(R.id.e1Btn);
        e1button.setOnClickListener(this);

        //Init button for E2
        Button e2Button = findViewById(R.id.e2Btn);
        e2Button.setOnClickListener(this);

        //Init button for G
        Button gButton = findViewById(R.id.gBtn);
        gButton.setOnClickListener(this);
    }

    //When buttons are clicked, jump to the OpenTableActivity method with String name
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.heritageBtn:
                busChoice = "H";
                OpenTableActivity();
                break;

            case R.id.heritageWBtn:
                busChoice = "HW";
                OpenTableActivity();
                break;

            case R.id.fastCatBtn:
                busChoice = "FC";
                OpenTableActivity();
                break;

            case R.id.c1Btn:
                busChoice = "C1";
                OpenTableActivity();
                break;

            case R.id.c2Btn:
                busChoice = "C2";
                OpenTableActivity();
                break;

            case R.id.e1Btn:
                busChoice = "E1";
                OpenTableActivity();
                break;

            case R.id.e2Btn:
                busChoice = "E2";
                OpenTableActivity();
                break;

            case R.id.gBtn:
                busChoice = "G";
                OpenTableActivity();
                break;
        }
    }

    //This shift the user to the actual page with the scrollable table
    public void OpenTableActivity()
    {
        //Stating which activity to jump to, StopSelectActivity
        Intent intent = new Intent(this, StopSelectActivity.class);
        intent.putExtra("busChoice",busChoice);
        startActivity(intent);
    }
}