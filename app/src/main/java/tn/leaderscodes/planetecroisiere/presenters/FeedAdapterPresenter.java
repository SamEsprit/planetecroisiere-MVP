package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.FeedAdapterContract;
import tn.leaderscodes.planetecroisiere.contracts.FeedFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.services.PublicationService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class FeedAdapterPresenter implements FeedAdapterContract.Presenter {

    private FeedAdapterContract.View view;
    private PublicationService publicationService;
    private CompositeDisposable mCompositeDisposable;

    public FeedAdapterPresenter(FeedAdapterContract.View view) {
        this.view = view;
        publicationService = Api.createRetrofitService(PublicationService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void do_Like(String id) {
        mCompositeDisposable.add(publicationService.like(Session.getInstance().getToken(),id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleLike, this::handleError));
    }

    @Override
    public void do_dislike(String id) {
        mCompositeDisposable.add(publicationService.dislike(Session.getInstance().getToken(),id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleDisLike, this::handleError));
    }

    private void handleDisLike(Publication publication) {
        view.dislike(publication);
    }

    private void handleLike(Publication publication) {
        view.like(publication);
    }
    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
