package tn.leaderscodes.planetecroisiere.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.MainActivityContract;
import tn.leaderscodes.planetecroisiere.fragments.MainFragment;
import tn.leaderscodes.planetecroisiere.fragments.ReservationFragment;
import tn.leaderscodes.planetecroisiere.presenters.MainActivityPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.tools.Session;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityContract.View {

    private Toolbar toolbar;
    private Session session = Session.getInstance();
    private static final int REQUEST_GALLERY_CODE = 200;
    private MainActivityContract.Presenter presenter;
    private ImageView profile_img_button;
    private RequestBody mFile;
    private MultipartBody.Part fileToUpload;
    private RequestBody filename;
    private List<String> civility = new ArrayList<String>();
    private ArrayAdapter civility_spn_ArrayAdapter;
    private Spinner civility_spn;
    private EditText firstname_txt, lastname_txt, phone_txt, address_txt;
    private String civility_str;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getPreferences(Context.MODE_PRIVATE);
         verifFirst();
        if (!session.getLogin()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class); //call your ViewPager class
            startActivity(intent);
        } else
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new MainFragment()).commit();
        setContentView(R.layout.activity_main);
        civility.add("Mr");
        civility.add("Mme");
        verifLogin();
        initializeToolBar();
        initializeUtils();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initializeToolBar() {
        super.initializeToolBar();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Planète Croisière");
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new MainFragment()).commit();
        } else if (id == R.id.nav_reservation) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new ReservationFragment()).commit();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void verifLogin() {
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        if (!sp.getBoolean("firstlogin", false)) {
            View view = View.inflate(getApplicationContext(), R.layout.complet_profile_information, null);
            civility_spn = view.findViewById(R.id.civility_spn);
            profile_img_button = view.findViewById(R.id.profile_img_button);
            firstname_txt = view.findViewById(R.id.firstname_txt);
            lastname_txt = view.findViewById(R.id.lastname_txt);
            phone_txt = view.findViewById(R.id.phone_txt);
            address_txt = view.findViewById(R.id.address_txt);
            civility_spn_ArrayAdapter = new ArrayAdapter(this,
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
            profile_img_button.setOnClickListener(v -> {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            });

            new MaterialStyledDialog.Builder(this)
                    .setCustomView(view)
                    .setCancelable(false)
                    .setHeaderDrawable(R.drawable.logopng)
                    .setPositiveText("Enregistre")
                    .onPositive((dialog, which) -> {
                        if (filename != null)
                            presenter.do_uploadProfileImage(fileToUpload, filename);
                        else {
                            User user = new User();
                            user.setUsername(Session.getInstance().getusename());
                            user.setEmail(Session.getInstance().getEmail());
                            user.setPhone(phone_txt.getText().toString());
                            user.setFirstName(firstname_txt.getText().toString());
                            user.setLastName(lastname_txt.getText().toString());
                            user.setCivility(civility_str);
                            user.setAddress(address_txt.getText().toString());
                            presenter.do_updateMyself(new UserRequest(user));
                        }
                        dialog.dismiss();
                    })
                    .show();
        }

    }

    public void verifFirst() {

        if (!sp.getBoolean("first",false)) {
            Intent intent = new Intent(getApplicationContext(), ActivityHelp.class); //call your ViewPager class
            startActivity(intent);
        }

        sp.edit().putBoolean("first", true).apply();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                String filePath = getRealPathFromURIPath(uri, MainActivity.this);
                File file = new File(filePath);
                Glide.with(getApplicationContext()).load(BitmapFactory.decodeFile(filePath)).into(profile_img_button);
                mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("profileImage", file.getName(), mFile);
                filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
        }
    }

    @Override
    public void uploadProfileImage(mediaResponse mediaResponse) {
        Session.getInstance().setImage(mediaResponse.getPictureName());
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
    public void updateMyself() {
        sp.edit().putBoolean("firstlogin", true).apply();
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
