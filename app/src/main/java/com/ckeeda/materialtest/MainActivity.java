package com.ckeeda.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    RecyclerView recyclerView;
    Recycler_Adapter recycler_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);


        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment navigation_drawer = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
        navigation_drawer.setUp(R.id.drawer_fragment,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);


        /*recycler_adapter = new Recycler_Adapter(getApplicationContext(),getdata());
        recyclerView.setAdapter(recycler_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext())); */

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu );
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_setting){
            Toast.makeText(this,"You hit Settings..",Toast.LENGTH_LONG).show();
            return true;

        }
        if(id == R.id.next){
            startActivity(new Intent(this,SubActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
