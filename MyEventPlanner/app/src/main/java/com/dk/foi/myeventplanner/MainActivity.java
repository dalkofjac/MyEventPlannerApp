package com.dk.foi.myeventplanner;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dk.foi.myeventplanner.activities.AppPreferenceActivity;
import com.dk.foi.myeventplanner.activities.LoginActivity;
import com.dk.foi.myeventplanner.enums.FragmentLevel;
import com.dk.foi.myeventplanner.fragments.AboutAppFragment;
import com.dk.foi.myeventplanner.fragments.BirthdaysFragment;
import com.dk.foi.myeventplanner.fragments.HolidaysFragment;
import com.dk.foi.myeventplanner.fragments.MainScreenFragment;
import com.dk.foi.myeventplanner.fragments.OtherEventsFragment;
import com.dk.foi.myeventplanner.fragments.RegisterFragment;
import com.dk.foi.myeventplanner.fragments.UpcomingEventsFragment;
import com.dk.foi.myeventplanner.helpers.FragmentStarter;
import com.dk.foi.myeventplanner.helpers.Util;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Util util = new Util();

    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount()==1){
                    drawer.openDrawer(GravityCompat.START);
                }else if(getFragmentManager().getBackStackEntryCount()==2){
                    drawer.openDrawer(GravityCompat.START);
                }
                else {
                    onBackPressed();
                }
            }
        });

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        util.setLanguage(this);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.activity_main_drawer);

        mainActivity = this;

        MainScreenFragment msf = new MainScreenFragment();
        fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentStarter.StartNewFragment(msf, this, FragmentLevel.INDEX);
    }

    @Override
    public void onBackPressed() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if (backStackCount>=2){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                fragmentManager.popBackStack();
            }
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else {
                this.finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AppPreferenceActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_about) {
            AboutAppFragment af = new AboutAppFragment();
            FragmentStarter.StartNewFragment(af, this, FragmentLevel.LEVEL_ONE);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_start:
                MainScreenFragment msf = new MainScreenFragment();
                fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentStarter.StartNewFragment(msf, this, FragmentLevel.INDEX);
                break;
            case R.id.nav_upcoming:
                UpcomingEventsFragment uf = new UpcomingEventsFragment();
                FragmentStarter.StartNewFragment(uf, this, FragmentLevel.LEVEL_ONE);
                break;
            case R.id.nav_holiday:
                HolidaysFragment hf = new HolidaysFragment();
                FragmentStarter.StartNewFragment(hf, this, FragmentLevel.LEVEL_ONE);
                break;
            case R.id.nav_birthday:
                BirthdaysFragment bf = new BirthdaysFragment();
                FragmentStarter.StartNewFragment(bf, this, FragmentLevel.LEVEL_ONE);
                break;
            case R.id.nav_other:
                OtherEventsFragment of = new OtherEventsFragment();
                FragmentStarter.StartNewFragment(of, this, FragmentLevel.LEVEL_ONE);
                break;
            case R.id.nav_reg:
                RegisterFragment ref = new RegisterFragment();
                FragmentStarter.StartNewFragment(ref, this, FragmentLevel.LEVEL_ONE);
                break;
            case R.id.nav_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            default: break;
        }

        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackStackChanged() {
        toggle.setDrawerIndicatorEnabled(fragmentManager.getBackStackEntryCount()==1
                || fragmentManager.getBackStackEntryCount()==2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount()>2);
        toggle.syncState();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        util.setLanguage(this);
        this.recreate();
    }

    public static MainActivity getInstance(){
        return mainActivity;
    }
}
