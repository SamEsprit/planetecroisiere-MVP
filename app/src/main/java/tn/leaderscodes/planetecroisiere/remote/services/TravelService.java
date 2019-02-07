package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.Travel;
import tn.leaderscodes.planetecroisiere.remote.request.TravelRequest;


public interface TravelService {

    @POST("travels/mlistByConditionLimit5")
    Observable<List<Travel>> mlistByConditionLimit5(@Body TravelRequest travelRequest,@Header("Authorization") String authorization);
    @POST("travels/mlistByFilter")
    Observable<List<Travel>> mlistByFilter(@Body TravelRequest travelRequest,@Header("Authorization") String authorization);
}
