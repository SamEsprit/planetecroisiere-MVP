package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.github.aakira.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.CompanyDetailActivity;
import tn.leaderscodes.planetecroisiere.activities.MainActivity;
import tn.leaderscodes.planetecroisiere.adapters.SearchableSpinnerListAdapter;
import tn.leaderscodes.planetecroisiere.adapters.TravelAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
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


public class TravelsFragment extends BaseFragment implements TravelByFilterContract.View {


    private ShimmerRecyclerView filtred_travel_rv;
    private TravelByFilterContract.Presenter presenter;
    private TravelAdapter travelAdapter;
    private LinearLayoutManager lineareLayoutManager;
    TravelRequest travelRequest = new TravelRequest();

    private SearchableSpinnerListAdapter compagnie_adapter, destination_adapter, place_adapter, boat_adapter;
    private SearchableSpinner cruiseLine_spn, destination_spn, place_spn, boat_spn;

    private ExpandableLayout main_expandableLayout;
    private Button toggle;
    private Button search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return initializeView(inflater, container);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUtils();
        presenter.do_showTravelsList(travelRequest);
        presenter.do_showDestinationsList();
        presenter.do_showCompagniesList();
        presenter.do_showPlacesList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_travels, container, false);
        filtred_travel_rv = view.findViewById(R.id.filtred_travel_rv);

        cruiseLine_spn = view.findViewById(R.id.cruiseLine_spn);
        destination_spn = view.findViewById(R.id.destination_spn);
        place_spn = view.findViewById(R.id.place_spn);
        boat_spn = view.findViewById(R.id.boat_spn);

        search = view.findViewById(R.id.search);

        main_expandableLayout = view.findViewById(R.id.main_expandableLayout);

        toggle = view.findViewById(R.id.toggle);
        toggle.setOnClickListener(v -> {
            main_expandableLayout.toggle();
        });
        search.setOnClickListener(v -> {
            presenter.do_showTravelsList(travelRequest);
            main_expandableLayout.toggle();
        });
        return view;
    }


    @Override
    public void initializeUtils() {
        super.initializeUtils();
        travelAdapter = new TravelAdapter(getActivity());
        presenter = new TravelByFilterPresenter(this);
        lineareLayoutManager = new LinearLayoutManager(getActivity());
    }
    @Override
    public void showTravelsList(List<Travel> travels) {
        if (travels.size() != 0) {
            filtred_travel_rv.showShimmerAdapter();

            filtred_travel_rv.postDelayed(() -> {
                filtred_travel_rv.setHasFixedSize(true);
                travelAdapter.setmItems(travels);
                filtred_travel_rv.setLayoutManager(lineareLayoutManager);
                filtred_travel_rv.setAdapter(travelAdapter);
                filtred_travel_rv.hideShimmerAdapter();
            }, 3000);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showCompagniesList(List<CruiseLine> cruiseLines) {
        ArrayList<String> cruiseLinesSting = new ArrayList<>();
        for (CruiseLine cruiseLine : cruiseLines) {
            cruiseLinesSting.add(cruiseLine.getName());
        }
        compagnie_adapter = new SearchableSpinnerListAdapter(getActivity(), cruiseLinesSting);
        cruiseLine_spn.setAdapter(compagnie_adapter);

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


        for (SubRegion subRegion : subRegions) {
            subRegionsSting.add(subRegion.getName());
        }
        destination_adapter = new SearchableSpinnerListAdapter(getActivity(), subRegionsSting);
        destination_spn.setAdapter(destination_adapter);
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
        place_adapter = new SearchableSpinnerListAdapter(getActivity(), placesSting);
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
        boat_adapter = new SearchableSpinnerListAdapter(getActivity(), boatString);
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

        presenter.do_loadMore(travelRequest);

    }
}
