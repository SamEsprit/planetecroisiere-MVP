package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.BoatDetailContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;
import tn.leaderscodes.planetecroisiere.remote.services.TravelService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class BoatDetailsPresenter implements BoatDetailContract.Presenter {
    private BoatDetailContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private TravelService travelService;

    public BoatDetailsPresenter(BoatDetailContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        travelService = Api.createRetrofitService(TravelService.class);
    }

    @Override
    public void do_showTravelsList(TravelRequest travelRequest) {
        mCompositeDisposable.add(travelService.mlistByConditionLimit5(travelRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTravelsList, this::handleError));
    }


    private void handleTravelsList(List<Travel> travels) {
        view.showTravelsList(travels);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
