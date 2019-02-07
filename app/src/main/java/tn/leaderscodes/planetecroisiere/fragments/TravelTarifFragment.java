package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.TravelTarifFragmentAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.TravelCabinsFragmenContract;
import tn.leaderscodes.planetecroisiere.contracts.TravelTarifFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.TravelCabinsFragmentPresenter;
import tn.leaderscodes.planetecroisiere.presenters.TravelTarifFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Price;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.ReservationRequest;
import tn.leaderscodes.planetecroisiere.tools.TravelTarifSectionHeader;


public class TravelTarifFragment extends BaseFragment implements TravelTarifFragmentContract.View {

    private RecyclerView traif_rv;
    private Travel travel;
    private RecyclerView.LayoutManager linearManager;
    private TravelTarifFragmentAdapter travelTarifFragmentAdapter;
    private Spinner dateSpn;
    private ImageButton next_date, previous_date;
    private static Integer position = 0;
    private TravelTarifFragmentContract.Presenter presenter;

    ReservationRequest reservationRequest;

    public TravelTarifFragment() {
        presenter= new TravelTarifFragmentPresenter(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        travel = (Travel) getArguments().get("Travel");
        reservationRequest= new ReservationRequest();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return initializeView(inflater, container);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearManager = new LinearLayoutManager(getActivity());
        showTravelTarif(position);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_travel_tarif, container, false);
        traif_rv = view.findViewById(R.id.traif_rv);
        dateSpn = view.findViewById(R.id.dateSpn);
        next_date = view.findViewById(R.id.next_date);
        previous_date = view.findViewById(R.id.previous_date);
        if (position == 0)
            previous_date.setClickable(false);
        if (position == travel.getDeparturesDates().size() - 1)
            next_date.setClickable(false);
        next_date.setOnClickListener(v -> {
            position++;
            dateSpn.setSelection(position);
        });
        previous_date.setOnClickListener(v -> {
            position--;
            dateSpn.setSelection(position);
        });
        List<String> departuresDates = new ArrayList<>();
        travel.getDeparturesDates().forEach(departuresDates1 -> {
            departuresDates.add(departuresDates1.getDate());
        });
        ArrayAdapter dateSpn_ArrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, departuresDates);
        dateSpn.setAdapter(dateSpn_ArrayAdapter);
        dateSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TravelTarifFragment.position = position;
                showTravelTarif(position);
                reservationRequest.setDate(departuresDates.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showTravelTarif(Integer position) {
        List<TravelTarifSectionHeader> sections = new ArrayList<>();
        travel.getDeparturesDates().get(position).getPricingList().forEach(pricing -> {
            switch (pricing.getCategory()) {
                case "I":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Interieur"));
                    break;
                case "B":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Balcon"));
                    break;
                case "S":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Suite"));
                    break;
                case "I/S":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Interieur Suite"));
                    break;
                case "O/S":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Suite Vue sur l'océan"));
                    break;
                case "O":
                    sections.add(new TravelTarifSectionHeader(pricing.getPricingList(), "Cabine Vue sur l'océan"));
                    break;
            }

        });
        travelTarifFragmentAdapter = new TravelTarifFragmentAdapter(getActivity(), sections);

        traif_rv.setHasFixedSize(true);
        traif_rv.setLayoutManager(linearManager);
        traif_rv.setAdapter(travelTarifFragmentAdapter);
        travelTarifFragmentAdapter.setOnItemClickListener(new TravelTarifFragmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Price obj, int position) {
                reservationRequest.setCabineCategorie(obj.getCategory().getCode()+"-"+obj.getCategory().getDescription());
                reservationRequest.setPrice(obj.getPrice());
                reservationRequest.setTravel_id(travel.get_id());
                View viewReservation = View.inflate(getActivity(), R.layout.reservation_content, null);
                TextView cabine_price=viewReservation.findViewById(R.id.cabine_price);
                cabine_price.setText(obj.getCategory().getCode()+"-"+obj.getCategory().getDescription() +" \n"+obj.getPrice()+" €");
                TextView date=viewReservation.findViewById(R.id.date);
                date.setText(reservationRequest.getDate());
                EditText adulte=viewReservation.findViewById(R.id.adulte);
                adulte.setText("2");
                EditText child=viewReservation.findViewById(R.id.child);
                child.setText("0");
                EditText comment=viewReservation.findViewById(R.id.comment);
                comment.setText("");
                new MaterialStyledDialog.Builder(getActivity())
                        .setCustomView(viewReservation)
                        .setCancelable(true)
                        .setPositiveText("Réserver")
                        .onPositive((dialog, which) -> {
                            reservationRequest.setAdulte(Integer.parseInt(adulte.getText().toString()));
                            reservationRequest.setChild(Integer.parseInt(child.getText().toString()));
                            reservationRequest.setContenu(comment.getText().toString());
                            presenter.do_addReservation(reservationRequest);
                            dialog.dismiss();
                        })
                        .show();
            };
        });
    }

    @Override
    public void reservationAdded() {
        Toast.makeText(getActivity(),"Réservation Enregistré",Toast.LENGTH_SHORT).show();
    }
}
