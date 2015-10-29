package com.example.rene.houseenabler.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.nfc.Tag;
import android.util.Log;
import com.example.rene.houseenabler.Model.User;

import java.sql.SQLException;


/**
 * Created by Rene on 14-10-2015.
 */
public class Connection
{
    private static final String TAG = "Connection"; //used for logging database version changes


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


    private static final String DATABASE_NAME = "house_enabler.db";

    private static final int DATABASE_VERSION = 1;


    private final Context context;
    private Connection conn;
    private DatabaseHelper myDBHelper;
    private  SQLiteDatabase db;


    public Connection(Context ctx)
    {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Open the database connection.
    public Connection open()
    {
        db = myDBHelper.getWritableDatabase();
        return this;
    }


    // Close the database connection.
    public void close()
    {
        myDBHelper.close();
    }

    public Cursor fetchParrent()
    {
        String query = "SELECT * FROM parrent";
        return myDBHelper.getReadableDatabase().rawQuery(query, null);
    }

    public Cursor fetchChildren(String _idchild)
    {
        String query = "SELECT * FROM child WHERE category =  '" + _idchild + "'";
        return myDBHelper.getReadableDatabase().rawQuery(query, null);
    }








    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db)
        {


            // create table user.

            String query_user = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT " + ");";

            String query_parrent = "CREATE TABLE " + TABLE_ITEMS_PARRENT + "(" + COLUMN_ITEM_PARRENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_PARRENT_NAME + " TEXT " + ");";

            String query_child = "CREATE TABLE " + TABLE_ITEM_CHILD + "(" + COLUMN_ITEM_CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_CHILD_CATEGORY + " INTEGER, " + COLUMN_ITEM_CHILD_NAME + " TEXT, " + COLUMN_ITEM_CHILD_DESCRIPTION + " TEXT, " + " FOREIGN KEY ("+COLUMN_ITEM_CHILD_CATEGORY+") REFERENCES "+ TABLE_ITEMS_PARRENT +"(" +COLUMN_ITEM_PARRENT_ID +"));";

            _db.execSQL(query_user);
            _db.execSQL(query_parrent);
            _db.execSQL(query_child);





            // insert to tables

            String A = "INSERT INTO parrent (_idparrent, parrentname) VALUES (1, 'A')";
            String B = "INSERT INTO parrent (_idparrent, parrentname) VALUES (2, 'B')";
            String C = "INSERT INTO parrent (_idparrent, parrentname) VALUES (3, 'C')";
            SQLiteStatement statement1 = _db.compileStatement(A);
            SQLiteStatement statement2 = _db.compileStatement(B);
            SQLiteStatement statement3 = _db.compileStatement(C);
            long rowId1 = statement1.executeInsert();
            long rowId2 = statement2.executeInsert();
            long rowId3 = statement3.executeInsert();

            //Insert to child ABC from 1 - 161 for each

            int category_a = 1;
            String childname_a = "A";

            int category_b = 2;
            String childname_b = "B";

            int category_c = 3;
            String childname_c = "C";

            String description = "Indsæt beskrivelse her";


            try

            {

                _db.beginTransaction();
                String sql = "INSERT INTO child (_idchild, category, childname, description) VALUES (?, ?, ?, ?)";
                SQLiteStatement statement = _db.compileStatement(sql);

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

                _db.setTransactionSuccessful(); // This commits the transaction if there were no exceptions

            }
            catch (Exception e)
            {
                Log.w("Exception:", e);
            } finally {
                _db.endTransaction();
            }













        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
           _db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
           _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS_PARRENT);
            _db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_CHILD);

            // Recreate new database:
            onCreate(_db);
        }
    }

















}
