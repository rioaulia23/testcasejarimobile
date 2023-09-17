package com.example.jaritestcase.responses;

import com.example.jaritestcase.models.Pokemon;


import java.util.List;

public class PokemonResponse {
    public List<Pokemon> data;

    public List<Pokemon> getData() {
        return data;
    }

    public void setData(List<Pokemon> data) {
        this.data = data;
    }
}
