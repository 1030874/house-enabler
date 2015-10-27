package com.example.rene.houseenabler.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.rene.houseenabler.Model.User;


/**
 * Created by Rene on 14-10-2015.
 */
public class Connection extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "house_enabler.db";
    // table user
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // table parent
    public static final String TABLE_ITEMS_PARRENT = "parrent";
    public static final String COLUMN_ITEM_PARRENT_ID = "_idparrent";
    public static final String COLUMN_ITEM_PARRENT_NAME = "parrentname";

    // table child
    public static final String TABLE_ITEM_CHILD = "child";
    public static final String COLUMN_ITEM_CHILD_ID = "_idchild";
    public static final String COLUMN_ITEM_CHILD_CATEGORY = "category";
    public static final String COLUMN_ITEM_CHILD_NAME = "childname";
    public static final String COLUMN_ITEM_CHILD_DESCRIPTION = "description";

    private SQLiteDatabase db;
    private  Connection conn;


    public Connection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        // create table user.

        String query_user = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT " + ");";

        String query_parrent = "CREATE TABLE " + TABLE_ITEMS_PARRENT + "(" + COLUMN_ITEM_PARRENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_PARRENT_NAME + " TEXT " + ");";

        String query_child = "CREATE TABLE " + TABLE_ITEM_CHILD + "(" + COLUMN_ITEM_CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_CHILD_CATEGORY + " INTEGER, " + COLUMN_ITEM_CHILD_NAME + " TEXT, " + COLUMN_ITEM_CHILD_DESCRIPTION + " TEXT " + ");";

        db.execSQL(query_user);
        db.execSQL(query_parrent);
        db.execSQL(query_child);


        // insert to tables

        String A = "INSERT INTO parrent (_idparrent, parrentname) VALUES (1, 'A')";
        String B = "INSERT INTO parrent (_idparrent, parrentname) VALUES (2, 'B')";
        String C = "INSERT INTO parrent (_idparrent, parrentname) VALUES (3, 'C')";
        SQLiteStatement statement1 = db.compileStatement(A);
        SQLiteStatement statement2 = db.compileStatement(B);
        SQLiteStatement statement3 = db.compileStatement(C);
        long rowId1 = statement1.executeInsert();
        long rowId2 = statement2.executeInsert();
        long rowId3 = statement3.executeInsert();


        int category_a = 1;
        String childname_a = "A";

        int category_b = 2;
        String childname_b = "B";

        int category_c = 3;
        String childname_c = "C";

        String description = "Indsæt beskrivelse her";


        try

        {

            db.beginTransaction();
            String sql = "INSERT INTO child (_idchild, category, childname, description) VALUES (?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);

            int increase_a = 1;

            for (int i = 1; i < 162; i++)
            {
                statement.clearBindings();
                statement.bindLong(1, i);
                statement.bindLong(2, category_a); //+ i
                statement.bindString(3, childname_a + increase_a++);
                statement.bindString(4, description);
                statement.executeInsert();

            }


            int increase_b = 1;

            for (int j = 162; j < 323; j++)
            {
                statement.clearBindings();
                statement.bindLong(1, j);
                statement.bindLong(2, category_b);
                statement.bindString(3, childname_b + increase_b++);
                statement.bindString(4, description);
                statement.executeInsert();

            }

            int increase_c = 1;

            for (int k = 323; k < 484 ; k++)
            {
                statement.clearBindings();
                statement.bindLong(1, k);
                statement.bindLong(2, category_c);
                statement.bindString(3, childname_c + increase_c++);
                statement.bindString(4, description);
                statement.executeInsert();

            }

            db.setTransactionSuccessful(); // This commits the transaction if there were no exceptions

        }
        catch (Exception e)
        {
            Log.w("Exception:", e);
        } finally {
            db.endTransaction();
        }






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

    public Cursor fetchParrent()
    {
        String query = "SELECT * FROM parrent";
        return db.rawQuery(query, null);
    }

    public Cursor fetchChildren(String child) {
        String query = "SELECT * FROM child WHERE category = '" + child + "'";
        return db.rawQuery(query, null);
    }


}
