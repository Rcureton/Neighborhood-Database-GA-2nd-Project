package com.example.ra.neighborhoodproject2;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class NeighborhoodResultActivity extends AppCompatActivity {
    private NeighborhoodSQLiteHelper mHelper;

    TextView mNeighborhoodAddress;
    TextView mNeighborhoodDescription;
    TextView mNeighborhoodNeighborhood;
     FloatingActionButton mFaveButton;
    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighborhood_result);

//TODO: getting the intent from the Main.

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);




        mHelper = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this);

        if(mHelper.getNeighborhoodNameById(id)!=null ){
            setTitle(mHelper.getNeighborhoodNameById(id));
        }else{
            setTitle("Details");
        }

        //TODO: this is getting reference to the database and turning it into a string.

        String name = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodNameById(id);
        String address = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodAddressById(id);
        String description = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodDescriptionById(id);
        String neighborhood = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodDiffNameById(id);
        String url= NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodRatingsById(id);

        //TODO: This method is checking if there are any values at zero and if yes it's going to inflate the views.
        if (id >= 0) {
            String itemName = NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getNeighborhoodNameById(id);
            TextView textView = (TextView) findViewById(R.id.neighborhoodName);
            textView.setText(itemName);

            mNeighborhoodAddress = (TextView) findViewById(R.id.neighborhoodAddress);
            mNeighborhoodDescription = (TextView) findViewById(R.id.neighborhoodDescription);
            mNeighborhoodNeighborhood = (TextView) findViewById(R.id.neighborhoodNeighborhood);
            WebView myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl(url);

            mNeighborhoodAddress.setText(address);
            mNeighborhoodDescription.setText(description);
            mNeighborhoodNeighborhood.setText(neighborhood);

            ImageView fab = (FloatingActionButton) findViewById(R.id.myFAB);
            if (NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getFavoriteById(id) == 1) {
                fab.setImageResource(R.drawable.ic_favorite);
            } else {
                fab.setImageResource(R.drawable.ic_favorite_outline);
            }

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(getDrawableValue(name));
        }


        mFaveButton = (FloatingActionButton) findViewById(R.id.myFAB);
        mFaveButton.setOnClickListener(updateFavorites);

    }

    //TODO: This method below is a dialog button onclick it will ask if you want to add to favorites or take off.
    View.OnClickListener updateFavorites = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NeighborhoodResultActivity.this);
           final ImageView fab = (FloatingActionButton) findViewById(R.id.myFAB);
            if(NeighborhoodSQLiteHelper.getInstance(NeighborhoodResultActivity.this).getFavoriteById(id) ==0){
                builder.setMessage("Do you want to add this to Favorites?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHelper.updateFavorite(id, 1);
                        fab.setImageResource(R.drawable.ic_favorite); //If you click yes it will add to favorites
                    }
                });

            }else {
                builder.setMessage("Do you want to take off of Favorites?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHelper.updateFavorite(id, 0);
                        fab.setImageResource(R.drawable.ic_favorite_outline);
                    }
                });

            }

            AlertDialog alert = builder.create();
            alert.setTitle("Favorites");
            alert.show();

        }
    };


//TODO: getting references to the images and matching to the string name.

    private int getDrawableValue(String icon){
        switch(icon){
            case "Brooklyn Museum":
                return R.drawable.brooklynmuseum1;
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
            case "The Highline NYC":
                return R.drawable.highline;
            case "General Assembly":
                return R.drawable.generalassembly;
            case "Charging Bull":
                return R.drawable.wallstreetbull;
            case "Pies 'n' Thighs":
                return R.drawable.pies;
            case "Habana Outpost":
                return R.drawable.habana;
            case "Amy Ruth's":
                return R.drawable.amyruth;
            case "Harlem Tavern":
                return R.drawable.harlemtavern;
            case "Slate NYC":
                return R.drawable.slate;
            default:
                return 0;
        }

    }


}






