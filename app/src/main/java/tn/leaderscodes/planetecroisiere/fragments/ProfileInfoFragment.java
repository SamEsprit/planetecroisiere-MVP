package tn.leaderscodes.planetecroisiere.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.MainActivity;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.ProfileInfoFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.ProfileInfoFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.tools.Session;
import tn.leaderscodes.planetecroisiere.tools.URLS;

public class ProfileInfoFragment extends BaseFragment implements ProfileInfoFragmentContract.View {


    private TextView user_nom_prenom, email, phone, adresss;
    private User user;
    private ImageButton edit_btn;

    private ImageView profile_img_button;

    private List<String> civility = new ArrayList<String>();
    private ArrayAdapter civility_spn_ArrayAdapter;
    private Spinner civility_spn;
    private EditText firstname_txt, lastname_txt, phone_txt, address_txt;
    private String civility_str;

    ProfileInfoFragmentContract.Presenter presenter;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getArguments().get("User");
        civility.add("Mr");
        civility.add("Mme");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return initializeView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUtils();
        this.user_nom_prenom.setText(user.getCivility() + "." + user.getFirstName() + " " + user.getLastName());
        this.phone.setText(user.getPhone());
        this.adresss.setText(user.getAddress());
        this.email.setText(user.getEmail());

        edit_btn.setOnClickListener(v -> {
            civility_str = user.getCivility();
            View viewDialog = View.inflate(getActivity(), R.layout.complet_profile_information, null);
            civility_spn = viewDialog.findViewById(R.id.civility_spn);
            profile_img_button = viewDialog.findViewById(R.id.profile_img_button);
            firstname_txt = viewDialog.findViewById(R.id.firstname_txt);
            lastname_txt = viewDialog.findViewById(R.id.lastname_txt);
            phone_txt = viewDialog.findViewById(R.id.phone_txt);
            address_txt = viewDialog.findViewById(R.id.address_txt);
            civility_spn_ArrayAdapter = new ArrayAdapter(getActivity(),
                    android.R.layout.simple_spinner_dropdown_item, civility);
            civility_spn.setAdapter(civility_spn_ArrayAdapter);
            civility_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    civility_str = parent.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Glide.with(getActivity()).load(URLS.EndPointProfilImg + user.getImage()).into(profile_img_button);
            civility_spn.setSelection(civility.indexOf(user.getCivility()));
            firstname_txt.setText(user.getFirstName());
            lastname_txt.setText(user.getLastName());
            address_txt.setText(user.getAddress());
            phone_txt.setText(user.getPhone());
            profile_img_button.setVisibility(View.GONE);

            new MaterialStyledDialog.Builder(getActivity())
                    .setCustomView(viewDialog)
                    .setCancelable(false)
                    .setHeaderDrawable(R.drawable.logopng)
                    .setPositiveText("Enregistre")
                    .onPositive((dialog, which) -> {
                        User user = new User();
                        user.setUsername(Session.getInstance().getusename());
                        user.setImage(user.getImage());
                        user.setEmail(Session.getInstance().getEmail());
                        user.setPhone(phone_txt.getText().toString());
                        user.setFirstName(firstname_txt.getText().toString());
                        user.setLastName(lastname_txt.getText().toString());
                        user.setCivility(civility_str);
                        user.setAddress(address_txt.getText().toString());
                        presenter.do_updateMyself(new UserRequest(user));
                        dialog.dismiss();
                    })
                    .show();
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new ProfileInfoFragmentPresenter(this);
    }

    @Override
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        user_nom_prenom = view.findViewById(R.id.user_nom_prenom);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        adresss = view.findViewById(R.id.adresss);
        edit_btn = view.findViewById(R.id.edit_btn);
        return view;
    }

    @Override
    public void uploadProfileImage(mediaResponse mediaResponse) {
        User user = new User();
        user.setUsername(Session.getInstance().getusename());
        user.setImage(mediaResponse.getPictureName());
        user.setEmail(Session.getInstance().getEmail());
        user.setPhone(phone_txt.getText().toString());
        user.setFirstName(firstname_txt.getText().toString());
        user.setLastName(lastname_txt.getText().toString());
        user.setCivility(civility_str);
        user.setAddress(address_txt.getText().toString());
        presenter.do_updateMyself(new UserRequest(user));
    }

    @Override
    public void updateMyself(UserResponse userResponse) {
        user = userResponse.getUser();
        this.user_nom_prenom.setText(user.getCivility() + "." + user.getFirstName() + " " + user.getLastName());
        this.phone.setText(user.getPhone());
        this.adresss.setText(user.getAddress());
        this.email.setText(user.getEmail());
    }

}
