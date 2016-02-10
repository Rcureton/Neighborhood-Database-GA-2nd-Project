package com.example.ra.neighborhoodproject2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NeighborhoodFavorites extends AppCompatActivity {
    private NeighborhoodSQLiteHelper mHelper;
    CursorAdapter mAdapter;
    int id;
    TextView mName;
    ListView mListview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_favorites);

        setTitle("Favorites");

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        mListview=(ListView)findViewById(R.id.listViewFavorites);
        mHelper = NeighborhoodSQLiteHelper.getInstance(NeighborhoodFavorites.this);

        Cursor cursor = NeighborhoodSQLiteHelper.getInstance(NeighborhoodFavorites.this).getFavorites(1);
        CursorAdapter cursorAdapter = new CursorAdapter(NeighborhoodFavorites.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.favorites_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, final Cursor cursor) {
                TextView textView = (TextView) view.findViewById(R.id.textViewFav1);
                TextView textView1 = (TextView) view.findViewById(R.id.textView2fFav);

                ImageView imageView = (ImageView) view.findViewById(R.id.imageView2Fav);
                String name = NeighborhoodSQLiteHelper.getInstance(NeighborhoodFavorites.this).getNeighborhoodNameById(id);

                textView.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_ADDRESS)));
                textView1.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_NAME)));

                imageView.setImageResource(mHelper.getDrawableValue(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_NAME))));


            }
        };
        mListview.setAdapter(cursorAdapter);
    }




        };

