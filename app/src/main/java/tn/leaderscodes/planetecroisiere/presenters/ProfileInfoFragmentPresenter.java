package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.contracts.MainActivityContract;
import tn.leaderscodes.planetecroisiere.contracts.ProfileInfoFragmentContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.ApiMedia;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.remote.services.MediaService;
import tn.leaderscodes.planetecroisiere.remote.services.UserService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class ProfileInfoFragmentPresenter implements ProfileInfoFragmentContract.Presenter {

    private ProfileInfoFragmentContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private MediaService mediaService;
    private UserService userService;

    public ProfileInfoFragmentPresenter(ProfileInfoFragmentContract.View view) {
        this.view = view;
        this.mCompositeDisposable = new CompositeDisposable();
        mediaService= ApiMedia.createRetrofitService(MediaService.class);
        userService= Api.createRetrofitService(UserService.class);
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
        view.updateMyself(user);
    }

    private void handlefileUplodaed(mediaResponse mediaResponse) {
        view.uploadProfileImage(mediaResponse);
    }

    private void handleError(Throwable throwable) {
        Log.d("errorA", throwable.getLocalizedMessage());
    }
}
