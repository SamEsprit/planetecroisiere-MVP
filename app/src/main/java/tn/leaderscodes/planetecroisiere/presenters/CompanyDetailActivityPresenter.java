package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.CompaniesFragmentContract;
import tn.leaderscodes.planetecroisiere.contracts.CompanyDetailActivityContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.services.BoatService;
import tn.leaderscodes.planetecroisiere.remote.services.CruiseLineService;
import tn.leaderscodes.planetecroisiere.remote.services.SubRegionService;
import tn.leaderscodes.planetecroisiere.remote.services.TravelService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class CompanyDetailActivityPresenter implements CompanyDetailActivityContract.Presenter {
    private CompanyDetailActivityContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private BoatService boatService;
    private TravelService travelService;
    private SubRegionService subRegionService;

    CompanyBoatRequest companyBoatRequest;

    public CompanyDetailActivityPresenter(CompanyDetailActivityContract.View view, String companyCode) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        boatService = Api.createRetrofitService(BoatService.class);
        travelService = Api.createRetrofitService(TravelService.class);
        subRegionService = Api.createRetrofitService(SubRegionService.class);
        companyBoatRequest = new CompanyBoatRequest(companyCode);


    }

    @Override
    public void do_showDestinationsList() {
        mCompositeDisposable.add(subRegionService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleSubRegionsList, this::handleError));
    }

    @Override
    public void do_showBoatsList() {
        mCompositeDisposable.add(boatService.listByCompany(companyBoatRequest,Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleBoatsList, this::handleError));
    }

    @Override
    public void do_showTravelsList(TravelRequest travelRequest) {
        mCompositeDisposable.add(travelService.mlistByConditionLimit5(travelRequest,Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTravelsList, this::handleError));
    }


    private void handleTravelsList(List<Travel> travels) {
        view.showTravelsList(travels);
    }

    private void handleBoatsList(List<Boat> boats) {
        view.showBoatsList(boats);
    }

    private void handleSubRegionsList(List<SubRegion> subRegions) {
        view.showDestinationsList(subRegions);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
