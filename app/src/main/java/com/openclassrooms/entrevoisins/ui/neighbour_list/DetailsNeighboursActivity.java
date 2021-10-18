package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author  Jean-Christophe MAGALHAES MARTINS.
 */

public class DetailsNeighboursActivity extends AppCompatActivity{


    // UI components
    @BindView(R.id.activity_details_neighbours_button_favorite)
    FloatingActionButton mButtonFav;
    @BindView(R.id.activity_details_neighbours_button_back)
    FloatingActionButton mButtonBack;
    @BindView(R.id.activity_details_neighbours_avatar)
    ImageView mAvatar;
    @BindView(R.id.activity_details_neighbours_id)
    TextView mDetailsFirstName;
    @BindView(R.id.activity_details_neighbours_name)
    TextView mDetailsName;
    @BindView(R.id.activity_details_neighbours_location)
    TextView mDetailsLocation;
    @BindView(R.id.activity_details_neighbours_phone_number)
    TextView mDetailsPhone;
    @BindView(R.id.activity_details_neighbours_www_address)
    TextView mDetailsAddress;
    @BindView(R.id.activity_details_neighbours_about_details)
    TextView mDetailsAboutDetails;
    @BindView(R.id.activity_details_neighbours_description)
    TextView mDetailsDescription;


    private NeighbourApiService mApiService;
    private Neighbour favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbours);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();


        /*
         *  New intent to import neighbour details from FavoriteNeighbourRecyclerView
         */

        Intent neighboursDetailsIntent = new Intent(this, MyNeighbourRecyclerViewAdapter.class);{
            Intent intent= getIntent();
            favorite = (Neighbour) intent.getSerializableExtra("neighbour");
          /*  Long id= intent.getLongExtra(Neighbour.BUNDLE_DETAILS_ID, 0);
            *String avatar = intent.getStringExtra(Neighbour.BUNDLE_DETAILS_AVATAR);
            *String name= intent.getStringExtra(Neighbour.BUNDLE_DETAILS_NAME);
            *String address= intent.getStringExtra(Neighbour.BUNDLE_DETAILS_ADDRESS);
            *String aboutMe= intent.getStringExtra(Neighbour.BUNDLE_DETAILS_ABOUT_DETAILS);
            *String phoneNumber= intent.getStringExtra(Neighbour.BUNDLE_DETAILS_PHONE);
            */

            /*
             * favorite components
             */
            //favorite= new Neighbour(id,  name,  avatar,  address,  phoneNumber,  aboutMe);


            /*
             * Set the details of each neighbour
             */
            Glide.with(this).load(favorite.getAvatarUrl())
                    .apply(RequestOptions.centerCropTransform()).into(mAvatar);
            mDetailsFirstName.setText(favorite.getName());
            mDetailsName.setText(favorite.getName());
            mDetailsLocation.setText(favorite.getAddress());
            mDetailsDescription.setText(favorite.getAboutMe());
            mDetailsPhone.setText(favorite.getPhoneNumber());
            mDetailsAddress.setText(String.format("www.facebook.fr/%s", favorite.getName()));
            }

        // Change star color if favorite or not
        if(mApiService.getMyFavorite().contains(favorite)) {
            mButtonFav.setImageResource(R.drawable.ic_star_yellow_24dp);
            mButtonFav.setEnabled(false);
        } else {mButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
                mButtonFav.setEnabled(true);
        }
        /*
         * CLick on the button to create add favorite action button
         */
        mButtonFav.setOnClickListener(view -> {
            mApiService.addFavorite(favorite);
            mButtonFav.setImageResource(R.drawable.ic_star_yellow_24dp);
            mButtonFav.setEnabled(false);
        });

        /*
         * Click on the button to create back on Neighbour List action button
         */
        mButtonBack.setOnClickListener(view -> DetailsNeighboursActivity.this.finish());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}