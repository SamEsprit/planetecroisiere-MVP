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
import tn.leaderscodes.planetecroisiere.remote.entities.Price;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.tools.SubRegionSectionHeader;
import tn.leaderscodes.planetecroisiere.tools.TravelTarifSectionHeader;
import tn.leaderscodes.planetecroisiere.viewholders.DestinationViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.HeaderDestinationViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.HeaderTarifViewHolder;
import tn.leaderscodes.planetecroisiere.viewholders.TarifViewHolder;

public class TravelTarifFragmentAdapter extends SectionRecyclerViewAdapter<TravelTarifSectionHeader, Price, HeaderTarifViewHolder, TarifViewHolder>  {

    Context context;
    private List<TravelTarifSectionHeader> TravelTarifSectionHeader;
    private boolean clicked = false;
    public TravelTarifFragmentAdapter(Context context, List<TravelTarifSectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
        TravelTarifSectionHeader =sectionItemList;

    }

    private TravelTarifFragmentAdapter.OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, Price obj, int position);
    }
    public void setOnItemClickListener(final TravelTarifFragmentAdapter.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    @Override
    public HeaderTarifViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_travel_tarif, viewGroup, false);
        return new HeaderTarifViewHolder(view);
    }

    @Override
    public TarifViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarif_travel, viewGroup, false);
        return new TarifViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(HeaderTarifViewHolder headerTarifViewHolder, int i, TravelTarifSectionHeader travelTarifSectionHeader) {
        headerTarifViewHolder.getTarif_header_txt().setText(travelTarifSectionHeader.getSectionText());
    }

    @Override
    public void onBindChildViewHolder(TarifViewHolder tarifViewHolder, int i, int i1, Price price) {
        tarifViewHolder.getTarif_description().setText(price.getCategory().getCode() +" - " + price.getCategory().getDescription());
        tarifViewHolder.getTarif().setText(price.getPrice()+" €");
        tarifViewHolder.getLyt_parent().setOnClickListener(view -> {
            if(!clicked && mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(view, price, i);
            }
        });

    }



    @Override
    public void notifyDataChanged(List<tn.leaderscodes.planetecroisiere.tools.TravelTarifSectionHeader> sectionItemList) {
        super.notifyDataChanged(sectionItemList);
    }
}
