package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;

public class TravelGoodToKnowFragment extends BaseFragment {

    Travel travel;
    TextView good_to_know,price_info,price_include,price_not_include,formality_txt;

    public TravelGoodToKnowFragment() {
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
        setHasOptionsMenu(true);
        return initializeView(inflater,container);
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
        View view= inflater.inflate(R.layout.fragment_travel_good_to_know, container, false);
        good_to_know = view.findViewById(R.id.good_to_know);
        price_info = view.findViewById(R.id.price_info);
        price_include = view.findViewById(R.id.price_include);
        price_not_include = view.findViewById(R.id.price_not_include);
        formality_txt = view.findViewById(R.id.formality_txt);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            good_to_know.setText(Html.fromHtml(travel.getCruise().getInformation(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            good_to_know.setText(Html.fromHtml(travel.getCruise().getInformation()));
        }

        formality_txt.setText(travel.getCruise().getFormality());
        price_info.setText(travel.getCruise().getDetail());
        price_include.setText(travel.getCruise().getDetail_price_include());
        price_not_include.setText(travel.getCruise().getDetail_price_not_include());
        return view;
    }
}
