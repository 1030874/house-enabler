package com.example.rene.houseenabler.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import com.example.rene.houseenabler.Model.User;




/**
 * Created by Rene on 14-10-2015.
 */
public class Connection extends SQLiteOpenHelper
{
    private User user;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "house_enabler.db";
    public static final String COLUMN_ID = "_id";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "passwords";

    public Connection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT " +
                COLUMN_PASSWORD + " TEXT " +

                ");";
        db.execSQL(query);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);


    }

    public void addUser(User user)
    {

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME,  user.get_username());
        values.put(COLUMN_PASSWORD, user.get_password());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();

    }


}
