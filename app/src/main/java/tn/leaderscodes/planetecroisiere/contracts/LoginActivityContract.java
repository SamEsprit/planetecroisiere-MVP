package tn.leaderscodes.planetecroisiere.contracts;


import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;

public class LoginActivityContract {
    public interface View {
        void LoginSucces(String s);
        void LoginFail(String s);
        void RegisterFail(String s);
        void RegisterSucces(String s);
        void goToMainActivity();
        void setSignUpLayout();
        void setSignInLayout();
        boolean validateRegister();
        boolean validateLogin();
        UserRequest createUserRegisterRequest();
        UserRequest createUserLoginRequest();
        void clearRegisterEditText();
    }

    public interface Presenter {
        void do_login();
        void do_register();
    }
}
