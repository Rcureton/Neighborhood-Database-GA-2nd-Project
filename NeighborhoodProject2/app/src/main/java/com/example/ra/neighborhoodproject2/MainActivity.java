package com.example.ra.neighborhoodproject2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ra.neighborhoodproject2.data.Channel;
import com.example.ra.neighborhoodproject2.data.Item;
import com.example.ra.neighborhoodproject2.services.WeatherServiceCallback;
import com.example.ra.neighborhoodproject2.services.YahooWeatherService;

import java.util.ArrayList;
import java.util.Deque;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherImageIcon;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

     ListView mListView;
    CursorAdapter mCursorAdapter;
     NeighborhoodSQLiteHelper mHelper;
    ArrayAdapter<Neighborhood> mAdapter;
    ArrayList<Neighborhood> mNeighborhoodList;
    FloatingActionButton mFab;
    Button mIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: casting all of my views

        weatherImageIcon= (ImageView)findViewById(R.id.weatherIconImage);
        temperatureTextView=(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView=(TextView)findViewById(R.id.conditionTextView);
        locationTextView=(TextView)findViewById(R.id.location);

        service= new YahooWeatherService(this);
        service.refreshWeather("New York, NY");
        dialog= new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        setTitle("Welcome to NYC");


        //TODO:getting the helper class to make reference to my database.

        NeighborhoodSQLiteHelper helper=  new NeighborhoodSQLiteHelper(MainActivity.this);
        //TODO:Adding Neighborhoods to the Database

        helper.insert(1,"Brooklyn Museum","Neighborhood: Brooklyn" ,"Address: 200 Eastern Pkwy, Brooklyn, NY 11238",
      "Description: The Brooklyn Museum is an art museum located in the New York City borough of Brooklyn. At 560,000 square feet (52,000 m), the museum is New York City's third largest in physical size and holds an art collection with roughly 1.5 million works.\n" +
              " Located near the Prospect Heights, Crown Heights, Flatbush, and Park Slope neighborhoods of Brooklyn and founded in 1895, the Beaux-Arts building, designed by McKim, Mead and White, was planned to be the largest art museum in the world.\n" +
              " The museum initially struggled to maintain its building and collection, only to be revitalized in the late 20th-century, thanks to major renovations. Significant areas of the collection include antiquities, specifically their collection of Egyptian antiquities spanning over 3,000 years. African, Oceanic, and Japanese art make for notable antiquities collections as well. American art is heavily represented, starting at the Colonial period.\n" +
              " Artists represented in the collection include Mark Rothko, Edward Hopper, Norman Rockwell, Winslow Homer, Edgar Degas, Georgia O'Keeffe, and Max Weber. The museum also has a Memorial Sculpture Garden which features salvaged architectural elements from throughout New York City.\"", 1,
                        "https://www.brooklynmuseum.org/");


        helper.insert(2,"Brooklyn Bridge","Neighborhood: Brooklyn" ,"Address: Brooklyn Bridge, New York, NY",
        "Description: The Brooklyn Bridge is a hybrid cable-stayed/suspension bridge in New York City and is one of the oldest bridges of either type in the United States. Completed in 1883, it connects the boroughs of Manhattan and Brooklyn by spanning the East River. It has a main span of 1,595.5 feet (486.3 m), and was the first steel-wire suspension bridge constructed.\n" +
                " It was originally referred to as the New York and Brooklyn Bridge and as the East River Bridge, but it was later dubbed the Brooklyn Bridge, a name coming from an earlier January 25, 1867,\n" +
                " letter to the editor of the Brooklyn Daily Eagle, and formally so named by the city government in 1915. Since its opening, it has become an icon of New York City, and was designated a National Historic Landmark in 1964 and a National Historic Civil Engineering Landmark in 1972. ", 0,
                        "http://www.history.com/topics/brooklyn-bridge");


        helper.insert(3,"La Marina","Neighborhood: Washington Heights", "Address: 348 Dyckman St, New York, NY 10034",
        "Description: Dyckman Marina has been a vital part of upper Manhattan’s unique waterfront culture for more than two centuries. Where in former times, notable Manhattanites kept their summer estates and yachts, where seaplanes and ferries dotted the piers, nestled just below the Cloisters at the end of Dyckman Street along the Hudson River, sits the new La Marina, NOW OPEN. The newly rebuilt restaurant, bar, lounge and events space covers more than 75,000 square feet of Hudson River waterfront in Upper Manhattan for you to plan your event. A short walk from the A-train, La Marina is the ideal spot in NYC to enjoy cocktails at sunset, to hop off your bike for a break, to host your event, or to enjoy a romantic,\n" +
                "  moonlit dinner for two. La Marina offers a unique experience that infuses a touch of South Beach in Manhattan, setting the perfect backdrop for private social events or corporate parties. ", 1,
                        "https://foursquare.com/v/la-marina-nyc/4faad0484fc68152ec7e368c");


        helper.insert(4,"Freedom Tower", "Neighborhood: Manhattan", "Address: One World Trade Center",
        "Description:  The Brooklyn Heights Promenade will take your breath away. Made famous by cameo appearances in movies like Annie Hall and Moonstruck, it is one of the most romantic spots in New York City, and has been the destination for thousands of first dates, wedding proposals and anniversary celebrations. One-third of a mile long, it offers a vista of the Statue of Liberty,\n" +
                "         the Manhattan skyline and the majestic Brooklyn Bridge. Lined with flower beds, trees, benches and playgrounds, the promenade is a favorite destination for tourists, joggers, strollers, families and lovers." , 0, "https://oneworldobservatory.com/");


        helper.insert(5,"Brooklyn Heights Promenade", "Neighborhood: Brooklyn","Address: Pierrepont Pl, Brooklyn, NY 11201",
         "Description:  The Brooklyn Heights Promenade will take your breath away. Made famous by cameo appearances in movies like Annie Hall and Moonstruck, it is one of the most romantic spots in New York City, and has been the destination for thousands of first dates, wedding proposals and anniversary celebrations. One-third of a mile long, it offers a vista of the Statue of Liberty,\n" +
                 "         the Manhattan skyline and the majestic Brooklyn Bridge. Lined with flower beds, trees, benches and playgrounds, the promenade is a favorite destination for tourists, joggers, strollers, families and lovers." ,1,
                        "https://foursquare.com/v/brooklyn-heights-promenade/42377700f964a52024201fe3");


        helper.insert(6, "Meatball Shop", "Neighborhood: Lower East Side", "Address: 84 Stanton St, New York, NY 10002", "Description: The Meatball Shop has given us the opportunity to give back to the city we love so much, we've created this place for YOU. Enjoy the menu on your own terms, eat simple, eat sustainable, eat good food with no strings attached.", 1
                ,"https://foursquare.com/v/the-meatball-shop/4b709d01f964a5204c252de3");


        helper.insert(7, "Havana Central", "Neighborhood: Upper West Side", "Address: 2911 Broadway, New York, NY 10025",
        "Description: Back in 2001, Jeremy Merrin was looking for a new, entrepreneurial concept. He loves Latin cooking and frequented local Cuban restaurants. After seeing the popularity of many local Cuban restaurants, he began to think about a concept where people could get big plates of rice and beans for reasonable prices. However, he knew nothing about the restaurant business. After extensive interviewing, Jeremy met with Arlene Spiegel, a restaurant consultant and former restaurant owner. Together,\n" +
                "        they decided that there is a great opportunity in Cuban cuisine and dedicated the next nine months to creating a restaurant plan that had the best possible chance for success.Jeremy’s first hire, to manage the restaurant was Stanley Licairac. Stanley was (and still is) instrumental in setting the culinary direction and very much the atmosphere of Havana Central. Stanley is currently the Executive Chef of Havana Central.\n" +
                "        On June 24th, 2002, Havana Central opened its doors to a big crowd and great reviews.", 0
                    ,"http://www.opentable.com/havana-central-times-square");

        helper.insert(8,"The Highline NYC", "Neighborhood: Chelsea", "Address: Manhattan, New York 10011",
                "Description: The High Line (also known as the High Line Park) is a 1.45-mile-long (2.33 km) New York City linear park built in Manhattan on an elevated section of a disused New York Central Railroad spur called the West Side Line. " +
                        "Inspired by the 3-mile (4.8-kilometer) Promenade plantée (tree-lined walkway), a similar project in Paris completed in 1993," +
                        " the High Line has been redesigned and planted as an aerial greenway and rails-to-trails park.",0, "http://www.thehighline.org/");

        helper.insert(9,"General Assembly", "Neighborhood: Flatiron District", "Address: 10 East 21st Street,New York, New York, 10010",
                "Description: Established in early 2011 as an innovative community in New York City for entrepreneurs and startup companies," +
                        " General Assembly is an educational institution that transforms thinkers into creators through education in technology," +
                        " business and design at fifteen campuses across four continents", 1, "https://generalassemb.ly/");

        helper.insert(10,"Charging Bull", "Neighborhood: Financial District", "Address: Broadway & Morris Street, New York, New York",
                "Description: The 3,200-kilogram (7,100 lb) sculpture stands 11 feet (3.4 m) tall[1] and measures 16 feet (4.9 m) long.The oversize sculpture depicts a bull, the symbol of aggressive financial optimism and prosperity, " +
                        "leaning back on its haunches and with its head lowered as if ready to charge. The sculpture is both a popular tourist destination which draws thousands of people a day, as well" +
                        " as one of the most iconic images of New York and a Wall Street icon symbolizing Wall Street and the Financial District.",0, "http://chargingbull.com/");

        helper.insert(11,"Pies 'n' Thighs", "Neighborhood: Williamsburg", "Address: 166 S. 4th Street, Brooklyn, NY 11211",
                "Description: Pies ’n’ Thighs started in 2006, in the shadow of the Williamsburg Bridge. We took over a beer storage closet and transformed it " +
                "into a tiny kitchen with six stools, serving huge donuts, two-napkin smoked pork sandwiches and salty, crispy fried chicken. The New York Times called the pies and the thighs “…a compelling combination," +
                " well executed and put forth with real heart—the sort of restaurant that’s hard to find, especially in the big city, but easy to love once found.”",0,
                        "https://foursquare.com/v/pies-n-thighs/448acb74f964a52048341fe3");

        helper.insert(12,"Habana Outpost", "Neighborhood: Clinton Hill", "Address: 757 Fulton St, Brooklyn, NY 11217",
                "Description: Really great atmosphere with people from lots of different areas. The space outside is really nice, as are the music and the food! Strongly recommended with to go with a group of friends!",0,
                        "https://foursquare.com/v/habana-outpost/42d1b680f964a5200b261fe3");

        helper.insert(13,"Amy Ruth's", "Neighborhood: East Harlem", "Address: 113 W 116th St, New York, NY 10026",
                "Description: The same love that inspired Amy Ruth to prepare delicious food to delight her family and friends is still alive and well at Amy Ruth’s Restaurant today. Every guest that comes to Amy Ruth’s Restaurant is treated to excellent home-style Southern cuisine, prepared and served with genuine love and care.",1,
                        "https://foursquare.com/v/amy-ruths/3fd66200f964a52066e81ee3");


        helper.insert(14,"Harlem Tavern", "Neighborhood: Harlem", "Address: 2153 Frederick Douglass Blvd, New York, NY 10026",
                "Description: Harlem Tavern is New York City’s premiere restaurant and beer garden.  An uptown neighborhood bar and grill, Harlem Tavern is located on 116th St and Frederick Douglass Blvd just blocks from historic Central Park.  Harlem Tavern offers an ideal space for all dining experiences. " +
                        " Its family friendly environment has something for everyone: cutting edge cocktails, a large craft beer selection and a menu designed to suit all tastes.",0,
                        "https://foursquare.com/v/harlem-tavern/4deffce2b0fbfee72f3cacde");

        helper.insert(15,"Slate NYC", "Neighborhood: Flatiron District", "Address: 54 W 21st St, New York, NY 10010",
                "Description: SLATE defines elegance and excitement. Located in the heart of the Flatiron District, this one-of-a-kind upscale bar, lounge and club offers something for everyone. From film stars and musicians, to bankers and brokers, many of the world’s hottest celebrities and prestigious corporations have discovered that SLATE is the perfect venue for their night out or event",0,
                        "http://www.opentable.com/slate-new-york");


        //TODO: Creating a simple cursor adapter

        mListView=(ListView)findViewById(R.id.listView);
        final Cursor cursor= NeighborhoodSQLiteHelper.getInstance(MainActivity.this).getNeighborhoods();
            mCursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,cursor,new String[]{NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_NAME},new int[]{android.R.id.text1},0);
            mListView.setAdapter(mCursorAdapter);
