package tn.leaderscodes.planetecroisiere.remote.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tn.leaderscodes.planetecroisiere.remote.entities.Commentaire;
import tn.leaderscodes.planetecroisiere.remote.entities.Publication;
import tn.leaderscodes.planetecroisiere.remote.request.CommentsRequest;
import tn.leaderscodes.planetecroisiere.remote.request.PublicationRequest;

public interface PublicationService {

    @GET("feeds")
    Observable<List<Publication>> list(@Header("Authorization") String authorization);

    @GET("feeds/feed")
    Observable<List<Publication>> userlist(@Header("Authorization") String authorization);

    @POST("feeds")
    Observable<Publication> Add(@Body PublicationRequest publicationRequest, @Header("Authorization") String authorization);

    @POST("feeds/{id}/like")
    Observable<Publication> like(@Header("Authorization") String authorization, @Path("id") String feed_id);

    @DELETE("feeds/{id}/dislike")
    Observable<Publication> dislike(@Header("Authorization") String authorization, @Path("id") String feed_id);

    @POST("feeds/{id}/comments")
    Observable<Commentaire> addComment(@Header("Authorization") String authorization, @Path("id") String feed_id, @Body CommentsRequest commentaire);

    @GET("feeds/{id}/comments")
    Observable<List<Commentaire>> commentList(@Header("Authorization") String authorization, @Path("id") String feed_id);
}
