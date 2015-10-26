package com.example.rene.houseenabler.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.Model.User;
import com.example.rene.houseenabler.R;


public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPassword;

    Connection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Sets layout
        setContentView(R.layout.activity_main);




        // Initialize EditText Control on then the app starts
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        // Makes connection to the database SQLite
        conn = new Connection(this, null, null, 1);
    }




    public void onClick_Logon(View view)
    {
        User user = new User(txtUser.getText().toString() , txtPassword.getText().toString());

        conn.addUser(user);

        //user = txtUser.getText();


    }

    public void onClick_Submit(View view)
    {
        // we intent to go to CreateUser class
        Intent i = new Intent(this, CreateUser.class);

        startActivity(i);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
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
