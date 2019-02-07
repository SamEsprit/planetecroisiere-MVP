package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.ActivityAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;


public class TravelBoatFragment extends BaseFragment {

    private TextView boat_name_dec, boat_name, boat_desc,boat_name_txt;
    private TextView boat_launchDate_txt, boat_passengerLength_txt, boat_width_length_txt, boat_speed_txt, boat_pont_txt, boat_tonage_txt;
    private RatingBar boat_star_rating;
    private SliderLayout sliderLayout;
    private ActivityAdapter activityAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager;
    private RecyclerView activities_rv;
    Travel travel;
    public TravelBoatFragment() {
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
        setSliderViews();
        boat_name.setText(travel.getCruise().getBoat().getName());
        boat_name_dec.setText(boat_name_dec.getText() + " " + travel.getCruise().getBoat().getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            boat_desc.setText(Html.fromHtml(travel.getCruise().getBoat().getLongText(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            boat_desc.setText(Html.fromHtml(travel.getCruise().getBoat().getLongText()));
        }

        if (travel.getCruise().getBoat().getLaunchDate() != null)
            boat_launchDate_txt.setText(travel.getCruise().getBoat().getLaunchDate());
        else
            boat_launchDate_txt.setText("Non défini");
        if (travel.getCruise().getBoat().getPassengerLength() != null)
            boat_passengerLength_txt.setText(String.valueOf(travel.getCruise().getBoat().getPassengerLength()));
        else
            boat_passengerLength_txt.setText("Non défini");

        if (travel.getCruise().getBoat().getPont() != null)
            boat_pont_txt.setText(String.valueOf(travel.getCruise().getBoat().getPont()));
        else
            boat_pont_txt.setText("Non défini");

        boat_star_rating.setRating(travel.getCruise().getBoat().getStar());

        boat_width_length_txt.setText(travel.getCruise().getBoat().getLength() + " / " + travel.getCruise().getBoat().getWidth());
        boat_speed_txt.setText(String.valueOf(travel.getCruise().getBoat().getSpeed()));
        boat_tonage_txt.setText(String.valueOf(travel.getCruise().getBoat().getTonnage()) + " t");
        showActivity();
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

    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_travel_boat, container, false);
        sliderLayout = view.findViewById(R.id.navire_slider);

        boat_name_dec = view.findViewById(R.id.boat_name_dec);
        boat_name = view.findViewById(R.id.boat_name);
        boat_desc = view.findViewById(R.id.boat_desc);
        boat_name_txt = view.findViewById(R.id.boat_name_txt);


        boat_launchDate_txt = view.findViewById(R.id.boat_launchDate_txt);
        boat_passengerLength_txt = view.findViewById(R.id.boat_passengerLength_txt);
        boat_width_length_txt = view.findViewById(R.id.boat_width_length_txt);
        boat_speed_txt = view.findViewById(R.id.boat_speed_txt);
        boat_pont_txt = view.findViewById(R.id.boat_pont_txt);
        boat_tonage_txt = view.findViewById(R.id.boat_tonnage_txt);
        boat_star_rating = view.findViewById(R.id.boat_star_rating);

        activities_rv = view.findViewById(R.id.activities_rv);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout.setScrollTimeInSec(5);
        return view;
    }

    public void showActivity() {
        if (travel.getCruise().getBoat().getActiviteList() != null) {
            activities_rv.setHasFixedSize(true);
            activityAdapter.setmItems(travel.getCruise().getBoat().getActiviteList());
            activities_rv.setAdapter(activityAdapter);
            activities_rv.setLayoutManager(lineareLayoutManager);
        }
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        activityAdapter= new ActivityAdapter(getActivity());
        lineareLayoutManager= new LinearLayoutManager(getActivity());
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 1:
                    sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 2:
                    sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
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
}
