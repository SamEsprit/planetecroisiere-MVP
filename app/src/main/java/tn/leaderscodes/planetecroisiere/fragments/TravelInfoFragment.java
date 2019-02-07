package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.AdvantageAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.tools.AdvantagesData;

public class TravelInfoFragment extends BaseFragment {

    private TextView company_name_txt, boat_name_txt, destination_name_txt, stopover_name_txt, depart_name_txt;
    private Travel travel;
    private RecyclerView advantage_rv;
    private AdvantageAdapter advantageAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager;

    public TravelInfoFragment() {
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
        setHasOptionsMenu(true);
        return initializeView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUtils();
        showAdvantages();
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
        View view = inflater.inflate(R.layout.fragment_travel_info, container, false);
        company_name_txt = view.findViewById(R.id.company_name_txt);
        boat_name_txt = view.findViewById(R.id.boat_name_txt);
        destination_name_txt = view.findViewById(R.id.destination_name_txt);
        stopover_name_txt = view.findViewById(R.id.stopover_name_txt);
        depart_name_txt = view.findViewById(R.id.depart_name_txt);

        advantage_rv = view.findViewById(R.id.advantage_rv);



        company_name_txt.setText(travel.getCruise().getCruiseLine().getName());
        boat_name_txt.setText(travel.getCruise().getBoat().getName());
        if (travel.getCruise().getGeo().getSubRegion() != null)
            destination_name_txt.setText(travel.getCruise().getGeo().getSubRegion().getName());
        else
            destination_name_txt.setText(travel.getCruise().getGeo().getName());

        stopover_name_txt.setText(travel.getCruise().getPlace().getName());
        depart_name_txt.setText(travel.getCruise().getCruiseLength() + " jours / " + (travel.getCruise().getCruiseLength() - 1) + " nuits  ");
        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        advantageAdapter = new AdvantageAdapter(getActivity());
        lineareLayoutManager = new LinearLayoutManager(getActivity());
    }

    public void showAdvantages() {

        advantage_rv.setHasFixedSize(true);
        advantageAdapter.setmItems(AdvantagesData.getRandomAdvantage());
        advantage_rv.setAdapter(advantageAdapter);
        advantage_rv.setLayoutManager(lineareLayoutManager);
    }
}
