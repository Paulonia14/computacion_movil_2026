package com.example.retrofitpokemon;

import com.example.retrofitpokemon.interfaces.Petitions;
import com.example.retrofitpokemon.models.Pokedex;
import com.example.retrofitpokemon.models.ReturnModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultPokeAPI {
    private static final String API_URL = "https://pokeapi.co/api/v2/";
    private static Retrofit retrofit;
    public ReturnModel returnModel;
    public Petitions consultApi;

    public ConsultPokeAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        returnModel = new ReturnModel();
        consultApi = retrofit.create(Petitions.class);
    }

    public interface OnResultListener {
        void onResult(ReturnModel pokemon);
        void onError(String error);
    }

    public void response(String id, OnResultListener listener){
        Call<Pokedex> call = consultApi.consult(id);

        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pokedex pokedex = response.body();
                    ReturnModel returnModel = new ReturnModel();
                    returnModel.setId(pokedex.getId());
                    returnModel.setName(pokedex.getName());
                    returnModel.setHeight(pokedex.getHeight());
                    returnModel.setWeight(pokedex.getWeight());
                    returnModel.setFrontDefault(pokedex.getFrontDefault());
                    listener.onResult(returnModel);
                } else {
                    listener.onError("Pokemon no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

}
