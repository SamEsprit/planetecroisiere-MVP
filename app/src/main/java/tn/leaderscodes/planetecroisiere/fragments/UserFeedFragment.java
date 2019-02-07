package tn.leaderscodes.planetecroisiere.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.FeedsFragmentAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseFragment;
import tn.leaderscodes.planetecroisiere.contracts.FeedFragmentContract;
import tn.leaderscodes.planetecroisiere.contracts.UserFeedFragmentContract;
import tn.leaderscodes.planetecroisiere.presenters.FeedFragmentPresenter;
import tn.leaderscodes.planetecroisiere.presenters.UserFeedFragmentPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;
import tn.leaderscodes.planetecroisiere.tools.Session;
import tn.leaderscodes.planetecroisiere.tools.URLS;


public class UserFeedFragment extends BaseFragment implements UserFeedFragmentContract.View {

    private ShimmerRecyclerView feed_rv;
    private ImageView bt_share, bt_add_img, photo_content, user_image;
    private EditText content_edt;
    private TextView user_name;
    private UserFeedFragmentContract.Presenter presenter;

    private FeedsFragmentAdapter feedsFragmentAdapter;
    private RecyclerView.LayoutManager lineareLayoutManager;

    private static final int REQUEST_GALLERY_CODE = 999;

    private RequestBody mFile;
    private MultipartBody.Part fileToUpload;
    private RequestBody filename;
    UserFeedFragment feedFragment;

    public UserFeedFragment() {
        // Required empty public constructor
        feedFragment = this;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
        presenter.do_showFeedsList();
        bt_add_img.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            feedFragment.startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
        });

        bt_share.setOnClickListener(v -> {
            if (filename != null || !content_edt.getText().toString().equals("")) {
                presenter.do_uploadProfileImage(fileToUpload, filename);
            }
            if (filename == null && !content_edt.getText().toString().equals("")) {
                Publication publication = new Publication();
                publication.setContenu(content_edt.getText().toString());
                presenter.do_addFeed(publication);
            }
            if (filename==null && content_edt.getText().toString().equals(""))
            {

            }

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
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        feed_rv = view.findViewById(R.id.feed_rv);
        bt_share = view.findViewById(R.id.bt_share);
        bt_add_img = view.findViewById(R.id.bt_add_img);
        content_edt = view.findViewById(R.id.content_edt);
        photo_content = view.findViewById(R.id.photo_content);
        user_image = view.findViewById(R.id.user_image);
        user_name = view.findViewById(R.id.user_name);

        Glide.with(getActivity()).load(URLS.EndPointProfilImg + Session.getInstance().getImage()).into(user_image);
        user_name.setText(Session.getInstance().getusename());

        return view;
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new UserFeedFragmentPresenter(this);
        feedsFragmentAdapter = new FeedsFragmentAdapter(getActivity());
        lineareLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public void showFeedsList(List<Publication> publications) {
        feed_rv.showShimmerAdapter();
        feed_rv.postDelayed(() -> {
            feed_rv.setHasFixedSize(true);
            feedsFragmentAdapter.setmItems(publications);
            feed_rv.setAdapter(feedsFragmentAdapter);
            feed_rv.setLayoutManager(lineareLayoutManager);
            feed_rv.hideShimmerAdapter();
        }, 2000);
    }

    @Override
    public void feedAdded(Publication publication) {
        feedsFragmentAdapter.addData(publication);
        content_edt.setText("");
        photo_content.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(content_edt.getWindowToken(), 0);
    }

    @Override
    public void uploadImage(mediaResponse mediaResponse) {
        Publication publication = new Publication();
        publication.setContenu(content_edt.getText().toString());
        publication.setImage(mediaResponse.getPictureName());
        presenter.do_addFeed(publication);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            } else {
                String filePath = getRealPathFromURIPath(uri, getActivity());
                File file = new File(filePath);

                Glide.with(feedFragment).load(filePath).into(photo_content);
                photo_content.setVisibility(View.VISIBLE);
                mFile = RequestBody.create(MediaType.parse("image/*"), file);
                fileToUpload = MultipartBody.Part.createFormData("imageFeed", file.getName(), mFile);
                filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            }
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
