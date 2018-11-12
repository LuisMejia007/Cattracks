package uc.cattracks.cattracksapp;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity implements HorizontalScroll.ScrollViewListener, VerticalScroll.ScrollViewListener
{
    private static int SCREEN_HEIGHT;
    private static int SCREEN_WIDTH;

    RelativeLayout relativeLayoutTable;
    RelativeLayout relativeLayoutA;
    RelativeLayout relativeLayoutB;
    RelativeLayout relativeLayoutC;
    RelativeLayout relativeLayoutD;

    TableLayout tableLayoutA;
    TableLayout tableLayoutB;
    TableLayout tableLayoutC;
    TableLayout tableLayoutD;

    TableRow tableRow;
    TableRow tableRowB;

    HorizontalScroll horizontalScrollViewB;
    HorizontalScroll horizontalScrollViewD;

    VerticalScroll scrollViewC;
    VerticalScroll scrollViewD;

    //Counting how many columns are added in the row.
    int tableColumnCountB = 0;

    //Counting how many rows are added.
    int tableRowCountC = 0;

    //Strings set to get info from database
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
        setContentView(R.layout.activity_table);

        //Mandatory Content that makes it all work how it should, don't erase please
        relativeLayoutTable = (RelativeLayout)findViewById(R.id.relativeLayoutTable);
        getScreenDimension();
        initializeRelativeLayout();
        initializeScrollers();
        initializeTableLayout();
        horizontalScrollViewB.setScrollViewListener(this);
        horizontalScrollViewD.setScrollViewListener(this);
        scrollViewC.setScrollViewListener(this);
        scrollViewD.setScrollViewListener(this);
        addRowToTableA();
        initializeRowForTableB();
        //Mandatory content ends here

        //Top Right Table (B)
        for(int i = 0; i < countRow; i++)
        {
            //Creates what the header should be called
            addColumnsToTableB(" ", i);
        }

        //Bottom Right Table (D)
        for(int i = 0; i < countRow; i++)
        {
            initializeRowForTableD(i);

            //Fills in stop names in Table C
            addRowToTableC(testRowText[i]);

            for(int j = 0; j < countCol; j++)
            {
                //Fills in departure times in Table D
                addColumnToTableAtD(i,testColText[j]);
            }
        }
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

    //Grabs everything and places into relative layout
    private void initializeRelativeLayout()
    {
        relativeLayoutA= new RelativeLayout(getApplicationContext());
        relativeLayoutA.setId(R.id.relativeLayoutA);
        relativeLayoutA.setPadding(0,0,0,0);

        relativeLayoutB= new RelativeLayout(getApplicationContext());
        relativeLayoutB.setId(R.id.relativeLayoutB);
        relativeLayoutB.setPadding(0,0,0,0);

        relativeLayoutC= new RelativeLayout(getApplicationContext());
        relativeLayoutC.setId(R.id.relativeLayoutC);
        relativeLayoutC.setPadding(0,0,0,0);

        relativeLayoutD= new RelativeLayout(getApplicationContext());
        relativeLayoutD.setId(R.id.relativeLayoutD);
        relativeLayoutD.setPadding(0,0,0,0);

        relativeLayoutA.setLayoutParams(new RelativeLayout.LayoutParams(SCREEN_WIDTH/5,SCREEN_HEIGHT/20));
        this.relativeLayoutTable.addView(relativeLayoutA);

        //top right
        RelativeLayout.LayoutParams layoutParamsRelativeLayoutB= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/20);
        layoutParamsRelativeLayoutB.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutA);
        relativeLayoutB.setLayoutParams(layoutParamsRelativeLayoutB);
        this.relativeLayoutTable.addView(relativeLayoutB);

        //bottom left
        RelativeLayout.LayoutParams layoutParamsRelativeLayoutC= new RelativeLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutC.addRule(RelativeLayout.BELOW, R.id.relativeLayoutA);
        relativeLayoutC.setLayoutParams(layoutParamsRelativeLayoutC);
        this.relativeLayoutTable.addView(relativeLayoutC);

        //bottom right
        RelativeLayout.LayoutParams layoutParamsRelativeLayoutD= new RelativeLayout.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.BELOW, R.id.relativeLayoutB);
        layoutParamsRelativeLayoutD.addRule(RelativeLayout.RIGHT_OF, R.id.relativeLayoutC);
        relativeLayoutD.setLayoutParams(layoutParamsRelativeLayoutD);
        this.relativeLayoutTable.addView(relativeLayoutD);
    }

    //Providing all tables with particular scrolling abilities
    private void initializeScrollers()
    {
        //Giving Table B the ability to scroll left & right (horizontally)
        horizontalScrollViewB= new HorizontalScroll(getApplicationContext());
        horizontalScrollViewB.setPadding(0,0,0,0);

        //Giving Table D the ability to scroll left & right (horizontally)
        horizontalScrollViewD= new HorizontalScroll(getApplicationContext());
        horizontalScrollViewD.setPadding(0,0,0,0);

        //Giving Table C the ability to scroll up & down (vertically)
        scrollViewC= new VerticalScroll(getApplicationContext());
        scrollViewC.setPadding(0,0,0,0);

        //Giving Table D the ability to scroll up & down (vertically)
        scrollViewD= new VerticalScroll(getApplicationContext());
        scrollViewD.setPadding(0,0,0,0);

        //
        horizontalScrollViewB.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT/20));
        scrollViewC.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH/5 ,SCREEN_HEIGHT - (SCREEN_HEIGHT/20)));
        scrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));
        horizontalScrollViewD.setLayoutParams(new ViewGroup.LayoutParams(SCREEN_WIDTH- (SCREEN_WIDTH/5), SCREEN_HEIGHT - (SCREEN_HEIGHT/20) ));

        //
        this.relativeLayoutB.addView(horizontalScrollViewB);
        this.relativeLayoutC.addView(scrollViewC);
        this.scrollViewD.addView(horizontalScrollViewD);
        this.relativeLayoutD.addView(scrollViewD);
    }

    private  void initializeTableLayout()
    {
        tableLayoutA= new TableLayout(getApplicationContext());
        tableLayoutA.setPadding(0,0,0,0);
        tableLayoutB= new TableLayout(getApplicationContext());
        tableLayoutB.setPadding(0,0,0,0);
        tableLayoutB.setId(R.id.tableLayoutB);
        tableLayoutC= new TableLayout(getApplicationContext());
        tableLayoutC.setPadding(0,0,0,0);
        tableLayoutD= new TableLayout(getApplicationContext());
        tableLayoutD.setPadding(0,0,0,0);

        TableLayout.LayoutParams layoutParamsTableLayoutA = new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);
        tableLayoutA.setLayoutParams(layoutParamsTableLayoutA);
        tableLayoutA.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        this.relativeLayoutA.addView(tableLayoutA);

        TableLayout.LayoutParams layoutParamsTableLayoutB = new TableLayout.LayoutParams(SCREEN_WIDTH -(SCREEN_WIDTH/5), SCREEN_HEIGHT/20);
        tableLayoutB.setLayoutParams(layoutParamsTableLayoutB);
        tableLayoutB.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        this.horizontalScrollViewB.addView(tableLayoutB);

        TableLayout.LayoutParams layoutParamsTableLayoutC = new TableLayout.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT - (SCREEN_HEIGHT/20));
        tableLayoutC.setLayoutParams(layoutParamsTableLayoutC);
        tableLayoutC.setBackgroundColor(getResources().getColor(R.color.darkBlue));
        this.scrollViewC.addView(tableLayoutC);

        TableLayout.LayoutParams layoutParamsTableLayoutD = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        tableLayoutD.setLayoutParams(layoutParamsTableLayoutD);
        this.horizontalScrollViewD.addView(tableLayoutD);

    }

    //Checking what scroll is being interacted with to make the horizontal changes
    @Override
    public void onScrollChanged(HorizontalScroll scrollView, int x, int y, int oldx, int oldy)
    {
        if(scrollView == horizontalScrollViewB)
        {
            horizontalScrollViewD.scrollTo(x,y);
        }
        else if(scrollView == horizontalScrollViewD)
        {
            horizontalScrollViewB.scrollTo(x, y);
        }
    }

    //Checking what scroll is being is bering interacted with to make the vertical changes
    @Override
    public void onScrollChanged(VerticalScroll scrollView, int x, int y, int oldx, int oldy)
    {
        if(scrollView == scrollViewC)
        {
            scrollViewD.scrollTo(x,y);
        }
        else if(scrollView == scrollViewD)
        {
            scrollViewC.scrollTo(x,y);
        }
    }

    //Top left table
    private void addRowToTableA()
    {
        tableRow = new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow = new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(" ");
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        label_date.setTextColor(getResources().getColor(R.color.white));
        tableRow.addView(label_date);
        this.tableLayoutA.addView(tableRow);
    }

    private void initializeRowForTableB()
    {
        tableRowB = new TableRow(getApplicationContext());
        tableRow.setPadding(0,0,0,0);
        this.tableLayoutB.addView(tableRowB);
    }

    //Creates Columns in Table B
    private synchronized void addColumnsToTableB(String text, final int id)
    {
        tableRow = new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20);
        tableRow.setPadding(3,3,3,4);
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        label_date.setTextColor(getResources().getColor(R.color.white));
        this.tableRow.addView(label_date);
        this.tableRow.setTag(id);
        this.tableRowB.addView(tableRow);
        tableColumnCountB++;
    }

    //Creates rows in Table C
    private synchronized void addRowToTableC(String text)
    {
        TableRow tableRow1= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow1= new TableRow.LayoutParams(SCREEN_WIDTH, SCREEN_HEIGHT/20); //sq/5 & sh/20
        tableRow1.setPadding(3,3,3,4);
        tableRow1.setLayoutParams(layoutParamsTableRow1);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        label_date.setTextColor(getResources().getColor(R.color.white));
        tableRow1.addView(label_date);

        TableRow tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/5, SCREEN_HEIGHT/20); //sw/5 & sh/20
        tableRow.setPadding(0,0,0,0);
        tableRow.setLayoutParams(layoutParamsTableRow);
        tableRow.addView(tableRow1);
        this.tableLayoutC.addView(tableRow, tableRowCountC);
        tableRowCountC++;
    }

    private synchronized void initializeRowForTableD(int pos)
    {
        TableRow tableRowB = new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, SCREEN_HEIGHT/20);
        tableRowB.setPadding(0,0,0,0);
        tableRowB.setLayoutParams(layoutParamsTableRow);
        this.tableLayoutD.addView(tableRowB, pos);
    }

    private synchronized void addColumnToTableAtD(final int rowPos, String text)
    {
        TableRow tableRowAdd= (TableRow) this.tableLayoutD.getChildAt(rowPos);
        tableRow= new TableRow(getApplicationContext());
        TableRow.LayoutParams layoutParamsTableRow= new TableRow.LayoutParams(SCREEN_WIDTH/15, SCREEN_HEIGHT/20); //SW/5
        tableRow.setPadding(3,3,3,4);
        tableRow.setBackgroundDrawable(getResources().getDrawable(R.drawable.cell_bacground));
        tableRow.setLayoutParams(layoutParamsTableRow);
        TextView label_date = new TextView(getApplicationContext());
        label_date.setText(text);
        label_date.setTextSize(getResources().getDimension(R.dimen.cell_text_size));
        tableRow.setTag(label_date);
        this.tableRow.addView(label_date);
        tableRowAdd.addView(tableRow);
        label_date.setGravity(Gravity.CENTER); //Aligns the text to the center
    }

    private void createCompleteColumn(String value)
    {
        int i = 0;
        int j = tableRowCountC-1;

        for(int k = i; k <= j; k++)
        {
            addColumnToTableAtD(k, value);
        }
    }

    private void createCompleteRow(String value)
    {
        initializeRowForTableD(0);
        int i = 0;
        int j = tableColumnCountB-1;
        int pos = tableRowCountC-1;

        for(int k = i; k <= j; k++)
        {
            addColumnToTableAtD(pos, value);
        }
    }
}