package tn.leaderscodes.planetecroisiere.remote.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import tn.leaderscodes.planetecroisiere.remote.entities.User;
import tn.leaderscodes.planetecroisiere.remote.request.UserRequest;
import tn.leaderscodes.planetecroisiere.remote.response.UserResponse;

public interface UserService {
    @POST("users/login")
    Observable<UserResponse> login(@Body UserRequest user);

    @POST("users")
    Observable<User> register(@Body UserRequest user);

    @PUT("user")
    Observable<UserResponse> updateMyself(@Body UserRequest user, @Header("Authorization") String authorization);

    @GET("user")
    Observable<UserResponse> me(@Header("Authorization") String authorization);

}
