package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.CompaniesFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.services.CruiseLineService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class CompaniesFragmentPresenter implements CompaniesFragmentContract.Presenter
{
    private CompaniesFragmentContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private CruiseLineService cruiseLineService;

    public CompaniesFragmentPresenter(CompaniesFragmentContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        cruiseLineService = Api.createRetrofitService(CruiseLineService.class);

    }
    @Override
    public void do_showCruisesLinesList() {
        Log.d("token", Session.getInstance().getToken());
        mCompositeDisposable
                .add(cruiseLineService.list(Session.getInstance().getToken())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleCruiseLineList, this::handleError));
    }

    private void handleCruiseLineList(List<CruiseLine> cruisesLines) {

        view.showCruisesLinesList(cruisesLines);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
