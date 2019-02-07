package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.entities.Place;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;

public class TravelByFilterContract {
    public interface View {
        void showTravelsList(List<Travel> travels);
        void showCompagniesList(List<CruiseLine> travels);
        void showDestinationsList(List<SubRegion> travels);
        void showPlacesList(List<Place> places);
        void showBoatsByCompagnieList(List<Boat> boats);
        void showLoadMore(List<Travel> travels);
    }

    public interface Presenter {
        void do_showCompagniesList();
        void do_showDestinationsList();
        void do_showPlacesList();
        void do_showBoatsByCompagnieList(CompanyBoatRequest companyBoatRequest);
        void do_showTravelsList(TravelRequest travelRequest);
        void do_loadMore(TravelRequest travelRequest);
    }
}
