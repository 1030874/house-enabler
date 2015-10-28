package com.example.rene.houseenabler.View;

import android.app.Activity;
import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.R;



public class Items extends Activity {

    Connection conn;
    SQLiteDatabase db;
    ExpandableListAdapter listAdapter;
    ExpandableListView elv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        elv = (ExpandableListView)findViewById(R.id.lvExp);


// open the connection
        conn.open();

        // Makes connection to the database SQLite open


        fillData();



    }

    private void fillData()
    {






        Cursor mGroupsCursor;

        mGroupsCursor = conn.fetchParrent();

        startManagingCursor(mGroupsCursor);

        mGroupsCursor.moveToFirst();

        ExpandableListView elv = (ExpandableListView) findViewById(R.id.lvExp);

        MyExpandableListAdapter madapter;

        madapter = new MyExpandableListAdapter(mGroupsCursor, Items.this,
                R.layout.activity_parrent,                     // Your row layout for a group
                R.layout.activity_child,                 // Your row layout for a child
                new String[] { "_idchild" },                      // Field(s) to use from group cursor
                new int[] { R.id.lblListHeader },                 // Widget ids to put group data into
                new String[] { "childname" },          // Field(s) to use from child cursors
                new int[] { R.id.lblListItem });          // Widget ids to put child data into

        elv.setAdapter(madapter);                         // set the list adapter.


        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Your child click code here
                return true;
            }
        });


    }



    public class MyExpandableListAdapter extends SimpleCursorTreeAdapter
    {
        public MyExpandableListAdapter(Cursor cursor, Context context, int groupLayout, int childLayout, String[] groupFrom, int[] groupTo, String[] childrenFrom, int[] childrenTo)
        {
            super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childrenFrom, childrenTo);
        }

        @Override
        protected Cursor getChildrenCursor(Cursor groupCursor)
        {
            Cursor childCursor = conn.fetchChildren(groupCursor.getString(groupCursor.getColumnIndex("category")));
            startManagingCursor(childCursor);
            childCursor.moveToFirst();
            return childCursor;
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
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
