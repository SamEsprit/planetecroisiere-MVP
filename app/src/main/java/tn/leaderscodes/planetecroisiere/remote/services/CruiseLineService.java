package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.CruiseLine;


public interface CruiseLineService {

    @POST("companie/mlist")
    Observable<List<CruiseLine>> list(@Header("Authorization") String authorization);

}
