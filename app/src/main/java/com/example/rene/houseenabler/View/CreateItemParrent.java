package com.example.rene.houseenabler.View;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rene.houseenabler.Model.ParrentItem;
import com.example.rene.houseenabler.Database.Connection;
import com.example.rene.houseenabler.R;

public class CreateItemParrent extends Activity
{
    Connection conn;
    EditText txtAddParrent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item_parrent);
        txtAddParrent = (EditText) findViewById(R.id.txtAddUser);

    }


    public void btnAddItemParrent_onClick(View view)
    {

        ParrentItem parrentItem = new ParrentItem(txtAddParrent.getText().toString());

        conn = new Connection(this);

        conn.addParrent(parrentItem);




    }

}
