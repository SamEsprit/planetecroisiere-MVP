package tn.leaderscodes.planetecroisiere.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.adapters.CommentAdapter;
import tn.leaderscodes.planetecroisiere.bases.BaseActivity;
import tn.leaderscodes.planetecroisiere.contracts.CommentaireActivityContract;
import tn.leaderscodes.planetecroisiere.presenters.CommentaireActivityPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.request.CommentsRequest;
import tn.leaderscodes.planetecroisiere.tools.URLS;

public class CommentsActivity extends BaseActivity implements CommentaireActivityContract.View {


    private ImageView user_image, photo_content;
    private TextView user_name;
    private RelativeTimeTextView text_date;
    private TextView text_content;
    private EditText content;
    private ImageButton comment;
    private RecyclerView lvCommentaire;
    private RecyclerView.LayoutManager lineareLayoutManager;
    private CommentaireActivityContract.Presenter presenter;
    private CommentAdapter commentAdapter;
    private Publication publication;

    public CommentsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        publication = (Publication) getIntent().getSerializableExtra("publication");
        initializeView();
        initializeUtils();
        initializeToolBar();
        presenter.do_showComments(publication.get_id());
    }

    @Override
    public void showComments(List<Commentaire> commentaires) {
        lvCommentaire.setHasFixedSize(true);
        commentAdapter.setmItems(commentaires);
        lvCommentaire.setLayoutManager(lineareLayoutManager);
        lvCommentaire.setAdapter(commentAdapter);
    }

    @Override
    public void addComments(Commentaire commentaire) {
        commentAdapter.addData(commentaire);
        content.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(content.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_comments);
        user_image = findViewById(R.id.user_image);
        user_name = findViewById(R.id.user_name);
        content = findViewById(R.id.content);
        comment = findViewById(R.id.comment);
        text_content = findViewById(R.id.text_content);
        photo_content = findViewById(R.id.photo_content);
        text_date = findViewById(R.id.text_date);

        lvCommentaire = findViewById(R.id.lvCommentaire);

        text_content.setText(publication.getContenu());
        user_name.setText(publication.getUser().getFirstName() + " " + publication.getUser().getLastName());

        Glide.with(getApplicationContext()).load(URLS.EndPointProfilImg + publication.getUser().getImage()).into(user_image);

        if (publication.getImage() != null) {
            Glide.with(getApplicationContext()).load(URLS.EndPointFeedImg + publication.getImage()).into(photo_content);
            photo_content.setVisibility(View.VISIBLE);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = publication.getDate().substring(8, 10) + "/" + publication.getDate().substring(5, 7) + "/" + publication.getDate().substring(0, 4) + " " + publication.getDate().substring(11, 16);
        try {
            Date date = dateFormat.parse(d);
            text_date.setReferenceTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        comment.setOnClickListener(v -> {
            if (!content.getText().toString().equals("")) {
                Commentaire commentaire = new Commentaire();
                commentaire.setText(content.getText().toString());
                CommentsRequest commentsRequest = new CommentsRequest(commentaire);
                presenter.do_addComments(publication.get_id(), commentsRequest);
            }
        });


    }

    @Override
    public void initializeToolBar() {
        super.initializeToolBar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Planète Croisière");
    }

    @Override
    public void initializeUtils() {
        super.initializeUtils();
        presenter = new CommentaireActivityPresenter(this);
        commentAdapter = new CommentAdapter(getApplicationContext());
        lineareLayoutManager = new LinearLayoutManager(getApplicationContext());
    }
}
