package com.example.rene.houseenabler.View;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.Model.ChildItem;
import com.example.rene.houseenabler.Model.ListData;
import com.example.rene.houseenabler.Model.ParrentItem;
import com.example.rene.houseenabler.R;

import java.util.ArrayList;



public class Items extends Activity {

    Connection conn;
    SQLiteDatabase db;
    ExpandableListAdapter listAdapter;
    ExpandableListView elv;
    ArrayList<ListData> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);


        // open the connection
        openDB();

        //load the data from the database.
        mArrayList = conn.getAllListData();

        //Initiate the list view
        elv = (ExpandableListView) findViewById(R.id.lvExp);

       listAdapter = new ExpandableListAdapter(this, mArrayList);

        // setting list adapter
        elv.setAdapter(listAdapter);




    }

    private void openDB()
    {
        conn = new Connection(this);
        conn.open();
    }


    //Adapter class
    class ExpandableListAdapter extends BaseExpandableListAdapter
    {

        private Context _context;
        private ArrayList<ListData> _data;

        public ExpandableListAdapter(Context context, ArrayList<ListData> arrayData) {
            this._data = arrayData;
            this._context = context;
        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosititon) {
            return this._data.get(groupPosition).childItems.get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final ChildItem child = getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.activity_child, null);
            }

            final TextView childDescription = (TextView) convertView.findViewById(R.id.row_child_description);

            //set child name
            final TextView childName = (TextView) convertView.findViewById(R.id.row_child_name);
            childName.setText(child.get_childname());
            childName.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Toggle description text view when user clicks
                    if (childDescription.getVisibility() == View.VISIBLE) {
                        childDescription.setVisibility(View.GONE);  //hide text view if it's visible
                    } else {
                        childDescription.setVisibility(View.VISIBLE); //show text view if it's invisible
                    }
                }
            });

            //Set child description
            childDescription.setText(child.get_description());
            childDescription.setVisibility(View.GONE);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return this._data.get(groupPosition).childItems.size();
        }

        @Override
        public ParrentItem getGroup(int groupPosition)
        {
            return this._data.get(groupPosition).parrentItem;
        }

        @Override
        public int getGroupCount()
        {
            return this._data.size();
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            ParrentItem headerTitle = getGroup(groupPosition);
            if (convertView == null)
            {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.activity_parrent, null);
            }

            final TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.get_Parrentname());
            return convertView;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }










    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
