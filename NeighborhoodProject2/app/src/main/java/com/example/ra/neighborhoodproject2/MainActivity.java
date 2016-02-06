package com.example.ra.neighborhoodproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Deque;

public class MainActivity extends AppCompatActivity {
     ListView mListView;
    CursorAdapter mCursorAdapter;
     NeighborhoodSQLiteHelper mHelper;
    ArrayAdapter<Neighborhood> mAdapter;
    ArrayList<Neighborhood> mNeighborhoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NeighborhoodSQLiteHelper helper=  new NeighborhoodSQLiteHelper(MainActivity.this);
        //Adding Neighborhoods to the Database

    /*    helper.insert(1,"Brooklyn Museum","Neighborhood: Brooklyn" ,"Address: 200 Eastern Pkwy, Brooklyn, NY 11238",
      "Description: The Brooklyn Museum is an art museum located in the New York City borough of Brooklyn. At 560,000 square feet (52,000 m), the museum is New York City's third largest in physical size and holds an art collection with roughly 1.5 million works.\n" +
              " Located near the Prospect Heights, Crown Heights, Flatbush, and Park Slope neighborhoods of Brooklyn and founded in 1895, the Beaux-Arts building, designed by McKim, Mead and White, was planned to be the largest art museum in the world.\n" +
              " The museum initially struggled to maintain its building and collection, only to be revitalized in the late 20th-century, thanks to major renovations. Significant areas of the collection include antiquities, specifically their collection of Egyptian antiquities spanning over 3,000 years. African, Oceanic, and Japanese art make for notable antiquities collections as well. American art is heavily represented, starting at the Colonial period.\n" +
              " Artists represented in the collection include Mark Rothko, Edward Hopper, Norman Rockwell, Winslow Homer, Edgar Degas, Georgia O'Keeffe, and Max Weber. The museum also has a Memorial Sculpture Garden which features salvaged architectural elements from throughout New York City.\"");


        helper.insert(2,"Brooklyn Bridge","Neighborhood: Brooklyn" ,"Address: Brooklyn Bridge, New York, NY",
        "Description: The Brooklyn Bridge is a hybrid cable-stayed/suspension bridge in New York City and is one of the oldest bridges of either type in the United States. Completed in 1883, it connects the boroughs of Manhattan and Brooklyn by spanning the East River. It has a main span of 1,595.5 feet (486.3 m), and was the first steel-wire suspension bridge constructed.\n" +
                " It was originally referred to as the New York and Brooklyn Bridge and as the East River Bridge, but it was later dubbed the Brooklyn Bridge, a name coming from an earlier January 25, 1867,\n" +
                " letter to the editor of the Brooklyn Daily Eagle, and formally so named by the city government in 1915. Since its opening, it has become an icon of New York City, and was designated a National Historic Landmark in 1964 and a National Historic Civil Engineering Landmark in 1972. ");


        helper.insert(3,"La Marina","Neighborhood: Washington Heights", "Address: 348 Dyckman St, New York, NY 10034",
        "Description: Dyckman Marina has been a vital part of upper Manhattan’s unique waterfront culture for more than two centuries. Where in former times, notable Manhattanites kept their summer estates and yachts, where seaplanes and ferries dotted the piers, nestled just below the Cloisters at the end of Dyckman Street along the Hudson River, sits the new La Marina, NOW OPEN. The newly rebuilt restaurant, bar, lounge and events space covers more than 75,000 square feet of Hudson River waterfront in Upper Manhattan for you to plan your event. A short walk from the A-train, La Marina is the ideal spot in NYC to enjoy cocktails at sunset, to hop off your bike for a break, to host your event, or to enjoy a romantic,\n" +
                "  moonlit dinner for two. La Marina offers a unique experience that infuses a touch of South Beach in Manhattan, setting the perfect backdrop for private social events or corporate parties. ");


        helper.insert(4,"Freedom Tower", "Neighborhood: Manhattan", "Address: One World Trade Center",
        "Description:  The Brooklyn Heights Promenade will take your breath away. Made famous by cameo appearances in movies like Annie Hall and Moonstruck, it is one of the most romantic spots in New York City, and has been the destination for thousands of first dates, wedding proposals and anniversary celebrations. One-third of a mile long, it offers a vista of the Statue of Liberty,\n" +
                "         the Manhattan skyline and the majestic Brooklyn Bridge. Lined with flower beds, trees, benches and playgrounds, the promenade is a favorite destination for tourists, joggers, strollers, families and lovers." );


        helper.insert(5,"Brooklyn Heights Promenade", "Neighborhood: Brooklyn","Address: Pierrepont Pl, Brooklyn, NY 11201",
         "Description:  The Brooklyn Heights Promenade will take your breath away. Made famous by cameo appearances in movies like Annie Hall and Moonstruck, it is one of the most romantic spots in New York City, and has been the destination for thousands of first dates, wedding proposals and anniversary celebrations. One-third of a mile long, it offers a vista of the Statue of Liberty,\n" +
                 "         the Manhattan skyline and the majestic Brooklyn Bridge. Lined with flower beds, trees, benches and playgrounds, the promenade is a favorite destination for tourists, joggers, strollers, families and lovers.");


        helper.insert(6, "Meatball Shop", "Neighborhood: Lower East Side", "Address: 84 Stanton St, New York, NY 10002", "Description: The Meatball Shop has given us the opportunity to give back to the city we love so much, we've created this place for YOU. Enjoy the menu on your own terms, eat simple, eat sustainable, eat good food with no strings attached.");


        helper.insert(7, "Havana Central", "Neighborhood: Upper West Side", "Address: 2911 Broadway, New York, NY 10025",
        "Description: Back in 2001, Jeremy Merrin was looking for a new, entrepreneurial concept. He loves Latin cooking and frequented local Cuban restaurants. After seeing the popularity of many local Cuban restaurants, he began to think about a concept where people could get big plates of rice and beans for reasonable prices. However, he knew nothing about the restaurant business. After extensive interviewing, Jeremy met with Arlene Spiegel, a restaurant consultant and former restaurant owner. Together,\n" +
                "        they decided that there is a great opportunity in Cuban cuisine and dedicated the next nine months to creating a restaurant plan that had the best possible chance for success.Jeremy’s first hire, to manage the restaurant was Stanley Licairac. Stanley was (and still is) instrumental in setting the culinary direction and very much the atmosphere of Havana Central. Stanley is currently the Executive Chef of Havana Central.\n" +
                "        On June 24th, 2002, Havana Central opened its doors to a big crowd and great reviews.");
*/


        mListView=(ListView)findViewById(R.id.listView);
        final Cursor cursor= NeighborhoodSQLiteHelper.getInstance(MainActivity.this).getNeighborhoods();
        mCursorAdapter= new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,cursor,new String[]{NeighborhoodSQLiteHelper.COL_NEIGHBORHOOD_NAME},new int[]{android.R.id.text1},0);
        mListView.setAdapter(mCursorAdapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NeighborhoodResultActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(NeighborhoodSQLiteHelper.COL_ID)));
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "You are searching for " + query, Toast.LENGTH_SHORT).show();
            Cursor cursor = mHelper.searchNeighborhoods(query);
            mCursorAdapter.changeCursor(cursor);
            mCursorAdapter.swapCursor(cursor);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

}
