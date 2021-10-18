
package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static final int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more show */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * @author Jean-Christophe Magalhaes Martins
     */

    @Test
    public void checkDetailsNeighboursActivityLaunch() {
        // Click on the first item of the list
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //We check that the elements of this new activity exist
        onView(withId(R.id.activity_details_neighbours_name)).check(matches(isDisplayed()));

    }

    @Test
    public void myFavoriteNeighboursList_shouldNotBeEmpty() {
        // view neighbours list
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // click on first neighbour
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // add the neighbour to favorites
        onView(withId(R.id.activity_details_neighbours_button_favorite)).perform(click());
        // back to neighbours list
        onView(withId(R.id.activity_details_neighbours_button_back)).perform(click());
        // view on neighbours list
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // slide to favorites
        onView(withId(R.id.container)).perform(scrollRight());
        // view on favorites list
        onView(withId(R.id.list_favorite_neighbours)).check(matches(isDisplayed()));
        // check if we got the favorite added
        onView(withId(R.id.list_favorite_neighbours))
               .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void checkDetailsNeighboursTextView_shouldNotBeEmpty() {
        // click on an item of list neighbour
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //We check if TextView element of DetailsNeighbours exist
        onView(ViewMatchers.withId(R.id.activity_details_neighbours_name)).check(matches(withText("Caroline")));
    }

    @Test
    public void myFavoriteNeighboursList_deleteAction_shouldRemoveItem() {
        // view neighbours list
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // click on first neighbour
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        // add the neighbour to favorites
        onView(withId(R.id.activity_details_neighbours_button_favorite)).perform(click());
        // back to neighbours list
        onView(withId(R.id.activity_details_neighbours_button_back)).perform(click());
        // view on neighbours list
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
        // slide to favorites
        onView(withId(R.id.container)).perform(scrollRight());
        // view on favorites list
        onView(withId(R.id.list_favorite_neighbours)).check(matches(isDisplayed()));
        // check if we got the favorite added
        onView(withId(R.id.list_favorite_neighbours))
                .check(matches(hasMinimumChildCount(1)));
        // initiate with item count
        onView(withId(R.id.list_favorite_neighbours)).check(withItemCount(1));
        // click on a delete icon
        onView(withId(R.id.item_list_delete_button_fav)).perform(click());
        // then : the number of element is 0
        onView(withId(R.id.list_favorite_neighbours)).check(withItemCount(0));
    }

    @Test
    public void myFavoriteNeighbourList_onlyContainsFavoriteNeighbours() {
        {
            // view on neighbours list
            onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
            // click on first neighbour
            onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            // add the neighbour to favorites
            onView(withId(R.id.activity_details_neighbours_button_favorite)).perform(click());
            // back to neighbours list
            onView(withId(R.id.activity_details_neighbours_button_back)).perform(click());
            // view on neighbours list
            onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));
            // slide to favorites
            onView(withId(R.id.container)).perform(scrollRight());
            // view on favorites list
            onView(withId(R.id.list_favorite_neighbours)).check(matches(isDisplayed()));
            // view on favorites list
            onView(withId(R.id.list_favorite_neighbours))
                    .check(withItemCount(1));


        }
    }
 }



