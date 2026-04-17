package com.example.retrofitpokemon.models;

import com.google.gson.annotations.SerializedName;

public class Sprite {
    @SerializedName("front_default")
    public String frontDefault;

    public Sprite(String frontDefault){
        this.frontDefault = frontDefault;
    }

    public String getFrontDefault(){
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault){
        this.frontDefault = frontDefault;
    }
}
