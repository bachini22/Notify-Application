package com.example.ian.notify2;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainMenu extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerListener;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        listView=(ListView) findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);


        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        drawerListener=new ActionBarDrawerToggle(this, drawerLayout,R.drawable.navigationlogo, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(drawerListener.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    class MyAdapter extends BaseAdapter {

        private Context context;

        String[] items;
        int[] images = {R.drawable.home, R.drawable.signout};

        public MyAdapter(Context context){
            this.context = context;
            items=context.getResources().getStringArray(R.array.navigationItems);
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.custom_row, parent, false);
            }
            else{
                row = convertView;

            }
            TextView titleTextView = (TextView) row.findViewById(R.id.text);
            ImageView titleImageView = (ImageView) row.findViewById(R.id.imageView1);
            titleTextView.setText(items[position]);
            titleImageView.setImageResource(images[position]);

            return row;
        }
    }
}
