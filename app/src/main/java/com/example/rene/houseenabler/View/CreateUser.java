package com.example.rene.houseenabler.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rene.houseenabler.Model.User;
import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.R;



public class CreateUser extends AppCompatActivity
{

    EditText txtAddUser;
    EditText txtAddPassword;

    Connection conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // Initialize EditText Control on then the app starts
        txtAddUser = (EditText) findViewById(R.id.txtAddUser);
        txtAddPassword = (EditText) findViewById(R.id.txtAddPassword);








    }



    public void onCreateUser_onClick(View view)
    {
        if(!TextUtils.isEmpty(txtAddUser.getText().toString() + txtAddPassword.getText().toString()))
        {
            User user = new User(txtAddUser.getText().toString(), txtAddPassword.getText().toString());
            conn = new Connection(this);
            conn.addUser(user);

            Toast.makeText(CreateUser.this,"Bruger oprettet", Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(CreateUser.this,"Indtask brugernavn og adgangskode", Toast.LENGTH_LONG).show();
        }






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
