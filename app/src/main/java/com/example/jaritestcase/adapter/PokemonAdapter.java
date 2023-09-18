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

import java.io.File;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private final List<Pokemon> pokemonList;
    private final Context context;
//    private final boolean isConnectedToInternet;

//    private final PokemonDatabase dbHelper;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public PokemonAdapter(List<Pokemon> data, Context context) {
        this.pokemonList = data;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon card = pokemonList.get(position);

        holder.cardNameTextView.setText(card.getName());
        Glide.with(context)
                .load(card.getImages().getSmall())
                .into(holder.cardImageView);

        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(card);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
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

    public void setCards(List<Pokemon> newCards) {
        this.pokemonList.clear();
        this.pokemonList.addAll(newCards);
        notifyDataSetChanged();
    }

    public void addCards(List<Pokemon> newCards) {
        int oldSize = this.pokemonList.size();
        this.pokemonList.addAll(newCards);
        notifyItemRangeInserted(oldSize, newCards.size());
    }
}


