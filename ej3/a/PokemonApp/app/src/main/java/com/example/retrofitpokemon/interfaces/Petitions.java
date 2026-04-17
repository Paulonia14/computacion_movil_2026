package com.example.retrofitpokemon.interfaces;

import com.example.retrofitpokemon.models.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Petitions {
    @GET("pokemon/{id}")
    Call<Pokedex> consult(@Path("id") String id);
}
