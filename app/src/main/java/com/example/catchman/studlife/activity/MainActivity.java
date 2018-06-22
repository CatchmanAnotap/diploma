package com.example.catchman.studlife.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.catchman.studlife.fragments.FindRoomFragment;
import com.example.catchman.studlife.FragmentUtil;
import com.example.catchman.studlife.fragments.NewsFragment;
import com.example.catchman.studlife.fragments.PosterFragment;
import com.example.catchman.studlife.R;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        FragmentUtil.replaceFragment(getSupportFragmentManager(),new PosterFragment(), false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.nav_poster:
                Toast.makeText(this, "nav_poster", Toast.LENGTH_SHORT).show();
                FragmentUtil.replaceFragment(getSupportFragmentManager(),new PosterFragment(), false);
                break;
            case R.id.nav_news:
                Toast.makeText(this, "nav_news", Toast.LENGTH_SHORT).show();
                FragmentUtil.replaceFragment(getSupportFragmentManager(),new NewsFragment(), false);
                break;
            case R.id.nav_find_room:
                Toast.makeText(this, "nav_find_room", Toast.LENGTH_SHORT).show();
                FragmentUtil.replaceFragment(getSupportFragmentManager(),new FindRoomFragment(), false);
                break;
            case R.id.nav_schedule:
                intent = new Intent(this, OtherActivity.class);
                intent.putExtra("index", OtherActivity.SCHEDULE_INDEX);
                startActivity(intent);
                break;
            case R.id.nav_info:
                intent = new Intent(this, OtherActivity.class);
                intent.putExtra("index", OtherActivity.INFO_INDEX);
                startActivity(intent);
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
