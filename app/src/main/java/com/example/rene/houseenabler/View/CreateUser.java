package com.example.rene.houseenabler.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.rene.houseenabler.Model.User;
import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.R;



public class CreateUser extends AppCompatActivity
{

    EditText txtUser;
    EditText txtPassword;

    Connection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Initialize EditText Control on then the app starts
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        // Makes connection to the database SQLite


        //conn = new Connection(this,null,null,1);




    }



    public void onCreate(View view)
    {
        User user = new User(txtUser.getText().toString(), txtPassword.getText().toString());
       // conn.addUser(user);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
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
