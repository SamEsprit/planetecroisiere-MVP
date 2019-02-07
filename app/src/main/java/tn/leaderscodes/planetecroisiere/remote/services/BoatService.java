package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.Boat;
import tn.leaderscodes.planetecroisiere.remote.entities.TypeCabine;
import tn.leaderscodes.planetecroisiere.remote.request.BoatIdRequest;
import tn.leaderscodes.planetecroisiere.remote.request.CompanyBoatRequest;

public interface BoatService {
    @POST("boats/listByCompany")
    Observable<List<Boat>> listByCompany(@Body CompanyBoatRequest companyBoatRequest,@Header("Authorization") String authorization);

    @POST("cabins/mlist")
    Observable<List<TypeCabine>> listTypeCabinsByBoat(@Body BoatIdRequest boatIdRequest,@Nullable @Header("Authorization") String authorization);
}
