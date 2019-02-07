package tn.leaderscodes.planetecroisiere.remote.services;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.SubRegion;
import tn.leaderscodes.planetecroisiere.remote.response.SubRegionResponse;

public interface SubRegionService {

    @POST("subRegions/mlist")
    Observable<List<SubRegion>> list(@Header("Authorization") String authorization);
    @POST("subRegions/mlistByGroupe")
    Observable<List<SubRegionResponse>> listByGroupe(@Header("Authorization") String authorization);
}
