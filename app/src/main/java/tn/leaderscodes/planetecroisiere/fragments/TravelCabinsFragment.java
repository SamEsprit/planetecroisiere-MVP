package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.CabinsAdapter;
import tn.leaderscodes.planetecroisiere.adapters.PlaceAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.TravelCabinsFragmenContract;
import tn.leaderscodes.planetecroisiere.presenters.TravelCabinsFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.remote.request.BoatIdRequest;


public class TravelCabinsFragment extends BaseFragment implements TravelCabinsFragmenContract.View {


    private RecyclerView cabins_rv;
    private CabinsAdapter cabinsAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager;
    TravelCabinsFragmenContract.Presenter presenter;
    Travel travel;

    public TravelCabinsFragment() {
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
        return initializeView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUtils();

        presenter.do_showTypeCabineList(new BoatIdRequest(travel.getCruise().getBoat().get_id()));
    }

    public void onButtonPressed(Uri uri) {

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
    public void showTypeCabineList(List<TypeCabine> typeCabines) {

        cabins_rv.setHasFixedSize(true);
        cabinsAdapter.setmItems(typeCabines);
        cabins_rv.setAdapter(cabinsAdapter);
        cabins_rv.setLayoutManager(lineareLayoutManager);
    }

    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_travel_cabins, container, false);
        cabins_rv = view.findViewById(R.id.cabins_rv);
        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        cabinsAdapter = new CabinsAdapter(getActivity());
        presenter = new TravelCabinsFragmentPresenter(this);
        lineareLayoutManager= new LinearLayoutManager(getActivity());
    }
}
