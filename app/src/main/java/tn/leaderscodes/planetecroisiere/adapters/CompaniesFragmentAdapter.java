package tn.leaderscodes.planetecroisiere.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.viewholders.CompaniesViewHolder;

import static tn.leaderscodes.planetecroisiere.tools.URLS.EndPointCompanyImg;

public class CompaniesFragmentAdapter extends RecyclerView.Adapter<CompaniesViewHolder> implements Filterable {


    private List<CruiseLine> cruisesLines;
    private List<CruiseLine> filtered_items = new ArrayList<>();
    Context context;
    private boolean clicked = false;
    public CompaniesFragmentAdapter(Context context) {
        super();
        this.context=context;
        cruisesLines = new ArrayList<>();
    }

    public void setmItems(List<CruiseLine> cruisesLines) {
        this.cruisesLines.clear();
        this.cruisesLines = cruisesLines;
        this.filtered_items=cruisesLines;
    }

    public void addData(CruiseLine cruiseLine) {
        cruisesLines.add(0, cruiseLine);
        notifyDataSetChanged();
    }

    public void clear() {
        cruisesLines.clear();
        filtered_items.clear();
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, CruiseLine obj, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
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
    public CompaniesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_company, parent, false);
        return new CompaniesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompaniesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CruiseLine cruiseLine = filtered_items.get(position);
        holder.getCompany_name_txt().setText(cruiseLine.getName());
        Glide.with(context).load(EndPointCompanyImg+cruiseLine.getImage()).thumbnail(0.5f).into(holder.getCompany_img());
        setAnimation(holder.getLyt_parent(), position);
        holder.getLyt_parent().setOnClickListener(view -> {
            if(!clicked && mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view, cruiseLine, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filtered_items.size();
    }
    @Override
    public Filter getFilter() {
        return new ItemFilter();
    }
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<CruiseLine> list = cruisesLines;
            final List<CruiseLine> result_list = new ArrayList<>(list.size());
            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getName();
                if (str_title.toLowerCase().contains(query)) {
                    result_list.add(list.get(i));
                }
            }
            results.values = result_list;
            results.count = result_list.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered_items = (List<CruiseLine>) results.values;
            notifyDataSetChanged();
        }

    }
}
