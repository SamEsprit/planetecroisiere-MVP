package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.Place;

public interface PlaceService {
    @POST("places/mlist")
    Observable<List<Place>> list(@Header("Authorization") String authorization);
}
