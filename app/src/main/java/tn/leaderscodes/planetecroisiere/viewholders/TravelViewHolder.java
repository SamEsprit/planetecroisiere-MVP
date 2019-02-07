package tn.leaderscodes.planetecroisiere.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import tn.leaderscodes.planetecroisiere.R;

public class TravelViewHolder extends RecyclerView.ViewHolder {

    final FoldingCell fc;
    //Header
    private TextView title_travel;
    private TextView cruiseLine_title;
    private TextView price_travel;
    private TextView first_departure_date;
    private TextView trave_depart;
    private TextView travel_duree;
    private TextView destination_name;


    //contant
    private TextView travel_title_content;
    private TextView travel_destination_content;
    private TextView travel_duree_content;
    private TextView travel_departdate_content;
    private TextView travel_depart_content;
    private TextView travel_itinerary_content;
    private TextView price_content;
    private TextView cruiseLine_content;
    private ImageView travel_image;
    private Button show_plus;


    public TravelViewHolder(View itemView) {
        super(itemView);
        fc =  itemView.findViewById(R.id.folding_cell);
        title_travel =  itemView.findViewById(R.id.title_travel);
        cruiseLine_title =  itemView.findViewById(R.id.cruiseLine_title);
        price_travel =  itemView.findViewById(R.id.price_travel);
        first_departure_date =  itemView.findViewById(R.id.first_departure_date);
        trave_depart =  itemView.findViewById(R.id.trave_depart);
        travel_duree =  itemView.findViewById(R.id.travel_duree);
        destination_name =  itemView.findViewById(R.id.destination_name);

        travel_title_content =  itemView.findViewById(R.id.travel_title_content);
        travel_destination_content =  itemView.findViewById(R.id.travel_destination_content);
        travel_duree_content =  itemView.findViewById(R.id.travel_duree_content);
        travel_departdate_content =  itemView.findViewById(R.id.travel_departdate_content);
        travel_depart_content =  itemView.findViewById(R.id.travel_depart_content);
        travel_itinerary_content =  itemView.findViewById(R.id.travel_itinerary_content);
        price_content =  itemView.findViewById(R.id.price_content);
        travel_image =  itemView.findViewById(R.id.travel_image);
        cruiseLine_content =  itemView.findViewById(R.id.cruiseLine_content);
        show_plus =  itemView.findViewById(R.id.show_plus);
    }

    public FoldingCell getFc() {
        return fc;
    }

    public TextView getTitle_travel() {
        return title_travel;
    }

    public void setTitle_travel(TextView title_travel) {
        this.title_travel = title_travel;
    }

    public TextView getPrice_travel() {
        return price_travel;
    }

    public void setPrice_travel(TextView price_travel) {
        this.price_travel = price_travel;
    }

    public TextView getFirst_departure_date() {
        return first_departure_date;
    }

    public void setFirst_departure_date(TextView first_departure_date) {
        this.first_departure_date = first_departure_date;
    }

    public TextView getTrave_depart() {
        return trave_depart;
    }

    public void setTrave_depart(TextView trave_depart) {
        this.trave_depart = trave_depart;
    }

    public TextView getTravel_duree() {
        return travel_duree;
    }

    public void setTravel_duree(TextView travel_duree) {
        this.travel_duree = travel_duree;
    }

    public TextView getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(TextView destination_name) {
        this.destination_name = destination_name;
    }

    public TextView getTravel_title_content() {
        return travel_title_content;
    }

    public void setTravel_title_content(TextView travel_title_content) {
        this.travel_title_content = travel_title_content;
    }

    public TextView getTravel_destination_content() {
        return travel_destination_content;
    }

    public void setTravel_destination_content(TextView travel_destination_content) {
        this.travel_destination_content = travel_destination_content;
    }

    public TextView getTravel_duree_content() {
        return travel_duree_content;
    }

    public void setTravel_duree_content(TextView travel_duree_content) {
        this.travel_duree_content = travel_duree_content;
    }

    public TextView getTravel_departdate_content() {
        return travel_departdate_content;
    }

    public void setTravel_departdate_content(TextView travel_departdate_content) {
        this.travel_departdate_content = travel_departdate_content;
    }

    public TextView getTravel_depart_content() {
        return travel_depart_content;
    }

    public void setTravel_depart_content(TextView travel_depart_content) {
        this.travel_depart_content = travel_depart_content;
    }

    public TextView getTravel_itinerary_content() {
        return travel_itinerary_content;
    }

    public void setTravel_itinerary_content(TextView travel_itinerary_content) {
        this.travel_itinerary_content = travel_itinerary_content;
    }

    public TextView getPrice_content() {
        return price_content;
    }

    public ImageView getTravel_image() {
        return travel_image;
    }

    public TextView getCruiseLine_content() {
        return cruiseLine_content;
    }

    public TextView getCruiseLine_title() {
        return cruiseLine_title;
    }

    public Button getShow_plus() {
        return show_plus;
    }
}
