package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.contracts.ProfileFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.ApiMedia;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.remote.services.MediaService;
import tn.leaderscodes.planetecroisiere.remote.services.UserService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class ProfileFragmentPresenter implements ProfileFragmentContract.Presenter {


    private ProfileFragmentContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private UserService userService;
    private MediaService mediaService;

    public ProfileFragmentPresenter(ProfileFragmentContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        userService = Api.createRetrofitService(UserService.class);
        mediaService= ApiMedia.createRetrofitService(MediaService.class);
    }


    @Override
    public void do_populateUser() {
        mCompositeDisposable
                .add(userService.me(Session.getInstance().getToken())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handlePopulateUser, this::handleError));
    }

    @Override
    public void do_uploadProfileImage(MultipartBody.Part file, RequestBody name) {
        mCompositeDisposable.add(mediaService.uploadProfilePicture(file,name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlefileUplodaed, this::handleError));

    }

    @Override
    public void do_updateMyself(UserRequest userRequest) {
        mCompositeDisposable.add(userService.updateMyself(userRequest, Session.getInstance().getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleUpdateMyself, this::handleError));
    }

    private void handleUpdateMyself(UserResponse user) {
        view.updateMyself();
    }

    private void handlefileUplodaed(mediaResponse mediaResponse) {
        view.uploadProfileImage(mediaResponse);
    }


    private void handlePopulateUser(UserResponse user) {
        view.populateUser(user.getUser());
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
