package tn.leaderscodes.planetecroisiere.remote;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tn.leaderscodes.planetecroisiere.tools.URLS;

/*
 * @param <T> object click listener
 * @author Leonid Ustenko (Leo.Droidcoder@gmail.com)
 * @since 0.1.0
 *
 */
public class ApiMedia {

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     * @param clazz    Java interface of the retrofit service
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLS.EndPointUploadFile)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = retrofit.create(clazz);

        return service;
    }
}
