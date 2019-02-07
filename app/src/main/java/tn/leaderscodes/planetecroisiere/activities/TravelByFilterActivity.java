package tn.leaderscodes.planetecroisiere.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.aakira.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.SearchableSpinnerListAdapter;
import tn.leaderscodes.planetecroisiere.adapters.TravelAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.TravelByFilterContract;
import tn.leaderscodes.planetecroisiere.presenters.TravelByFilterPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Place;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelSubRegionRequest;

public class TravelByFilterActivity extends BaseActivity implements TravelByFilterContract.View {

    private RecyclerView filtred_travel_rv;

    private TravelByFilterContract.Presenter presenter;

    private TravelAdapter travelAdapter;

    private SearchableSpinnerListAdapter compagnie_adapter, destination_adapter, place_adapter, boat_adapter;
    private SearchableSpinner cruiseLine_spn, destination_spn, place_spn, boat_spn;

    private ExpandableLayout main_expandableLayout;
    private RelativeLayout rltv_progressBar;

    private Button toggle;

    private Button search;

    private LinearLayoutManager lineareLayoutManager;
    TravelRequest travelRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        initializeToolBar();
        initializeUtils();
        travelRequest = (TravelRequest) getIntent().getSerializableExtra("Filter");
        presenter.do_showTravelsList(travelRequest);
        presenter.do_showDestinationsList();
        presenter.do_showCompagniesList();
        presenter.do_showPlacesList();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_travel_by_filter);

        filtred_travel_rv = findViewById(R.id.filtred_travel_rv);

        cruiseLine_spn = findViewById(R.id.cruiseLine_spn);
        destination_spn = findViewById(R.id.destination_spn);
        place_spn = findViewById(R.id.place_spn);
        boat_spn = findViewById(R.id.boat_spn);

        search = findViewById(R.id.search);

        main_expandableLayout = findViewById(R.id.main_expandableLayout);
        rltv_progressBar = findViewById(R.id.rltv_progressBar);

        toggle = findViewById(R.id.toggle);
        toggle.setOnClickListener(v -> {
            main_expandableLayout.toggle();
        });
        search.setOnClickListener(v -> {
            presenter.do_showTravelsList(travelRequest);
            main_expandableLayout.toggle();
        });
    }

    @Override
    public void initializeToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Planète Croisière");
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        travelAdapter = new TravelAdapter(getApplicationContext());
        presenter = new TravelByFilterPresenter(this);
        lineareLayoutManager = new LinearLayoutManager(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filters_travel, menu);
        menu.getItem(0).setIcon(getApplicationContext().getDrawable(R.drawable.ic_menu_filtre));
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTravelsList(List<Travel> travels) {
        if (travels.size() != 0) {
            filtred_travel_rv.setHasFixedSize(true);
            travelAdapter.setmItems(travels);
            filtred_travel_rv.setLayoutManager(lineareLayoutManager);
            filtred_travel_rv.setAdapter(travelAdapter);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showCompagniesList(List<CruiseLine> cruiseLines) {
        ArrayList<String> cruiseLinesSting = new ArrayList<>();
        CruiseLine cl;
        for (CruiseLine cruiseLine : cruiseLines) {
            cruiseLinesSting.add(cruiseLine.getName());
        }
        compagnie_adapter = new SearchableSpinnerListAdapter(getApplicationContext(), cruiseLinesSting);
        cruiseLine_spn.setAdapter(compagnie_adapter);

        if (!travelRequest.getCompany().equals("")) {
            cl = cruiseLines.stream().filter(a -> a.getCode().equals(travelRequest.getCompany())).findFirst().get();
            cruiseLine_spn.setSelectedItem(cruiseLinesSting.get(cruiseLinesSting.indexOf(cl.getName())));
            presenter.do_showBoatsByCompagnieList(new CompanyBoatRequest(cl.getCode()));
        }
        cruiseLine_spn.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                if (position != 0) {
                    presenter.do_showBoatsByCompagnieList(new CompanyBoatRequest(cruiseLines.get(position-1).getCode()));
                    travelRequest.setCompany(cruiseLines.get(position-1).getCode());
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showDestinationsList(List<SubRegion> subRegions) {
        ArrayList<String> subRegionsSting = new ArrayList<>();
        SubRegion sr;

        for (SubRegion subRegion : subRegions) {
            subRegionsSting.add(subRegion.getName());
        }
        destination_adapter = new SearchableSpinnerListAdapter(getApplicationContext(), subRegionsSting);
        destination_spn.setAdapter(destination_adapter);
        if (!travelRequest.getSubRegionRequest().getCode().equals("")) {
            sr = subRegions.stream().filter(a -> a.getCode().equals(travelRequest.getSubRegionRequest().getCode())).findFirst().get();
            destination_spn.setSelectedItem(subRegionsSting.get(subRegionsSting.indexOf(sr.getName())));
        }
        destination_spn.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                if (position-1 != 0) {
                    travelRequest.setSubRegionRequest(new TravelSubRegionRequest(subRegions.get(position-1).getCode(), subRegions.get(position-1).getSubRegionsCorps()));
                } else {
                    travelRequest.setSubRegionRequest(new TravelSubRegionRequest());
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    @Override
    public void showPlacesList(List<Place> places) {
        ArrayList<String> placesSting = new ArrayList<>();
        for (Place place : places) {
            placesSting.add(place.getName());
        }
        place_adapter = new SearchableSpinnerListAdapter(getApplicationContext(), placesSting);
        place_spn.setAdapter(place_adapter);

        place_spn.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                if (position-1 != 0) {
                    Log.d("TAG", "onItemSelected: "+placesSting.get(position-1)+" "+places.get(position-1).getName());
                    travelRequest.setStopOver(places.get(position-1).getCode());
                } else {
                    travelRequest.setStopOver("");
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @Override
    public void showBoatsByCompagnieList(List<Boat> boats) {
        ArrayList<String> boatString = new ArrayList<>();
        for (Boat boat : boats) {
            boatString.add(boat.getName());
        }
        boat_adapter = new SearchableSpinnerListAdapter(getApplicationContext(), boatString);
        boat_spn.setAdapter(boat_adapter);
        boat_spn.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                if (position-1 != 0) {
                    travelRequest.setBoat(boats.get(position-1).getCode());
                } else {
                    travelRequest.setBoat("");
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @Override
    public void showLoadMore(List<Travel> travels) {
        rltv_progressBar.setVisibility(View.VISIBLE);
        presenter.do_loadMore(travelRequest);
        rltv_progressBar.setVisibility(View.GONE);
    }

}
