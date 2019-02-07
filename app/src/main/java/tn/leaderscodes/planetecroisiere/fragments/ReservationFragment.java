package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.CompanyDetailActivity;
import tn.leaderscodes.planetecroisiere.activities.DetailReservationActivity;
import tn.leaderscodes.planetecroisiere.activities.MainActivity;
import tn.leaderscodes.planetecroisiere.adapters.CompaniesFragmentAdapter;
import tn.leaderscodes.planetecroisiere.adapters.ReservationAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.CompaniesFragmentContract;
import tn.leaderscodes.planetecroisiere.contracts.ReservationFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.ReservationFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;


public class ReservationFragment extends BaseFragment implements ReservationFragmentContract.View {

    private RecyclerView.LayoutManager layoutManager;
    private ReservationFragmentContract.Presenter presenter;

    public ShimmerRecyclerView reservation_rv;
    private ReservationAdapter reservationAdapter;

    public ReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        presenter.do_showReservationDate();
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
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        reservation_rv = view.findViewById(R.id.reservation_rv);
        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new ReservationFragmentPresenter(this);
        layoutManager = new LinearLayoutManager(getActivity());
        reservationAdapter = new ReservationAdapter(getActivity());
    }

    @Override
    public void showReservationDate(List<Reservation> reservations) {
        reservation_rv.showShimmerAdapter();

        reservation_rv.postDelayed(() -> {
            reservation_rv.setHasFixedSize(true);
            reservationAdapter.setmItems(reservations);
            reservation_rv.setAdapter(reservationAdapter);
            reservation_rv.setLayoutManager(layoutManager);
            reservation_rv.hideShimmerAdapter();
            reservationAdapter.setOnItemClickListener((view, obj, position) -> {
                Intent intent=new Intent(getActivity(), DetailReservationActivity.class);
                intent.putExtra("reservation",obj);
                startActivity(intent);
            });
        }, 1000);
    }
}
