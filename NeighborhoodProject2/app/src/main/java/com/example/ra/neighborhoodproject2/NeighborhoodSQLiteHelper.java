package com.example.ra.neighborhoodproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ra on 2/2/16.
 */
public class NeighborhoodSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "NEIGHBORHOOD_DB";
    public static final String NEIGHBORHOOD_TABLE_NAME= "NEIGHBORHOOD_TABLE";

    public static final String COL_ID = "_id";
    public static final String COL_NEIGHBORHOOD_NAME = "NEIGHBORHOOD_NAME";
    public static final String COL_NEIGHBORHOOD_ADDRESS = "NEIGHBORHOOD_ADDRESS";
    public static final String COL_NEIGHBORHOOD_NEIGHBORHOOD = "NEIGHBORHOOD_NEIGHBORHOOD";
    public static final String COL_NEIGHBORHOOD_DESCRIPTION = "NEIGHBORHOOD_DESCRIPTION";

    public static final String[] NEIGHBORHOOD_COLUMNS = {COL_ID,COL_NEIGHBORHOOD_NAME,COL_NEIGHBORHOOD_ADDRESS,COL_NEIGHBORHOOD_NEIGHBORHOOD,COL_NEIGHBORHOOD_DESCRIPTION};

    private static final String CREATE_NEIGHBORHOOD_TABLE_NAME =
            "CREATE TABLE " + NEIGHBORHOOD_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NEIGHBORHOOD_NAME + " TEXT, " +
                    COL_NEIGHBORHOOD_ADDRESS + " TEXT, " +
                    COL_NEIGHBORHOOD_NEIGHBORHOOD + " TEXT, " +
                    COL_NEIGHBORHOOD_DESCRIPTION + " TEXT )";

    private Context mHelperContext;


    public NeighborhoodSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mHelperContext=context;

    }

    private static NeighborhoodSQLiteHelper instance;

    public static NeighborhoodSQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new NeighborhoodSQLiteHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEIGHBORHOOD_TABLE_NAME);
// db.execSQL("CREATE TABLE" + NEIGHBORHOOD_TABLE_NAME + "(" + COL_ID + ","
//                        + COL_NEIGHBORHOOD_NAME + " ," + COL_NEIGHBORHOOD_ADDRESS + " ," +
//                COL_NEIGHBORHOOD_NEIGHBORHOOD + "," + COL_NEIGHBORHOOD_DESCRIPTION + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NEIGHBORHOOD_TABLE_NAME");
        onCreate(db);


    }

    public void insert(int id,String name ,String neighborhood, String address, String description){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COL_ID,id);
        values.put(COL_NEIGHBORHOOD_NAME, name);
        values.put(COL_NEIGHBORHOOD_NEIGHBORHOOD,neighborhood);
        values.put(COL_NEIGHBORHOOD_ADDRESS, address);
        values.put(COL_NEIGHBORHOOD_DESCRIPTION, description);

        db.insert(NEIGHBORHOOD_TABLE_NAME, null, values);
    }
    public Neighborhood getNeighborhood(int id){
        SQLiteDatabase db= getReadableDatabase();

        String [] columns= new String[]{COL_ID,COL_NEIGHBORHOOD_NAME,COL_NEIGHBORHOOD_NEIGHBORHOOD, COL_NEIGHBORHOOD_ADDRESS, COL_NEIGHBORHOOD_DESCRIPTION };

        String selection = "id= ?";

        String [] selectionArgs= new String[]{String.valueOf(id)};

        Cursor cursor= db.query(NEIGHBORHOOD_TABLE_NAME, columns, selection, selectionArgs, null, null, null, null);

        cursor.moveToFirst();

        String name= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NAME));
        String neighborhood= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NEIGHBORHOOD));
        String address= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_ADDRESS));
        String description= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_DESCRIPTION));

        return new Neighborhood(id,name,neighborhood,address,description);
    }

    public ArrayList<Neighborhood> getAllNeighborhoods (){
        SQLiteDatabase db= getReadableDatabase();

        // SELECT name, year FROM games;
        String [] columns= new String [] {COL_ID, COL_NEIGHBORHOOD_NEIGHBORHOOD, COL_NEIGHBORHOOD_ADDRESS, COL_NEIGHBORHOOD_DESCRIPTION};


        Cursor cursor= db.query(NEIGHBORHOOD_TABLE_NAME, columns, null, null, null, null, null, null);

        cursor.moveToFirst();

        ArrayList<Neighborhood> neighborhoods = new ArrayList<Neighborhood>();

        while(!cursor.isAfterLast()){
            int id= cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name=cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NAME));
            String neighborhood= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NEIGHBORHOOD));
            String address= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_ADDRESS));
            String description= cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_DESCRIPTION));


            neighborhoods.add(new Neighborhood(id,name,neighborhood,address,description));
            cursor.moveToNext();

        }
        cursor.close();
        return neighborhoods;
    }

    public Cursor getNeighborhoods() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                NEIGHBORHOOD_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        Log.d("Get Results","cursor count"+ cursor.getCount());
        Log.d("get results2", Arrays.toString(cursor.getColumnNames()));
        return cursor;
    }

    public Cursor searchNeighborhoods(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                NEIGHBORHOOD_COLUMNS, // b. column names
                COL_NEIGHBORHOOD_NAME + " LIKE ?", // c. selections
                new String[]{"%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }
    public String getNeighborhoodNameById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                new String[]{COL_NEIGHBORHOOD_NAME}, // b. column names
                COL_ID + " LIKE ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NAME));
        } else {
            return "Id not found with " + id;
        }
    }

    public String getNeighborhoodAddressById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                new String[]{COL_NEIGHBORHOOD_ADDRESS}, // b. column names
                COL_ID + " LIKE ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_ADDRESS));
        } else {
            return "Id not found with " + id;
        }
    }

    public String getNeighborhoodDescriptionById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                new String[]{COL_NEIGHBORHOOD_DESCRIPTION}, // b. column names
                COL_ID + " LIKE ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_DESCRIPTION));
        } else {
            return "Id not found with " + id;
        }
    }

    public String getNeighborhoodDiffNameById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(NEIGHBORHOOD_TABLE_NAME, // a. table
                new String[]{COL_NEIGHBORHOOD_NEIGHBORHOOD}, // b. column names
                COL_ID + " LIKE ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_NEIGHBORHOOD_NEIGHBORHOOD));
        } else {
            return "Id not found with " + id;
        }
    }


}
