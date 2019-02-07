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
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.MainActivity;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.ProfileFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.ProfileFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.tools.URLS;

public class ProfileFragment extends BaseFragment implements ProfileFragmentContract.View {
    private ViewPager mViewPager;
    private ImageView user_img;

    private ProfileInfoFragment profileInfoFragment;
    private UserFeedFragment publicationFragment;
    private static final int REQUEST_GALLERY_CODE = 300;
    private ProfileFragmentContract.Presenter presenter;
    private TabLayout tabLayout;
    private User user;
    private RequestBody mFile;
    private MultipartBody.Part fileToUpload;
    private RequestBody filename;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        presenter.do_populateUser();
        user_img.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            Objects.requireNonNull(getActivity()).startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
        });

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
    public View initializeView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mViewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.sliding_tabs);
        user_img = view.findViewById(R.id.user_img);
        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new ProfileFragmentPresenter(this);
    }

    @Override
    public void populateUser(User user) {

        this.user = user;
        Glide.with(Objects.requireNonNull(getActivity())).load(URLS.EndPointProfilImg + user.getImage()).into(user_img);
        setupViewPager(this.mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void uploadProfileImage(mediaResponse mediaResponse) {

    }

    @Override
    public void updateMyself() {

    }

    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
        profileInfoFragment = new ProfileInfoFragment();
        profileInfoFragment.setArguments(bundle);
        publicationFragment = new UserFeedFragment();
        publicationFragment.setArguments(bundle);
        adapter.addFragment(publicationFragment, "Publication");
        adapter.addFragment(profileInfoFragment, "Info");
        mViewPager.setAdapter(adapter);
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d("sdqsdqsd", "onActivityResult: " + "sasdqsdqsdqd");
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            // Permission has already been granted
            String filePath = getRealPathFromURIPath(uri, Objects.requireNonNull(getActivity()));
            File file = new File(filePath);
            Glide.with(getActivity().getApplicationContext()).load(BitmapFactory.decodeFile(filePath)).into(user_img);
            mFile = RequestBody.create(MediaType.parse("image/*"), file);
            fileToUpload = MultipartBody.Part.createFormData("profileImage", file.getName(), mFile);
            filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            new MaterialDialog.Builder(getActivity()).show();
        }
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
