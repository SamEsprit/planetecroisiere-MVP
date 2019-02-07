package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.PlaceAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;

public class TravelPlacesFragment extends BaseFragment {

    Travel travel;
    RecyclerView  stopOvers_rv;
    private PlaceAdapter placeAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager;
    public TravelPlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        travel = (Travel) getArguments().get("Travel");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return initializeView(inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUtils();
        showStopOvers();
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
    public void initializeUtils() {
        super.initializeUtils();
        placeAdapter= new PlaceAdapter(getActivity());
        lineareLayoutManager= new LinearLayoutManager(getActivity());
    }

    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_travel_places, container, false);
        stopOvers_rv=view.findViewById(R.id.stopOvers_rv);
        return view;
    }

    public void showStopOvers() {
        stopOvers_rv.setHasFixedSize(true);
        placeAdapter.setmItems(travel.getCruise().getItinerary().getStopOvers());
        stopOvers_rv.setAdapter(placeAdapter);
        stopOvers_rv.setLayoutManager(lineareLayoutManager);
    }
}
