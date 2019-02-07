package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.DestinationDetailsActivity;
import tn.leaderscodes.planetecroisiere.adapters.DestinationFragmentAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.DestinationFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.DestinationFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.response.SubRegionResponse;
import tn.leaderscodes.planetecroisiere.tools.SubRegionSectionHeader;


public class DestinationFragment extends BaseFragment implements DestinationFragmentContract.View {

    private RecyclerView.LayoutManager linearManager;
    private DestinationFragmentContract.Presenter presenter;

    public ShimmerRecyclerView destination_rv;
    private DestinationFragmentAdapter destinationFragmentAdapter;
    private SearchView searchView;

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
        presenter.do_showDestinationsList();
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
        View view = inflater.inflate(R.layout.fragment_destination, container, false);
        destination_rv = view.findViewById(R.id.destination_rv);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showDestinationsList(List<SubRegionResponse> subRegionResponses) {
        destination_rv.showShimmerAdapter();
        destination_rv.postDelayed(() -> {
            List<SubRegionSectionHeader> sections = new ArrayList<>();
            subRegionResponses.forEach(subRegionResponse -> {
                sections.add(new SubRegionSectionHeader(subRegionResponse.getSubRegionList(), subRegionResponse.getName()));
            });
            destinationFragmentAdapter = new DestinationFragmentAdapter(getActivity(), sections);

            destination_rv.setHasFixedSize(true);
            destination_rv.setLayoutManager(linearManager);
            destination_rv.setAdapter(destinationFragmentAdapter);
            destinationFragmentAdapter.setOnItemClickListener((view, obj, position) -> {
                Intent intent = new Intent(getActivity(), DestinationDetailsActivity.class);
                intent.putExtra("SubRegion", obj);
                startActivity(intent);
            });
            destination_rv.hideShimmerAdapter();
        }, 2000);

    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        linearManager = new LinearLayoutManager(getActivity());
        presenter = new DestinationFragmentPresenter(this);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_companies, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    destinationFragmentAdapter.getFilter().filter(s);
                } catch (Exception e) {
                }
                return true;
            }
        });
        // Detect SearchView icon clicks
        searchView.setOnSearchClickListener(v -> setItemsVisibility(menu, searchItem, false));

        // Detect SearchView close
        searchView.setOnCloseListener(() -> {
            setItemsVisibility(menu, searchItem, true);
            return false;
        });
        searchView.onActionViewCollapsed();
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
}
