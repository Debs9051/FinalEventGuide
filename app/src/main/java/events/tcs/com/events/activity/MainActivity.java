package events.tcs.com.events.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import events.tcs.com.events.R;
import events.tcs.com.events.fragment.DayOneFragment;
import events.tcs.com.events.fragment.DayThreeFragment;
import events.tcs.com.events.fragment.DayTwoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.ic_kol_howrah, R.drawable.ic_kol_hayaat, R.drawable.ic_kol_gate, R.drawable.ic_kol_eden};
    private int selectedId = 0;
    private Activity mActivity;
    private FragmentManager mFragmentManager;
    private View viewHome;
    private FrameLayout viewDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        viewHome = findViewById(R.id.layout_home);
        viewDays = findViewById(R.id.layout_main);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (selectedId != 0) {
                toggleHome(true);
                selectedId = 0;
            } else {
                super.onBackPressed();
            }
        }
    }

    private void toggleHome(boolean showHome) {
        if (showHome) {
            viewHome.setVisibility(View.VISIBLE);
            viewDays.setVisibility(View.GONE);
        } else {
            viewHome.setVisibility(View.GONE);
            viewDays.setVisibility(View.VISIBLE);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        selectedId = item.getItemId();
        mFragmentManager = getSupportFragmentManager();
        if (selectedId == R.id.nav_dayOne) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.layout_main, new DayOneFragment()).commit();
            toggleHome(false);
        } else if (selectedId == R.id.nav_dayTwo) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.layout_main, new DayTwoFragment()).commit();
            toggleHome(false);
        }

        else if (selectedId == R.id.nav_dayThree) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.layout_main, new DayThreeFragment()).commit();
            toggleHome(false);
        }

        else {
            toggleHome(true);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
