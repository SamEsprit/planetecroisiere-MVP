package tn.leaderscodes.planetecroisiere.contracts;

import tn.leaderscodes.planetecroisiere.remote.request.ReservationRequest;

public class TravelTarifFragmentContract {

    public interface View {
        void showTravelTarif(Integer position);
        void reservationAdded();
    }
    public interface Presenter{

        void do_addReservation(ReservationRequest reservationRequest);
    }
}
