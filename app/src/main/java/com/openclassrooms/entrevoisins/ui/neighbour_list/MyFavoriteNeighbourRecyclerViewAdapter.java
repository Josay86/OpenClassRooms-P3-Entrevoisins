package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteMyFavoriteNeighbourEvent;

import com.openclassrooms.entrevoisins.model.Neighbour;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author  Jean-Christophe MAGALHAES MARTINS.
 * RecyclerView for the favorite neighbours list
 */

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.MyFavoriteViewHolder>{

    private final List<Neighbour> mNeighbours;

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @NonNull
    @Override
    public MyFavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite_neighbour_item, parent, false);
        return new MyFavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyFavoriteViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

       holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteMyFavoriteNeighbourEvent(neighbour)));
        /*
         * Put Extras to Details neighbours
         */
        holder.itemView.setOnClickListener(v -> {
            Intent neighboursDetailsIntent = new Intent(v.getContext(), DetailsNeighboursActivity.class);
            neighboursDetailsIntent.putExtra("neighbour", neighbour);
            /*neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_ID, neighbour.getId());
            neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_AVATAR, neighbour.getAvatarUrl());
            neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_NAME, neighbour.getName());
            neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_PHONE, neighbour.getPhoneNumber());
            neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_ADDRESS, neighbour.getAddress());
            neighboursDetailsIntent.putExtra(Neighbour.BUNDLE_DETAILS_ABOUT_DETAILS, neighbour.getAboutMe());**/

            ContextCompat.startActivity(v.getContext(), neighboursDetailsIntent, null  );
            });
    }



    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public static class MyFavoriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button_fav)
        public ImageButton mDeleteButton;

        public MyFavoriteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

