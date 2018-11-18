package uc.cattracks.cattracksapp;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

//This activity displays the stop times in landscape mode
public class NeatTableActivity extends AppCompatActivity
{
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;

    RelativeLayout relativeLayoutTable;

    TableLayout tableLayout;

    TableRow tableRow;

    String[] testRowText = {"Moraga Subdivision", "Yosemite Church", "Uni. Surgery Center",
            "Promenade Center", "Mercy Hospital", "Cardella & M Street", "Bellevue Ranch on Arrow Wood Dr"
            , "SAAC", "Emigrant Pass at Scholars Lane", "Bellevue Ranch on Arrow Wood Dr",
            "Mercy College", "Yosemite & Cordova ", "University Surgery Center", "Yosemite Church"
            , "Moraga Subdivision", "SAAC", "Emigrant Pass at Scholars Lane", "Morage Subdivision"};

    String[] testColText = {"5:40", "5:58", "6:16", "6:34", "6:52", "7:10", "7:48", "8:06", "8:24",
            "8:42", "9:00", "9:18", "9:36", "9:54", "10:12", "10:30", "10:48", "11:06", "11:24",
            "11:42", "12:20", "12:38", "12:56", "1:14", "1:32", "1:50", "2:08","2:26"};

    //Grabbing the number of rows & columns that will need to be populated
    int countRow = testRowText.length;
    int countCol = testColText.length;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neat_table);


    }


    //Makes sure everything looks great in any screen size
    private void getScreenDimension()
    {
        //getAppCon is an interface to global info about app environment
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        assert wm != null;
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        SCREEN_WIDTH= size.x;
        SCREEN_HEIGHT = size.y;
    }
}
