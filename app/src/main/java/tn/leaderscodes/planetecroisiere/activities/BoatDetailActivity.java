package tn.leaderscodes.planetecroisiere.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.ActivityAdapter;
import tn.leaderscodes.planetecroisiere.adapters.BoatCompanyAdapter;
import tn.leaderscodes.planetecroisiere.adapters.TravelAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.BoatDetailContract;
import tn.leaderscodes.planetecroisiere.presenters.BoatDetailsPresenter;
import tn.leaderscodes.planetecroisiere.presenters.CompanyDetailActivityPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelSubRegionRequest;

public class BoatDetailActivity extends BaseActivity implements BoatDetailContract.View {

    SliderLayout sliderLayout;

    private TextView boat_name_dec, boat_name, boat_desc,boat_name_txt;
    private TextView boat_launchDate_txt, boat_passengerLength_txt, boat_width_length_txt, boat_speed_txt, boat_pont_txt, boat_tonage_txt;
    private TextView more, no_data;

    private RatingBar boat_star_rating;
    private BoatDetailContract.Presenter presenter;
    private TravelAdapter travelAdapter;
    private RecyclerView boat_travel_rv;
    private ActivityAdapter activityAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager,lineareLayoutManager2;
    private RecyclerView activities_rv;
    private Boat boat;
    private TravelRequest travelRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeToolBar();
        initializeUtils();
        showActivity();
        travelRequest = new TravelRequest();
        travelRequest.setBoat(boat.getCode());
        presenter.do_showTravelsList(travelRequest);

    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://s.krfb.de/library/original/aussenansicht.39vui13u.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("https://static2.cruiseline.eu/images/compagnies/gd/celebrity-cruises/celebrity-constellation/celebrity-constellation.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("https://static0.traveltek.net/images/www.cruisenation.com/v7/img/header-images/Celebrity%20Constellation.jpg");
                    break;
                case 3:
                    sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    @Override
    public void initializeToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
        boat_name_txt.setText(boat.getName());

    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_boat_detail);

        sliderLayout = findViewById(R.id.navire_slider);

        boat_name_dec = findViewById(R.id.boat_name_dec);
        boat_name = findViewById(R.id.boat_name);
        boat_desc = findViewById(R.id.boat_desc);
        boat_name_txt = findViewById(R.id.boat_name_txt);

        more = findViewById(R.id.more);
        no_data = findViewById(R.id.no_data);

        boat_launchDate_txt = findViewById(R.id.boat_launchDate_txt);
        boat_passengerLength_txt = findViewById(R.id.boat_passengerLength_txt);
        boat_width_length_txt = findViewById(R.id.boat_width_length_txt);
        boat_speed_txt = findViewById(R.id.boat_speed_txt);
        boat_pont_txt = findViewById(R.id.boat_pont_txt);
        boat_tonage_txt = findViewById(R.id.boat_tonnage_txt);
        boat_star_rating = findViewById(R.id.boat_star_rating);

        activities_rv = findViewById(R.id.activities_rv);
        boat_travel_rv = findViewById(R.id.boat_travel_rv);

        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout.setScrollTimeInSec(5);

        boat = (Boat) getIntent().getSerializableExtra("Boat");
        setSliderViews();

        boat_name.setText(boat.getName());
        boat_name_dec.setText(boat_name_dec.getText() + " " + boat.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            boat_desc.setText(Html.fromHtml(boat.getLongText(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            boat_desc.setText(Html.fromHtml(boat.getLongText()));
        }

        if (boat.getLaunchDate() != null)
            boat_launchDate_txt.setText(boat.getLaunchDate());
        else
            boat_launchDate_txt.setText("Non défini");
        if (boat.getPassengerLength() != null)
            boat_passengerLength_txt.setText(String.valueOf(boat.getPassengerLength()));
        else
            boat_passengerLength_txt.setText("Non défini");

        if (boat.getPont() != null)
            boat_pont_txt.setText(String.valueOf(boat.getPont()));
        else
            boat_pont_txt.setText("Non défini");

        boat_star_rating.setRating(boat.getStar());

        boat_width_length_txt.setText(boat.getLength() + " / " + boat.getWidth());

        boat_speed_txt.setText(String.valueOf(boat.getSpeed()));
        boat_tonage_txt.setText(String.valueOf(boat.getTonnage()) + " t");

        more.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),TravelByFilterActivity.class);
            intent.putExtra("Filter", travelRequest);
            startActivity(intent);
        });
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        lineareLayoutManager = new LinearLayoutManager(getApplicationContext());
        lineareLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        activityAdapter = new ActivityAdapter(getApplicationContext());
        travelAdapter = new TravelAdapter(getApplicationContext());
        presenter=new BoatDetailsPresenter(this);
    }

    @Override
    public void showActivity() {
        if (boat.getActiviteList() != null) {
            activities_rv.setHasFixedSize(true);
            activityAdapter.setmItems(boat.getActiviteList());
            activities_rv.setAdapter(activityAdapter);
            activities_rv.setLayoutManager(lineareLayoutManager2);
        }
    }

    @Override
    public void showTravelsList(List<Travel> travels) {
        if (travels.size() != 0) {
            no_data.setVisibility(View.GONE);
            boat_travel_rv.setHasFixedSize(true);
            travelAdapter.setmItems(travels);
            boat_travel_rv.setAdapter(travelAdapter);
            boat_travel_rv.setLayoutManager(lineareLayoutManager);
            more.setVisibility(View.VISIBLE);
        } else {
            no_data.setVisibility(View.VISIBLE);
            more.setVisibility(View.GONE);
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
