package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.TravelTarifFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;
import tn.leaderscodes.planetecroisiere.remote.request.ReservationRequest;
import tn.leaderscodes.planetecroisiere.remote.services.ReservationService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class TravelTarifFragmentPresenter implements TravelTarifFragmentContract.Presenter {
    private TravelTarifFragmentContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private ReservationService reservationService;

    public TravelTarifFragmentPresenter(TravelTarifFragmentContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        reservationService = Api.createRetrofitService(ReservationService.class);
    }


    @Override
    public void do_addReservation(ReservationRequest reservationRequest) {
        mCompositeDisposable.add(reservationService.addReservation(reservationRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleReservationAdded, this::handleError));
    }

    private void handleReservationAdded(Reservation reservation) {
        view.reservationAdded();
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }

}
