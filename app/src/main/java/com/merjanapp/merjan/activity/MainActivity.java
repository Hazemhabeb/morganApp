package com.merjanapp.merjan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.ViewPagerAdapter;
import com.merjanapp.merjan.fragment.ActivityFragment;
import com.merjanapp.merjan.fragment.HotelFragment;
import com.merjanapp.merjan.fragment.JourneyFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //the left menu
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ImageView menuIV;
    private int mSelectedId;


    //init the tab view
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);


        setupViewPager(viewPager);

        initViews();


    }

    /**
     * action the search
     */
    @OnClick(R.id.searchAction)void searchAction(){
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new JourneyFragment(), "العطلات");
        adapter.addFragment(new ActivityFragment(), "الانشطة");
        adapter.addFragment(new HotelFragment(), "الفنادق");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setCurrentItem(1);

        tabLayout.setupWithViewPager(viewPager);
    }


    private void initViews() {
        mDrawer = findViewById(R.id.nvView);
        mDrawerLayout = findViewById(R.id.drawer_layout);


        mDrawer.setNavigationItemSelectedListener(this);


        menuIV = findViewById(R.id.menuIV);

        menuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.END)) {
                    mDrawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

    }


    @SuppressLint("WrongConstant")
    private void itemSelection(int mSelectedId) {

        switch (mSelectedId) {
            case R.id.item_navigation_profile:
                String url = "http://172.107.175.236:50/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.item_navigation_contactus:
                String url1 = "http://172.107.175.236/Home/ContactUs";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url1));
                startActivity(i1);
                break;

            case R.id.item_navigation_about:
                String url2 = "http://172.107.175.236/Home/WhoUs";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2));
                startActivity(i2);
                break;

            case R.id.item_navigation_search:
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
                break;

        }

    }


    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param ,menuItem The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(false);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID", mSelectedId);
    }
}



