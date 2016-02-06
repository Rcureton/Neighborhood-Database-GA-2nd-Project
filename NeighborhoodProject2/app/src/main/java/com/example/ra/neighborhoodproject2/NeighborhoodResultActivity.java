package com.example.ra.neighborhoodproject2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class NeighborhoodResultActivity extends AppCompatActivity {
    private NeighborhoodSQLiteHelper mHelper;
    TextView mNeighborhoodName;
    TextView mNeighborhoodAddress;
    TextView mNeighborhoodDescription;
    TextView mNeighborhoodNeighborhood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_result);

        Intent intent= getIntent();
        int id = intent.getIntExtra("id", -1);

        mHelper= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this);


        String name= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodNameById(id);
        String address= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodAddressById(id);
        String description= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodDescriptionById(id);
        String neighborhood= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodDiffNameById(id);

        if(id >=0) {
            String itemName = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodNameById(id);
            TextView textView = (TextView) findViewById(R.id.neighborhoodName);
            textView.setText(itemName);

            mNeighborhoodAddress = (TextView) findViewById(R.id.neighborhoodAddress);
            mNeighborhoodDescription = (TextView) findViewById(R.id.neighborhoodDescription);
            mNeighborhoodNeighborhood = (TextView) findViewById(R.id.neighborhoodNeighborhood);

            mNeighborhoodAddress.setText(address);
            mNeighborhoodDescription.setText(description);
            mNeighborhoodNeighborhood.setText(neighborhood);


            ImageView imageView= (ImageView)findViewById(R.id.imageView);
            imageView.setImageResource(getDrawableValue(name));

        }


    }
    private int getDrawableValue(String icon){
        switch(icon){
            case "Brooklyn Museum":
                return R.drawable.brooklynmuseum;
            case "Brooklyn Bridge":
                return R.drawable.brookylnbridgeone;
            case "La Marina":
                return R.drawable.lamarina;
            case "Freedom Tower":
                return R.drawable.freedom;
            case "Brooklyn Heights Promenade":
                return R.drawable.brooklynp;
            case "Meatball Shop":
                return R.drawable.meatballshop;
            case "Havana Central":
                return R.drawable.havana;
            default:
                return 0;
        }
    }
}
