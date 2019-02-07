package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;

public class CompanyDetailActivityContract {

    public interface View {
        void showBoatsList(List<Boat> boats);

        void showTravelsList(List<Travel> travels);
        void showDestinationsList(List<SubRegion> subRegions);
    }

    public interface Presenter {
        void do_showBoatsList();

        void do_showTravelsList(TravelRequest travelRequest);
        void do_showDestinationsList();
    }
}
