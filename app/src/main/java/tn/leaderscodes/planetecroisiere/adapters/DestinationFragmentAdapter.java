package tn.leaderscodes.planetecroisiere.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.tools.SubRegionSectionHeader;
import tn.leaderscodes.planetecroisiere.viewholders.DestinationViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.HeaderDestinationViewHolder;

public class DestinationFragmentAdapter extends SectionRecyclerViewAdapter<SubRegionSectionHeader, SubRegion, HeaderDestinationViewHolder, DestinationViewHolder>  implements Filterable {

    Context context;
    private List<SubRegionSectionHeader> filtered_items;
    private List<SubRegionSectionHeader> subRegionSectionHeaders;
    private boolean clicked = false;
    public DestinationFragmentAdapter(Context context, List<SubRegionSectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
        subRegionSectionHeaders =sectionItemList;
        filtered_items=sectionItemList;
    }

    private DestinationFragmentAdapter.OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, SubRegion obj, int position);
    }
    public void setOnItemClickListener(final DestinationFragmentAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @Override
    public HeaderDestinationViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_destination, viewGroup, false);
        return new HeaderDestinationViewHolder(view);
    }

    @Override
    public DestinationViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_destionation, viewGroup, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(HeaderDestinationViewHolder headerDestinationViewHolder, int i, SubRegionSectionHeader subRegionSectionHeader) {
        headerDestinationViewHolder.getDestination_header_txt().setText(subRegionSectionHeader.getSectionText());
        setAnimationHeader(headerDestinationViewHolder.getLyt_parent(),i);
    }

    @Override
    public void onBindChildViewHolder(DestinationViewHolder destinationViewHolder, int i, int i1, SubRegion subRegion) {
        destinationViewHolder.getDestination_name_txt().setText(subRegion.getName());
        Glide.with(context).load("https://storage.gra1.cloud.ovh.net/v1/AUTH_5f5451bc9b2b421b8a5e21db942a5510/planete-croisiere/uploads/slider_element/slider_4_728.jpg").into(destinationViewHolder.getDestination_img());
        setAnimationCore(destinationViewHolder.getLyt_parent(),i);
        destinationViewHolder.getLyt_parent().setOnClickListener(view -> {
            if(!clicked && mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view, subRegion, i);
            }
        });
    }


    public Filter getFilter() {
        return new DestinationFragmentAdapter.ItemFilter();
    }
    private class ItemFilter extends Filter {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<SubRegionSectionHeader> list = subRegionSectionHeaders;
            final List<SubRegionSectionHeader> result_list = new ArrayList<>(list.size());
            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getSectionText();
                if (str_title.toLowerCase().contains(query)||list.get(i).getChildItems().stream().filter(a->a.getName().contains(query)).count()>0) {
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
            filtered_items = (List<SubRegionSectionHeader>) results.values;
            Log.d("TAG", "publishResults: "+filtered_items.size());
            notifyDataChanged(filtered_items);
        }

    }

    /**
     * Here is the key method to apply the animation
     */
    private int lastPosition = -1;
    private void setAnimationHeader(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    private void setAnimationCore(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
