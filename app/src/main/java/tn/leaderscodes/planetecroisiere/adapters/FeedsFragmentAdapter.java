package tn.leaderscodes.planetecroisiere.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.activities.CommentsActivity;
import tn.leaderscodes.planetecroisiere.contracts.FeedAdapterContract;
import tn.leaderscodes.planetecroisiere.presenters.FeedAdapterPresenter;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.tools.URLS;
import tn.leaderscodes.planetecroisiere.viewholders.CompaniesViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.FeedViewHolder;

public class FeedsFragmentAdapter extends RecyclerView.Adapter<FeedViewHolder> implements FeedAdapterContract.View {


    private List<Publication> publications;
    FeedAdapterContract.Presenter presenter;
    Context context;
    Integer publicationIndex = 0;

    public FeedsFragmentAdapter(Context context) {
        super();
        this.context = context;
        publications = new ArrayList<>();
        presenter = new FeedAdapterPresenter(this);
    }

    public void setmItems(List<Publication> publications) {
        this.publications.clear();
        this.publications = publications;

    }

    public void addData(Publication publication) {
        publications.add(0, publication);
        notifyDataSetChanged();
    }

    public void clear() {
        publications.clear();
        notifyDataSetChanged();
    }


    /**
     * Here is the key method to apply the animation
     */
    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_feed, parent, false);
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Publication publication = publications.get(position);
        holder.getUser_name().setText(publication.getUser().getFirstName() + " " + publication.getUser().getLastName());
        Glide.with(context).load(URLS.EndPointProfilImg + publication.getUser().getImage()).into(holder.getUser_image());
        if (publication.getImage() != null) {
            Glide.with(context).load(URLS.EndPointFeedImg + publication.getImage()).into(holder.getPhoto_content());
            holder.getPhoto_content().setVisibility(View.VISIBLE);
        } else holder.getPhoto_content().setVisibility(View.GONE);

        if (publication.isLiked())
            holder.getBt_like().setImageDrawable(context.getDrawable(R.drawable.baseline_favorite_24));
        else
            holder.getBt_like().setImageDrawable(context.getDrawable(R.drawable.baseline_favorite_border_24));

        if (publication.getContenu() != null) {
            holder.getText_content().setText(publication.getContenu());
            holder.getText_content().setVisibility(View.VISIBLE);
        } else holder.getPhoto_content().setVisibility(View.GONE);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d=publication.getDate().substring(8,10)+"/"+publication.getDate().substring(5,7)+"/"+publication.getDate().substring(0,4)+" "+publication.getDate().substring(11,16);
        try {
            Date date = dateFormat.parse(d);
            holder.getText_date().setReferenceTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.getBt_like().setOnClickListener(v -> {
            publicationIndex = position;
            if (publication.isLiked())
                presenter.do_dislike(publication.get_id());
            else
                presenter.do_Like(publication.get_id());
        });

        holder.getBt_comment().setOnClickListener(v -> {
            Intent intent= new Intent(context, CommentsActivity.class);
            intent.putExtra("publication",publication);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    @Override
    public void like(Publication publication) {
        publications.get(publicationIndex).setLiked(publication.isLiked());
        notifyDataSetChanged();
    }

    @Override
    public void dislike(Publication publication) {
        publications.get(publicationIndex).setLiked(publication.isLiked());
        notifyDataSetChanged();
    }
}
