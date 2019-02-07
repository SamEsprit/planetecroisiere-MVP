package tn.leaderscodes.planetecroisiere.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.LoginActivityContract;
import tn.leaderscodes.planetecroisiere.presenters.LoginActivityPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;

public class LoginActivity extends BaseActivity implements LoginActivityContract.View {

    EditText email, pass, username, email2, pass2, confirmPass;
    RelativeLayout signUpLayout, signInLayout;
    LinearLayout mainLinear;
    TextView signUp, login;
    ImageView logo, back;
    LinearLayout.LayoutParams params, params2;
    FrameLayout.LayoutParams params3;
    FrameLayout mainFrame;
    ObjectAnimator animator2, animator1;
    Boolean usernameExiste = false;
    Boolean emailExiste = false;

    LoginActivityContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeView();
        initializeToolBar();
        initializeUtils();
    }


    @Override
    public void initializeView() {
        initializeParms();
        setContentView(R.layout.activity_login);
        signUp = findViewById(R.id.signUp);
        username = findViewById(R.id.username);
        email2 = findViewById(R.id.email2);
        pass2 = findViewById(R.id.pass2);
        confirmPass = findViewById(R.id.pass3);


        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);


        mainFrame = findViewById(R.id.mainFrame);


        back = findViewById(R.id.backImg);

        signUpLayout = findViewById(R.id.signUpLayout);
        signInLayout = findViewById(R.id.signInLayout);
        mainLinear = findViewById(R.id.mainLinear);

        logo = new ImageView(this);
        logo.setImageResource(R.drawable.logopng);
        logo.setLayoutParams(params3);
        signUpLayout.post(() -> {
            logo.setX((signInLayout.getRight() / 2));
            logo.setY(inDp(50));
            mainFrame.addView(logo);
        });

        params.weight = (float) 0.75;
        params2.weight = (float) 4.25;


        signUp.setOnClickListener(view -> {

            if (params.weight == 4.25) {

                presenter.do_register();
                return;
            } else {
                setSignUpLayout();
            }
        });


        login.setOnClickListener(view -> {

            if (params2.weight == 4.25) {
                if (validateLogin()) {
                    presenter.do_login();
                    return;
                } else
                    return;
            }
            setSignInLayout();
        });
    }

    @Override
    public void initializeToolBar() {
    }

    @Override
    public void initializeUtils() {
        presenter = new LoginActivityPresenter(this);
    }



    private int inDp(int dp) {

        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private void initializeParms() {
        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params3 = new FrameLayout.LayoutParams(inDp(200), inDp(200));
    }

    @Override
    public void setSignUpLayout() {
        email2.setVisibility(View.VISIBLE);
        pass2.setVisibility(View.VISIBLE);
        confirmPass.setVisibility(View.VISIBLE);
        username.setVisibility(View.VISIBLE);

        final ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(1500);
        bounds.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


                ObjectAnimator animator1 = ObjectAnimator.ofFloat(signUp, "translationX", mainLinear.getWidth() / 2 - signInLayout.getWidth() / 2 - signUp.getWidth() / 2);

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(signUp, "rotation", 0);

                ObjectAnimator animator4 = ObjectAnimator.ofFloat(email, "alpha", 1, 0);
                ObjectAnimator animator5 = ObjectAnimator.ofFloat(pass, "alpha", 1, 0);

                ObjectAnimator animator7 = ObjectAnimator.ofFloat(login, "rotation", 90);
                ObjectAnimator animator8 = ObjectAnimator.ofFloat(login, "y", signInLayout.getHeight() / 2);
                ObjectAnimator animator19 = ObjectAnimator.ofFloat(username, "alpha", 0, 1);

                ObjectAnimator animator9 = ObjectAnimator.ofFloat(email2, "alpha", 0, 1);
                ObjectAnimator animator10 = ObjectAnimator.ofFloat(confirmPass, "alpha", 0, 1);
                ObjectAnimator animator11 = ObjectAnimator.ofFloat(pass2, "alpha", 0, 1);

                ObjectAnimator animator12 = ObjectAnimator.ofFloat(signUp, "y", login.getY());


                ObjectAnimator animator14 = ObjectAnimator.ofFloat(signUp, "scaleX", 2);
                ObjectAnimator animator15 = ObjectAnimator.ofFloat(signUp, "scaleY", 2);

                ObjectAnimator animator16 = ObjectAnimator.ofFloat(login, "scaleX", 1);
                ObjectAnimator animator17 = ObjectAnimator.ofFloat(login, "scaleY", 1);
                ObjectAnimator animator18 = ObjectAnimator.ofFloat(logo, "x", signInLayout.getRight() / 2 - signUpLayout.getRight());

                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1, animator3, animator4, animator5, animator7,
                        animator8, animator9, animator10, animator11, animator12, animator14, animator15, animator16, animator17, animator18, animator19);
                set.setDuration(1500).start();


            }

            @Override
            public void onTransitionEnd(Transition transition) {


                email.setVisibility(View.INVISIBLE);
                pass.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {


            }
        });

        TransitionManager.beginDelayedTransition(mainLinear, bounds);

        params.weight = (float) 4.25;
        params2.weight = (float) 0.75;


        signUpLayout.setLayoutParams(params);
        signInLayout.setLayoutParams(params2);

    }

    @Override
    public void setSignInLayout() {
        email.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);



        final ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(1500);
        bounds.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {


                ObjectAnimator animator1 = ObjectAnimator.ofFloat(login, "translationX", mainLinear.getWidth() / 2 - signUpLayout.getWidth() / 2 - login.getWidth() / 2);

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(login, "rotation", 0);

                ObjectAnimator animator4 = ObjectAnimator.ofFloat(email, "alpha", 0, 1);
                ObjectAnimator animator5 = ObjectAnimator.ofFloat(pass, "alpha", 0, 1);


                ObjectAnimator animator7 = ObjectAnimator.ofFloat(signUp, "rotation", 90);
                ObjectAnimator animator8 = ObjectAnimator.ofFloat(signUp, "y", signUpLayout.getHeight() / 2);
                ObjectAnimator animator19 = ObjectAnimator.ofFloat(username, "alpha", 1, 0);

                ObjectAnimator animator9 = ObjectAnimator.ofFloat(email2, "alpha", 1, 0);

                ObjectAnimator animator10 = ObjectAnimator.ofFloat(confirmPass, "alpha", 1, 0);
                ObjectAnimator animator11 = ObjectAnimator.ofFloat(pass2, "alpha", 1, 0);
                ObjectAnimator animator12 = ObjectAnimator.ofFloat(login, "y", signUp.getY());


                ObjectAnimator animator14 = ObjectAnimator.ofFloat(login, "scaleX", 2);
                ObjectAnimator animator15 = ObjectAnimator.ofFloat(login, "scaleY", 2);

                ObjectAnimator animator16 = ObjectAnimator.ofFloat(signUp, "scaleX", 1);
                ObjectAnimator animator17 = ObjectAnimator.ofFloat(signUp, "scaleY", 1);
                ObjectAnimator animator18 = ObjectAnimator.ofFloat(logo, "x", logo.getX() + signInLayout.getWidth());


                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1, animator3, animator4, animator5, animator7,
                        animator8, animator9, animator10, animator11, animator12, animator14, animator15, animator16, animator17, animator18, animator19);
                set.setDuration(1500).start();

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                username.setVisibility(View.INVISIBLE);
                email2.setVisibility(View.INVISIBLE);
                pass2.setVisibility(View.INVISIBLE);
                confirmPass.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        TransitionManager.beginDelayedTransition(mainLinear, bounds);

        params.weight = (float) 0.75;
        params2.weight = (float) 4.25;

        signUpLayout.setLayoutParams(params);
        signInLayout.setLayoutParams(params2);
    }

    @Override
    public boolean validateRegister() {
        boolean valid = true;

        String _username = username.getText().toString();
        String email = email2.getText().toString();
        String password = pass2.getText().toString();
        String confirPassword = confirmPass.getText().toString();

        if (usernameExiste == true) {
            Log.d("usernameExiste", usernameExiste.toString());
            username.setError("Ce Username Existe Déjà");
            valid = false;
        } else {
            username.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email2.setError("enter a valid email address");
            valid = false;
        } else {
            email2.setError(null);
        }
        if (emailExiste == true) {
            email2.setError("Cette email Existe Déjà");
            valid = false;
        } else {
            email2.setError(null);
        }
        if (!confirPassword.equals(password)) {
            confirmPass.setError("Ne correspond pas à votre mot de passe   ");
            valid = false;
        } else {
            confirmPass.setError(null);
        }
        if (password.isEmpty() || (password.length() < 8)) {
            pass2.setError("mot de passe plus lang que 8 characters ");
            valid = false;
        }
        if (confirPassword.isEmpty()) {
            confirmPass.setError("enter votre mot de passe de confirmation");
            valid = false;
        }
        if (_username.isEmpty()) {
            username.setError("enter votre mot de passe");
            valid = false;
        }

        return valid;
    }

    @Override
    public boolean validateLogin() {
        boolean valid = true;
        String _username = email.getText().toString();
        String password = pass.getText().toString();
        if (_username.isEmpty())

        {
            email.setError("Saisir votre username ");
            valid = false;
        }
        if (password.isEmpty())

        {
            pass.setError("Saisir votre mot de passe ");
            valid = false;
        }
        return valid;
    }


    @Override
    public void LoginSucces(String s){
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginFail(String s) {
        if (s.contains("\"message\": \"Email or password is invalid!")){
            email.setError("Verifier votre nom d'utilisateur");
            pass.setError("Verifier votre mot de passe");
        }
        if (s.contains("\"message\": \"Verifié mot de passe!\"")){
            pass.setError("Verifier votre mot de passe");
        }
    }

    @Override
    public void RegisterFail(String s) {
        if (s.contains("\"message\": \"Username exist déja!\"")){
            username.setError("Nom d'utilisateur existe déja!");
        }
        if (s.contains("Email is exist!")){
            email2.setError("Email existe déja!");
        }
    }

    @Override
    public void RegisterSucces(String s) {
        Snackbar.make(signInLayout,"Validez votre compte sur votre e'mail",Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void goToMainActivity() {

    }


    @Override
    public UserRequest createUserRegisterRequest() {
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setEmail(email2.getText().toString());
        user.setPassword(pass2.getText().toString());
        return new UserRequest(user);
    }

    @Override
    public UserRequest createUserLoginRequest() {

        User user = new User();
        user.setUsername(email.getText().toString());
        user.setPassword(pass.getText().toString());
        return new UserRequest(user);
    }

    @Override
    public void clearRegisterEditText() {

    }
}
