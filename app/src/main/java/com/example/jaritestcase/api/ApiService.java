package com.example.jaritestcase.api;



import com.example.jaritestcase.responses.PokemonDetailResponse;
import com.example.jaritestcase.responses.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cards")
    Call<PokemonResponse> searchCards(
            @Query("q") String query,
            @Query("page") int page,
            @Query("pageSize") int pageSize
    );

    @GET("cards/{id}")
    Call<PokemonDetailResponse> getCards(
            @Path("id") String id
    );
}
