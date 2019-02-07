package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;

public class DestinationDetailActivityContract {

    public interface View {
        void showTravelsList(List<Travel> travels);
        void showCruiseLinesList(List<CruiseLine> cruiseLines);
    }

    public interface Presenter {
        void do_showTravelsList(TravelRequest travelRequest);
        void do_showCruiseLinesList();
    }
}
