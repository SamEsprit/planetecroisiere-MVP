package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.contracts.FeedFragmentContract;
import tn.leaderscodes.planetecroisiere.contracts.UserFeedFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.ApiMedia;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.request.PublicationRequest;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.remote.services.MediaService;
import tn.leaderscodes.planetecroisiere.remote.services.PublicationService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class UserFeedFragmentPresenter implements UserFeedFragmentContract.Presenter {

    private UserFeedFragmentContract.View view;
    private PublicationService publicationService;
    private MediaService mediaService;
    private CompositeDisposable mCompositeDisposable;

    public UserFeedFragmentPresenter(UserFeedFragmentContract.View view) {
        this.view = view;
        publicationService = Api.createRetrofitService(PublicationService.class);
        mediaService = ApiMedia.createRetrofitService(MediaService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void do_showFeedsList() {
        mCompositeDisposable.add(publicationService.userlist(Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleFeedsList, this::handleError));
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }

    private void handleFeedsList(List<Publication> publications) {
        view.showFeedsList(publications);
    }

    @Override
    public void do_addFeed(Publication publication) {
        mCompositeDisposable.add(publicationService.Add(new PublicationRequest(publication),Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleFeedAdded, this::handleError));
    }

    private void handleFeedAdded(Publication publication) {
        view.feedAdded(publication);
    }

    @Override
    public void do_uploadProfileImage(MultipartBody.Part file, RequestBody name) {
        mCompositeDisposable.add(mediaService.uploadFeedPicture(file, name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleImageUploaded, this::handleError));
    }

    private void handleImageUploaded(mediaResponse mediaResponse) {
        view.uploadImage(mediaResponse);
    }
}
