package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import tn.leaderscodes.planetecroisiere.remote.entities.Reservation;
import tn.leaderscodes.planetecroisiere.remote.request.ReservationRequest;

public interface ReservationService {
    @POST("reservation")
    Observable<Reservation> addReservation(@Body ReservationRequest reservationRequest, @Header("Authorization") String authorization);

    @GET("reservation")
    Observable<List<Reservation>> list( @Header("Authorization") String authorization);
}
