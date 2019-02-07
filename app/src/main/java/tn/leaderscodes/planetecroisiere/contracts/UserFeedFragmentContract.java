package tn.leaderscodes.planetecroisiere.contracts;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;

public class UserFeedFragmentContract {

    public interface View {
        void showFeedsList(List<Publication> publications);
        void feedAdded(Publication publication);
        void uploadImage(mediaResponse mediaResponse);
    }

    public interface Presenter {
        void do_showFeedsList();
        void do_addFeed(Publication publication);
        void do_uploadProfileImage(MultipartBody.Part file, RequestBody name);
    }
}
