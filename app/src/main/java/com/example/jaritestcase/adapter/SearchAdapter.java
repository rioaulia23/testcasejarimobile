package com.example.jaritestcase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jaritestcase.R;
import com.example.jaritestcase.models.Pokemon;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Pokemon> searchResults;
    private Context context;

    public SearchAdapter(List<Pokemon> searchResults, Context context ) {
        this.searchResults = searchResults;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = searchResults.get(position);
        holder.cardNameTextView.setText(pokemon.getName());

        Glide.with(context)
                .load(pokemon.getImages().getSmall())
                .into(holder.cardImageView);


    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView cardImageView;
        private final TextView cardNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.imageCard);
            cardNameTextView = itemView.findViewById(R.id.textName);
        }
    }
}

