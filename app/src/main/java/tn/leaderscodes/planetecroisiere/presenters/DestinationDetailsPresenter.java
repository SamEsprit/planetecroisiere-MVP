package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.BoatDetailContract;
import tn.leaderscodes.planetecroisiere.contracts.DestinationDetailActivityContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.services.CruiseLineService;
import tn.leaderscodes.planetecroisiere.remote.services.TravelService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class DestinationDetailsPresenter implements DestinationDetailActivityContract.Presenter {
    private DestinationDetailActivityContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private TravelService travelService;
    private CruiseLineService cruiseLineService;

    public DestinationDetailsPresenter(DestinationDetailActivityContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        travelService = Api.createRetrofitService(TravelService.class);
        cruiseLineService = Api.createRetrofitService(CruiseLineService.class);
    }

    @Override
    public void do_showTravelsList(TravelRequest travelRequest) {
        mCompositeDisposable.add(travelService.mlistByConditionLimit5(travelRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTravelsList, this::handleError));
    }

    @Override
    public void do_showCruiseLinesList() {
        mCompositeDisposable.add(cruiseLineService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleCruiseLineList, this::handleError));
    }

    private void handleCruiseLineList(List<CruiseLine> cruiseLines) {
        view.showCruiseLinesList(cruiseLines);
    }


    private void handleTravelsList(List<Travel> travels) {
        view.showTravelsList(travels);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
