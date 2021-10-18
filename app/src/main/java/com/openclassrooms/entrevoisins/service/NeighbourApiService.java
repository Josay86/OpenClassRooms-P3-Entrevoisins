package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour Neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour Neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * @author  Jean-Christophe MAGALHAES MARTINS.
     * Get all my Favorite Neighbours
     * @return {@link List}
     */
     List<Neighbour> getMyFavorite();

    /**
     * Create a Favorite Neighbour
     * @param neighbour Neighbour
     */
    void addFavorite(Neighbour neighbour);

    /**
     * Delete a Favorite Neighbour
     * @param neighbour Neighbour
     */
    void deleteFavorite(Neighbour neighbour);
}
