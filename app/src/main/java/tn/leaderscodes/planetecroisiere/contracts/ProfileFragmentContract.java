package tn.leaderscodes.planetecroisiere.contracts;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;

public class ProfileFragmentContract {
    public interface View {
        void populateUser(User user);
        void uploadProfileImage(mediaResponse mediaResponse);
        void updateMyself();
    }

    public interface Presenter {
        void do_populateUser();
        void do_uploadProfileImage(MultipartBody.Part file, RequestBody name);
        void do_updateMyself(UserRequest userRequest);
    }
}
