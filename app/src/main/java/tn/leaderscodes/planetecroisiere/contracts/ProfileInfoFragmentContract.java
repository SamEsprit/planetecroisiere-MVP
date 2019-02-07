package tn.leaderscodes.planetecroisiere.contracts;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;

public class ProfileInfoFragmentContract {

    public interface View {
       void uploadProfileImage(mediaResponse mediaResponse);
       void updateMyself(UserResponse userResponse);
    }

    public interface Presenter {
       void do_uploadProfileImage(MultipartBody.Part file, RequestBody name);
       void do_updateMyself(UserRequest userRequest);
    }
}
