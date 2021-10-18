package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;


import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class   DummyNeighbourApiService implements  NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private final List<Neighbour> myFavoriteList = new ArrayList<>(); // init a empty Favorite List //

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour)
    {
        neighbours.remove(neighbour);
        myFavoriteList.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * @author  Jean-Christophe MAGALHAES MARTINS
     * Develop the Favorite Neighbour API methods from NeighbourApiService Interface

     * {@inheritDoc}
     * Create the favorite List
     */
    @Override
    public List<Neighbour> getMyFavorite() {
        return myFavoriteList;
    }

    /**
     * {@inheritDoc}
     * Add a neighbour to favorite list
     */
    @Override
    public void addFavorite(Neighbour neighbour) {
        if(!myFavoriteList.contains(neighbour))
            myFavoriteList.add(neighbour);
    }

    /**
     * {@inheritDoc}
     * Delete a neighbour from favorite list
     */
   public void deleteFavorite(Neighbour neighbour) {
        myFavoriteList.remove(neighbour);
    }

}
