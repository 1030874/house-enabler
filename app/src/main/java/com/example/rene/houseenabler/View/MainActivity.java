package com.example.rene.houseenabler.View;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rene.houseenabler.Model.User;
import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.R;


public class MainActivity extends Activity {

    EditText txtUser;
    EditText txtPassword;

    Connection conn;
    Button btnCreate;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Intilialze the components

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize EditText Control on then the app starts
        txtUser = (EditText) findViewById(R.id.txtAddUser);
        txtPassword = (EditText) findViewById(R.id.txtAddPassword);
         btnCreate = (Button) findViewById(R.id.btnCreate);


        // Makes connection to the database SQLite open
        openDB();








    }

    private void openDB()
    {
        conn = new Connection(this);
        conn.open();
    }





    public void logOn_onClick(View view)
    {
        /*


        txtUser.getText().toString();
        txtPassword.getText().toString();


        if(conn.check_User_Pin_Number(username, password))
        {
            Toast.makeText(MainActivity.this, "Rigtigt!", Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(MainActivity.this,"Forkert", Toast.LENGTH_LONG).show();
        }


   */



       Intent i = new Intent(this, Items.class);

       startActivity(i);


    }



    public void subMit_onClick(View view)
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
