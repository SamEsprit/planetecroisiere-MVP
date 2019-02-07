package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import tn.leaderscodes.planetecroisiere.remote.entities.Publication;

public class FeedAdapterContract {
    public interface View {
        void like(Publication publication);
        void dislike(Publication publication);
    }

    public interface Presenter {
        void do_Like(String id);
        void do_dislike(String id);
    }
}
