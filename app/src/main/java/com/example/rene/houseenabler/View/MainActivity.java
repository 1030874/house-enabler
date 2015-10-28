package com.example.rene.houseenabler.View;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.Model.ChildItem;
import com.example.rene.houseenabler.Model.ParrentItem;
import com.example.rene.houseenabler.R;

import java.util.HashMap;
import java.util.List;



public class MainActivity extends Activity {

    EditText txtUser;
    EditText txtPassword;

    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Intilialze the components

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize EditText Control on then the app starts
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);


        // Makes connection to the database SQLite open






    }





    public void logOn(View view)
    {

       Intent i = new Intent(this, Items.class);

        startActivity(i);


    }

    public void subMit(View view)
    {

        Intent i = new Intent(this, CreateUser.class);

        startActivity(i);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
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
