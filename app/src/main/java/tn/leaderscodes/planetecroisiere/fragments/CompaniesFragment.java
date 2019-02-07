package tn.leaderscodes.planetecroisiere.fragments;

import android.content.Context;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.List;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.CompanyDetailActivity;
import tn.leaderscodes.planetecroisiere.activities.MainActivity;
import tn.leaderscodes.planetecroisiere.adapters.CompaniesFragmentAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.CompaniesFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.CompaniesFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;


public class CompaniesFragment extends BaseFragment implements CompaniesFragmentContract.View {


    private RecyclerView.LayoutManager gridLayoutManager;
    private CompaniesFragmentContract.Presenter presenter;

    public ShimmerRecyclerView companies_rv;
    private CompaniesFragmentAdapter companiesFragmentAdapter;
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
        presenter.do_showCruisesLinesList();
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
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        companies_rv = view.findViewById(R.id.companies_rv);
        return view;
    }

    @Override
    public void showCruisesLinesList(List<CruiseLine> cruisesLines) {
        companies_rv.showShimmerAdapter();

        companies_rv.postDelayed(() -> {
            companies_rv.setHasFixedSize(true);
            companiesFragmentAdapter.setmItems(cruisesLines);
            companies_rv.setAdapter(companiesFragmentAdapter);
            companies_rv.setLayoutManager(gridLayoutManager);
            companiesFragmentAdapter.setOnItemClickListener((view, obj, position) -> {
                CompanyDetailActivity.navigate((MainActivity)getActivity(), view.findViewById(R.id.company_img), obj);
            });
            companies_rv.hideShimmerAdapter();
        }, 2000);
    }


    @Override
    public void initializeUtils() {
        super.initializeUtils();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        presenter = new CompaniesFragmentPresenter(this);
        companiesFragmentAdapter = new CompaniesFragmentAdapter(getActivity());
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
                    companiesFragmentAdapter.getFilter().filter(s);
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
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }
}
