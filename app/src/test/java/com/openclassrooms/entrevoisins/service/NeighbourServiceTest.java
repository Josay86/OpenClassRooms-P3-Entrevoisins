package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
       // service.getMyFavorite().addAll(DummyNeighbourGenerator.DUMMY_NEIGHBOURS);
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }


    /**
     * @author Jean-Christophe MAGALHAES MARTINS.
     */
    @Test
    public void createNeighbourWithSuccess() {
        // Récuperer un élément de liste Neighbours
        Neighbour neighbour = service.getNeighbours().get(0);
        //  et l'ajouter
        service.createNeighbour(neighbour);
        // Assurer que la liste dispose bien de l'élement que j'ai ajouter
        assertTrue(service.getNeighbours().contains(neighbour));
    }

     @Test
     public void getFavoriteNeighbourWithSuccess() {
         // Récuperer  un element de la liste favorite
         Neighbour favoriteOne= DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
         service.addFavorite(favoriteOne);
         List<Neighbour> mFavoriteList = service.getMyFavorite();
         // Préciser le type d'élément attendu
         List<Neighbour> expectedFavorite = new ArrayList<>();
         expectedFavorite.add(favoriteOne);
         // Assurer que la liste dispose bien de l'élément souhaité
         assertThat(mFavoriteList, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedFavorite.toArray())));
     }

    @Test
    public void addFavoriteNeighbourWithSuccess(){
        // Récuperer un élément de liste Favorite
        Neighbour favoriteToAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        //  et l'ajouter
        service.addFavorite(favoriteToAdd);
        // Assurer que la liste dispose bien de l'élement que j'ai ajouter
        assertTrue(service.getMyFavorite().contains(favoriteToAdd));

    }

     @Test
     public void deleteFavoriteNeighbourWithSuccess() {
         // Récuperer  un element de la liste favorite
         Neighbour favoriteToDelete = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
         service.addFavorite(favoriteToDelete);
         //  et le suprimmer
         service.deleteFavorite(favoriteToDelete);
         // Assurer que la liste ne dispose pas de l'élement que j'ai supprimer
         assertFalse(service.getMyFavorite().contains(favoriteToDelete));
     }

     // ajouter les fonctions de createNeighbour et addmyfavorite
}
