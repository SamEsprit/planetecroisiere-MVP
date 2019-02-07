package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;

public class CompaniesFragmentContract {
    public interface View {
        void showCruisesLinesList(List<CruiseLine> cruisesLines);
    }

    public interface Presenter {
        void do_showCruisesLinesList();
    }
}
