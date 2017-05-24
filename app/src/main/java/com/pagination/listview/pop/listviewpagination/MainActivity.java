package com.pagination.listview.pop.listviewpagination;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }

    private ListView listview;
    private TextView title;
    private Button btn_prev;
    private Button btn_next;

    private ArrayList<String> data;
    ArrayAdapter<String> sd;

    private int pageCount ;

    /**
     * Using this increment value we can move the listview items
     */
    private int increment = 0;

    /**
     * Here set the values, how the ListView to be display
     *
     * Be sure that you must set like this
     *
     * TOTAL_LIST_ITEMS > NUM_ITEMS_PAGE
     */

    public int TOTAL_LIST_ITEMS = 1030;
    public int NUM_ITEMS_PAGE   = 100;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.list);
        btn_prev     = (Button)findViewById(R.id.prev);
        btn_next     = (Button)findViewById(R.id.next);
        title    = (TextView)findViewById(R.id.title);

        btn_prev.setEnabled(false);

        data = new ArrayList<String>();

        /**
         * this block is for checking the number of pages
         * ====================================================
         */

        int val = TOTAL_LIST_ITEMS%NUM_ITEMS_PAGE;
        val = val==0?0:1;
        pageCount = TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;
        /**
         * =====================================================
         */

        /**
         * The ArrayList data contains all the list items
         */
        for(int i=0;i<TOTAL_LIST_ITEMS;i++)
        {
            data.add("This is Item "+(i+1));
        }

        loadList(0);

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment++;
                loadList(increment);
                CheckEnable();
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                loadList(increment);
                CheckEnable();
            }
        });

    }

    /**
     * Method for enabling and disabling Buttons
     */
    private void CheckEnable()
    {
        if(increment+1 == pageCount)
        {
            btn_next.setEnabled(false);
        }
        else if(increment == 0)
        {
            btn_prev.setEnabled(false);
        }
        else
        {
            btn_prev.setEnabled(true);
            btn_next.setEnabled(true);
        }
    }

    /**
     * Method for loading data in listview
     * @param number
     */
    private void loadList(int number)
    {
        ArrayList<String> sort = new ArrayList<String>();
        title.setText("Page "+(number+1)+" of "+pageCount);

        int start = number * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<data.size())
            {
                sort.add(data.get(i));
            }
            else
            {
                break;
            }
        }
        sd = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        listview.setAdapter(sd);
    }
}
