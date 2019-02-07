package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.ReservationFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;
import tn.leaderscodes.planetecroisiere.remote.services.ReservationService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class ReservationFragmentPresenter implements ReservationFragmentContract.Presenter {

    private ReservationFragmentContract.View view;
    private ReservationService reservationService;
    private CompositeDisposable mCompositeDisposable;

    public ReservationFragmentPresenter(ReservationFragmentContract.View view) {
        this.view = view;
        reservationService= Api.createRetrofitService(ReservationService.class);
        mCompositeDisposable= new CompositeDisposable();
    }

    @Override
    public void do_showReservationDate() {
        mCompositeDisposable.add(reservationService.list(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleReservationList, this::handleError));
    }

    private void handleReservationList(List<Reservation> reservations) {
        view.showReservationDate(reservations);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
