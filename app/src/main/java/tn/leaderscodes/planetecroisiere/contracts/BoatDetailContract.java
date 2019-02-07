package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;

public class BoatDetailContract {
    public interface View {
        void showActivity();
        void showTravelsList(List<Travel> travels);

    }

    public interface Presenter {
        void do_showTravelsList(TravelRequest travelRequest);
    }
}
