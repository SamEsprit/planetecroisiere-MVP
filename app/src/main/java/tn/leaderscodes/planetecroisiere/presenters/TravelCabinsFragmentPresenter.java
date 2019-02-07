package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.BoatDetailContract;
import tn.leaderscodes.planetecroisiere.contracts.TravelCabinsFragmenContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.remote.request.BoatIdRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.services.BoatService;
import tn.leaderscodes.planetecroisiere.remote.services.TravelService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class TravelCabinsFragmentPresenter implements TravelCabinsFragmenContract.Presenter {
    private TravelCabinsFragmenContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private BoatService boatService;

    public TravelCabinsFragmentPresenter(TravelCabinsFragmenContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        boatService = Api.createRetrofitService(BoatService.class);
    }

    @Override
    public void do_showTypeCabineList(BoatIdRequest boatIdRequest) {
        mCompositeDisposable.add(boatService.listTypeCabinsByBoat(boatIdRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTravelsList, this::handleError));
    }


    private void handleTravelsList(List<TypeCabine> typeCabines) {
        view.showTypeCabineList(typeCabines);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }


}
