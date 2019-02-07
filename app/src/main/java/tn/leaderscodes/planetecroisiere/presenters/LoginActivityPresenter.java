package tn.leaderscodes.planetecroisiere.presenters;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tn.leaderscodes.planetecroisiere.contracts.LoginActivityContract;
import tn.leaderscodes.planetecroisiere.remote.Api;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;
import tn.leaderscodes.planetecroisiere.remote.services.UserService;
import tn.leaderscodes.planetecroisiere.tools.Session;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {


    private LoginActivityContract.View view;
    private CompositeDisposable mCompositeDisposable;
    private UserService userService;

    public LoginActivityPresenter(LoginActivityContract.View view) {
        this.view = view;
        mCompositeDisposable= new CompositeDisposable();
        userService= Api.createRetrofitService(UserService.class);
    }

    @Override
    public void do_login() {
        if (view.validateLogin()){
            mCompositeDisposable
                    .add(userService.login(view.createUserLoginRequest())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::handleLogin, this::handleLoginError));
        }else return;
    }

    @Override
    public void do_register() {
        if (view.validateRegister()){
            mCompositeDisposable
                    .add(userService.register(view.createUserRegisterRequest())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::handleRegister, this::handleRegisterError));


        }else return;
    }
    private void handleLogin(UserResponse user)  {
        Session session= Session.getInstance();
        session.setid(user.getUser().get_id());
        session.setusename(user.getUser().getUsername());
        session.setLogin(true);
        session.setImage(user.getUser().getImage());
        session.setToken(user.getUser().getToken());
        session.setEmail(user.getUser().getEmail());
        view.LoginSucces("");

    }
    private void handleLoginError(Throwable throwable) throws Exception {
        HttpException error = (HttpException)throwable;
        String errorBody = error.response().errorBody().string();
        view.LoginFail(errorBody);
    }
    private void handleRegisterError(Throwable throwable) throws Exception
    {
        HttpException error = (HttpException)throwable;
        String errorBody = error.response().errorBody().string();
        view.RegisterFail(errorBody);
    }
    private void handleRegister(User user) {

        view.clearRegisterEditText();
        view.setSignInLayout();

    }
}
