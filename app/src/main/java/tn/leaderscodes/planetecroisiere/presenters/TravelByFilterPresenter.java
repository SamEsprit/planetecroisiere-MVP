package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.TravelByFilterContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Place;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.services.BoatService;
import tn.leaderscodes.planetecroisiere.remote.services.CruiseLineService;
import tn.leaderscodes.planetecroisiere.remote.services.PlaceService;
import tn.leaderscodes.planetecroisiere.remote.services.SubRegionService;
import tn.leaderscodes.planetecroisiere.remote.services.TravelService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class TravelByFilterPresenter implements TravelByFilterContract.Presenter {

    private TravelByFilterContract.View view;
    private TravelService travelService;
    private CruiseLineService cruiseLineService;
    private BoatService boatService;
    private SubRegionService subRegionService;
    private PlaceService placeService;
    private CompositeDisposable mCompositeDisposable;

    public TravelByFilterPresenter(TravelByFilterContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        travelService = Api.createRetrofitService(TravelService.class);
        cruiseLineService = Api.createRetrofitService(CruiseLineService.class);
        boatService = Api.createRetrofitService(BoatService.class);
        subRegionService = Api.createRetrofitService(SubRegionService.class);
        placeService = Api.createRetrofitService(PlaceService.class);
    }

    @Override
    public void do_showCompagniesList() {
        mCompositeDisposable.add(cruiseLineService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCruiseLineList, this::handleError));
    }

    @Override
    public void do_showDestinationsList() {
        mCompositeDisposable.add(subRegionService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleSubRegionsList, this::handleError));
    }

    @Override
    public void do_showPlacesList() {
        mCompositeDisposable.add(placeService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlePlacesList, this::handleError));
    }

    private void handlePlacesList(List<Place> places) {
        view.showPlacesList(places);
    }

    @Override
    public void do_showBoatsByCompagnieList(CompanyBoatRequest companyBoatRequest) {
        mCompositeDisposable.add(boatService.listByCompany(companyBoatRequest,Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleBoatsList, this::handleError));
    }

    @Override
    public void do_showTravelsList(TravelRequest travelRequest) {
        mCompositeDisposable.add(travelService.mlistByFilter(travelRequest,Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTravelsList, this::handleError));
    }

    @Override
    public void do_loadMore(TravelRequest travelRequest) {
        mCompositeDisposable.add(travelService.mlistByFilter(travelRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleloadMoreList, this::handleError));
    }

    private void handleTravelsList(List<Travel> travels) {
        view.showTravelsList(travels);
    }

    private void handleloadMoreList(List<Travel> travels) {
        view.showLoadMore(travels);
    }

    private void handleBoatsList(List<Boat> boats) {
        view.showBoatsByCompagnieList(boats);
    }

    private void handleSubRegionsList(List<SubRegion> subRegions) {
        view.showDestinationsList(subRegions);
    }

    private void handleCruiseLineList(List<CruiseLine> cruiseLines) {
        view.showCompagniesList(cruiseLines);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
