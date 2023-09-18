package com.example.jaritestcase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.jaritestcase.adapter.PokemonAdapter;
import com.example.jaritestcase.adapter.SearchAdapter;
import com.example.jaritestcase.api.ApiClient;
import com.example.jaritestcase.api.ApiService;


import com.example.jaritestcase.databinding.ActivityMainBinding;
import com.example.jaritestcase.models.Pokemon;
import com.example.jaritestcase.responses.PokemonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ApiService apiService;
    PokemonAdapter pokemonAdapter;
    SearchAdapter searchAdapter;
    private int currentPage = 1;
    private int pageSize = 20;
    private final List<Pokemon> cards = new ArrayList<>();
    private boolean isLoading = false;
    private final List<Pokemon> searchResults = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
        fetchDataFromJson();


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch(binding.searchView.getText().toString());
            }
        });
        searchAdapter = new SearchAdapter(searchResults, this);

        binding.recyclerSearch.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerSearch.setAdapter(searchAdapter);

        binding.recyclerSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerMain, int dx, int dy) {
                super.onScrolled(recyclerMain, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerMain.getLayoutManager();
                assert layoutManager != null;
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    loadNextPageSearch();
                }
            }
        });

    }

    private void fetchDataFromJson() {
        Call<PokemonResponse> call = apiService.searchCards("", 1, pageSize);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {

                    binding.recyclerMain.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                    pokemonAdapter = new PokemonAdapter(response.body().data, getApplicationContext());

                    binding.recyclerMain.setAdapter(pokemonAdapter);
                    pokemonAdapter.setItemClickListener(pokemon -> {
                        Intent intent = new Intent(MainActivity.this, DetailPokemonActivity.class);
                        intent.putExtra("id", pokemon.id);
                        startActivity(intent);


                    });
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
//
        });

//
        binding.recyclerMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerMain, int dx, int dy) {
                super.onScrolled(recyclerMain, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerMain.getLayoutManager();
                assert layoutManager != null;
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    loadNextPageMain();
                }
            }
        });


    }

    private void loadNextPageMain() {
        currentPage++;
        isLoading = true;


        Call<PokemonResponse> call = apiService.searchCards("", currentPage, 20);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                isLoading = false;

                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    if (pokemonResponse != null) {
                        List<Pokemon> newCards = pokemonResponse.getData();
                        pokemonAdapter.addCards(newCards);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                isLoading = false;
                t.printStackTrace();
            }
        });
    }

    private void showSearchResults(List<Pokemon> results) {
        searchResults.clear();
        searchResults.addAll(results);
        searchAdapter.notifyDataSetChanged();
        binding.recyclerSearch.setVisibility(View.VISIBLE);
    }

    private void performSearch(String query) {

        Call<PokemonResponse> call = apiService.searchCards("name:" + query, 1, pageSize);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                isLoading = false;

                if (response.isSuccessful()) {
                    binding.recyclerMain.setVisibility(View.GONE);
                    PokemonResponse pokemonResponse = response.body();
                    if (pokemonResponse != null) {
                        List<Pokemon> searchResults = pokemonResponse.getData();
                        showSearchResults(searchResults);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                isLoading = false;
                t.printStackTrace();
            }
        });
    }

    private void loadNextPageSearch() {
//        progressDialogHelper.show();
        currentPage++;
        isLoading = true;

        Call<PokemonResponse> call = apiService.searchCards("", currentPage, 20);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                isLoading = false;

                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    if (pokemonResponse != null) {
                        List<Pokemon> newCards = pokemonResponse.getData();
                        pokemonAdapter.addCards(newCards);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                isLoading = false;
                t.printStackTrace();
            }
        });
    }


}