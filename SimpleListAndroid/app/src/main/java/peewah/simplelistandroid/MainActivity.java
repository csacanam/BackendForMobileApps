package peewah.simplelistandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import java.util.ArrayList;
import java.util.List;

import model.ListItem;
import utilities.JSONParserListItem;
import utilities.ListItemAdapter;

public class MainActivity extends AppCompatActivity
{

    private Button btnParse;

    private Button btnSpark;

    private ListView listView;

    private ListItemAdapter listAdapter;

    private ArrayList<ListItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
        eventsDefinition();
    }

    /**
     * Initialize the UI components
     */
    private void initializeUI()
    {
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Without an action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        //Buttons
        btnParse = (Button) findViewById(R.id.btnParse);

        btnSpark = (Button) findViewById(R.id.btnSpark);

        //ListView and ListViewAdapter
        listView = (ListView) findViewById(R.id.listView);

        listAdapter = new ListItemAdapter(this, R.layout.list_item, list);

        listView.setAdapter(listAdapter);
    }

    /**
     * Definition of events
     */
    private void eventsDefinition()
    {
        //Btn Parse
        btnParse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Clear list
                list.clear();

                //Query items in Parse
                ParseQuery<ParseObject> query = ParseQuery.getQuery("List");
                query.findInBackground(new FindCallback<ParseObject>()
                {
                    public void done(List<ParseObject> scoreList, ParseException e)
                    {
                        if (e == null)
                        {
                            for (ParseObject object : scoreList)
                            {
                                //Create ListItem
                                String content = object.getString("content");
                                ListItem item = new ListItem(content);

                                //Add ListItem to Parse
                                list.add(item);
                            }

                            listAdapter.notifyDataSetChanged();

                        } else
                        {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
            }
        });

        //Btn Spark
        btnSpark.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                new HttpRequestTask().execute();

            }
        });

    }


    /**
     * HTTP Request Handler
     */
    private class HttpRequestTask extends AsyncTask<Void, Void, ArrayList<ListItem>>
    {
        @Override
        protected ArrayList<ListItem> doInBackground(Void... params)
        {
            try
            {
                final String url = "URL_WEB_SERVICE";//Example:http://172.30.162.239:4567/list
                JSONParserListItem jParser = new JSONParserListItem();
                ArrayList<ListItem> listItems = jParser.getData(url);
                return listItems;
            } catch (Exception e)
            {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<ListItem> info)
        {
            list.clear();

            for (ListItem listItem : info)
            {
                list.add(listItem);
            }

            listAdapter.notifyDataSetChanged();
        }

    }
}