//


                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NeighborhoodResultActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_ID)));
                startActivity(intent);
            }
        });


        handleIntent (getIntent() );


    }

    //TODO: The method below is for our search

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);


            Toast.makeText(MainActivity.this, "You are searching for " + query, Toast.LENGTH_SHORT).show();

            Log.d("Test for Toast", "Toast");
            Cursor cursor = NeighborhoodSQLiteHelper.getInstance(this).searchNeighborhoods(query);
//            while(cursor !=null){
//                cursor.getString(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_NAME));
//                cursor.isAfterLast();
//            }

            mCursorAdapter.swapCursor(cursor);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.favorites:
                Intent intent= new Intent(MainActivity.this, NeighborhoodFavorites.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_neighborhoods_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        SearchableInfo info =searchManager.getSearchableInfo(getComponentName());

        searchView.setSearchableInfo(info);


        return true;
    }

    //TODO: This method is implementing my Weather API

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId= getResources().getIdentifier("drawable/icon_" + item.getConditions().getCode(), null, getPackageName());

        @SuppressLint({"NewApi", "LocalSuppress"})
        Drawable weatherIcon= getResources().getDrawable(resourceId, null);

        weatherImageIcon.setImageDrawable(weatherIcon);

        locationTextView.setText(service.getLocation());

        temperatureTextView.setText(item.getConditions().getTemperature() +"\u00B0 "+ channel.getUnits().getTemperature() );

        conditionTextView.setText(item.getConditions().getDescription());



    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
