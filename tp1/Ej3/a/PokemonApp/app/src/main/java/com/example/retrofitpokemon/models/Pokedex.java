package com.example.retrofitpokemon.models;

public class Pokedex {
    private String id;
    private String name;
    private String height;
    private String weight;
    private Sprite sprites;

    public Pokedex(String id, String name, String height, String weight, Sprite sprites) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.sprites = sprites;
    }

    public Sprite getSprites() {
        return sprites;
    }

    public void setSprites(Sprite sprites) {
        this.sprites = sprites;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrontDefault() {
        return sprites != null ? sprites.getFrontDefault() : null;
    }

}
