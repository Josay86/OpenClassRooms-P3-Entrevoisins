package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position Position
     * @return Return
     */
    @Override
    public Fragment getItem(int position)
    {
       if(position==0)  return NeighbourFragment.newInstance();
       else return (Fragment) MyFavoriteNeighbourFragment.addFavorite();
    }

    /**
     * get the number of pages
     * @return Return
     */
    @Override
    public int getCount() {
        return 2;
    }
}