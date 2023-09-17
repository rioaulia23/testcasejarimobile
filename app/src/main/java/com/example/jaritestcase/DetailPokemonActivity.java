package com.example.jaritestcase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.jaritestcase.api.ApiClient;
import com.example.jaritestcase.api.ApiService;
import com.example.jaritestcase.databinding.ActivityDetailPokemonBinding;
import com.example.jaritestcase.responses.PokemonDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPokemonActivity extends AppCompatActivity {

    ActivityDetailPokemonBinding binding;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPokemonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
        Intent intent = getIntent();
        String data = intent.getStringExtra("id");
        getPokemonDetail(data);

    }

    private void getPokemonDetail(String id){
        apiService.getCards(id).enqueue(new Callback<PokemonDetailResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailResponse> call, Response<PokemonDetailResponse> response) {
                if(response.isSuccessful()){
                    Glide.with(getApplicationContext())
                            .load(response.body().getData().getImages().getLarge())
                            .into(binding.detailImage);
                    binding.detailName.setText(response.body().getData().getName());
                    binding.detailType.setText(response.body().getData().getTypes().get(0).toString());
                    binding.tvAttack.setText(response.body().getData().getAttacks().get(0).getDamage());
                    binding.tvWeak.setText(response.body().getData().getWeaknesses().get(0).getType());
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailResponse> call, Throwable t) {

            }
        });
    }
}