package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;

public class ReservationFragmentContract {

    public interface View {
        void showReservationDate(List<Reservation> reservations);
    }

    public interface Presenter {
        void do_showReservationDate();
    }
}
