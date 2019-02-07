package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.remote.request.BoatIdRequest;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;
import tn.leaderscodes.planetecroisiere.remote.response.mediaResponse;

public interface MediaService {
    @POST("userProfile")
    @Multipart
    Observable<mediaResponse> uploadProfilePicture(@Part MultipartBody.Part file,@Part("name") RequestBody name);

    @POST("feedImage")
    @Multipart
    Observable<mediaResponse> uploadFeedPicture(@Part MultipartBody.Part file,@Part("name") RequestBody name);

}
