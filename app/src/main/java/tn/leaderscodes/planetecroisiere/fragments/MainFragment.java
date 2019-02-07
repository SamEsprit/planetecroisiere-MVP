package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import butterknife.BindView;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;


public class MainFragment extends BaseFragment {
    @BindView(R.id.space)
    private SpaceNavigationView spaceNavigationView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        spaceNavigationView = view.findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.baseline_home_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.baseline_business_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.baseline_place_24));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.baseline_person_24));
        spaceNavigationView.setCentreButtonIcon(R.drawable.baseline_search_24);
        spaceNavigationView.setCentreButtonColor(getResources().getColor(R.color.btnRequest));
        spaceNavigationView.showIconOnly();
        getChildFragmentManager().beginTransaction().replace(R.id.container, new FeedFragment()).commit();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                getChildFragmentManager().beginTransaction().replace(R.id.container, new TravelsFragment()).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch (itemIndex) {
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.container, new FeedFragment()).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.container, new CompaniesFragment()).commit();
                        break;
                    case 2:
                        getChildFragmentManager().beginTransaction().replace(R.id.container, new DestinationFragment()).commit();
                        break;
                    case 3:
                        getChildFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                        break;
                    default:
                        getChildFragmentManager().beginTransaction().replace(R.id.container, new CompaniesFragment()).commit();
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
