package tn.leaderscodes.planetecroisiere.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.fragments.CompaniesFragment;
import tn.leaderscodes.planetecroisiere.fragments.DestinationFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelBoatFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelCabinsFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelGoodToKnowFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelInfoFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelItineraryFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelPlacesFragment;
import tn.leaderscodes.planetecroisiere.fragments.TravelTarifFragment;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;

public class DetailTravel extends BaseActivity {

    private ViewPager mViewPager;
    private ActionBar actionBar;
    private TextView travelName;
    private ImageView travel_img;

    private TravelInfoFragment travelInfoFragment;
    private TravelItineraryFragment itineraryFragment;
    private TravelPlacesFragment travelPlacesFragment;
    private TravelGoodToKnowFragment travelGoodToKnowFragment;
    private TravelCabinsFragment travelCabinsFragment;
    private TravelBoatFragment travelBoatFragment;
    private TravelTarifFragment travelTarifFragment;

    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_travel);
        mViewPager = findViewById(R.id.viewpager);
        travelName = findViewById(R.id.travelName);
        travel_img = findViewById(R.id.travel_img);
        travel = (Travel) getIntent().getSerializableExtra("Travel");
        setupViewPager(mViewPager);
        travelName.setText(travel.getCruise().getName());
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        initializeToolBar();
        Glide.with(getApplicationContext()).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/place/montego-bay-jamaique_219.jpg").into(travel_img);
    }

    @Override
    public void initializeToolBar() {
        super.initializeToolBar();
        // init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        bundle.putSerializable("Travel", travel);
        travelInfoFragment = new TravelInfoFragment();
        travelInfoFragment.setArguments(bundle);
        itineraryFragment = new TravelItineraryFragment();
        itineraryFragment.setArguments(bundle);
        travelPlacesFragment = new TravelPlacesFragment();
        travelPlacesFragment.setArguments(bundle);
        travelGoodToKnowFragment = new TravelGoodToKnowFragment();
        travelGoodToKnowFragment.setArguments(bundle);
        travelCabinsFragment = new TravelCabinsFragment();
        travelCabinsFragment.setArguments(bundle);
        travelBoatFragment = new TravelBoatFragment();
        travelBoatFragment.setArguments(bundle);
        travelTarifFragment = new TravelTarifFragment();
        travelTarifFragment.setArguments(bundle);

        adapter.addFragment(travelInfoFragment, "Informations");
        adapter.addFragment(itineraryFragment, "Itinéraire");
        adapter.addFragment(travelPlacesFragment, "Les escales");
        adapter.addFragment(travelTarifFragment, "Dates et tarifs");
        adapter.addFragment(travelBoatFragment, "Navire");
        adapter.addFragment(travelCabinsFragment, "Cabines");
        adapter.addFragment(travelGoodToKnowFragment, "Bon à savoir");

        mViewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
