package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.CompaniesFragmentContract;
import tn.leaderscodes.planetecroisiere.contracts.DestinationFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.response.SubRegionResponse;
import tn.leaderscodes.planetecroisiere.remote.services.CruiseLineService;
import tn.leaderscodes.planetecroisiere.remote.services.SubRegionService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class DestinationFragmentPresenter implements DestinationFragmentContract.Presenter
{
    private DestinationFragmentContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private SubRegionService subRegionService;

    public DestinationFragmentPresenter(DestinationFragmentContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        subRegionService = Api.createRetrofitService(SubRegionService.class);

    }
    @Override
    public void do_showDestinationsList() {
        mCompositeDisposable
                .add(subRegionService.listByGroupe(Session.getInstance().getToken())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleDestinationsList, this::handleError));
    }

    private void handleDestinationsList(List<SubRegionResponse> subRegionResponses) {
        view.showDestinationsList(subRegionResponses);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
