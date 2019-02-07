package tn.leaderscodes.planetecroisiere.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xyz.step.FlowViewVertical;

import java.util.ArrayList;
import java.util.List;

import tn.leaderscodes.planetecroisiere.R;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;

public class DetailReservationActivity extends AppCompatActivity {
    
    private TextView travel_name,depart_date,adulte_nbr,child_nbr,comany_txt,boat_txt,price_txt,cabins_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reservation);
        FlowViewVertical vFlow = findViewById(R.id.vflow);
        travel_name=findViewById(R.id.travel_name);
        depart_date=findViewById(R.id.depart_date);
        adulte_nbr=findViewById(R.id.adulte_nbr);
        child_nbr=findViewById(R.id.child_nbr);
        comany_txt=findViewById(R.id.comany_txt);
        boat_txt=findViewById(R.id.boat_txt);
        price_txt=findViewById(R.id.price_txt);
        cabins_name=findViewById(R.id.cabins_name);
        Reservation reservation = (Reservation) getIntent().getSerializableExtra("reservation");

        adulte_nbr.setText(reservation.getAdulte()+"");
        child_nbr.setText(reservation.getChild()+"");
        boat_txt.setText(reservation.getTravel().getCruise().getBoat().getName()+"");
        comany_txt.setText(reservation.getTravel().getCruise().getCruiseLine().getName()+"");
        depart_date.setText(reservation.getDate());
        price_txt.setText(reservation.getPrice()+" €");
        travel_name.setText(reservation.getTravel().getCruise().getName());
        cabins_name.setText(reservation.getCabineCategorie());

        List<String> list0 = new ArrayList<>();
        list0.add("Contrat Validé");
        list0.add("Contrat en cours");
        list0.add("Devie confirmé");
        list0.add("Devie en cours");
        list0.add("Demande Devie");
        if (reservation.getStatus() == 0)
            vFlow.setProgress(4, 5, list0.toArray(new String[0]), null);
        if (reservation.getStatus() == 1)
            vFlow.setProgress(3, 5, list0.toArray(new String[0]), null);
        if (reservation.getStatus() == 2)
            vFlow.setProgress(2, 5, list0.toArray(new String[0]), null);
        if (reservation.getStatus() == 3)
            vFlow.setProgress(1, 5, list0.toArray(new String[0]), null);
        if (reservation.getStatus() == 4)
            vFlow.setProgress(0, 5, list0.toArray(new String[0]), null);
    }
}
