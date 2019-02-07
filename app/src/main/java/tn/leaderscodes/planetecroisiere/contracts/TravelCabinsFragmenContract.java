package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.remote.request.BoatIdRequest;

public class TravelCabinsFragmenContract {
    public interface View {
        void showTypeCabineList(List<TypeCabine> typeCabines);
    }

    public interface Presenter {
        void do_showTypeCabineList(BoatIdRequest boatIdRequest);
    }
}
