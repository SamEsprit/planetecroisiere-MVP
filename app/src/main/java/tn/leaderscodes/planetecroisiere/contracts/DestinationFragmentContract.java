package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;
import tn.leaderscodes.planetecroisiere.remote.response.SubRegionResponse;

public class DestinationFragmentContract {
    public interface View {
        void showDestinationsList(List<SubRegionResponse> subRegionResponses);
    }

    public interface Presenter {
        void do_showDestinationsList();
    }
}
