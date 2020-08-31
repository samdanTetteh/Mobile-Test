package com.ijikod.uni.ui.Fragments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ijikod.uni.R
import com.ijikod.uni.ui.DataAdapter
import com.ijikod.uni.ui.MainActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContentFeedFragmentTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)



    @Test
    fun `check_recycler_view_exists_and_has_items`(){
        //this test will fail until the exact item count of the list is known and can be asserted
        Espresso.onView(withId(R.id.data_list)).check(CustomAssertions.hasItemCount(9))
    }



    @Test
    fun `check_click_action_works_on_recycler_view`(){
        Espresso.onView(withId(R.id.data_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<DataAdapter.ViewHolder>(5,
                    ViewActions.click()
                ))


        Espresso.onView(withId(R.id.title_entity_txt)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            ))


        Espresso.onView(withId(R.id.desc_edit_txt)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            ))

    }



    /**
     * [CustomAssertions] class to handle recycler view tests
     * **/
    class CustomAssertions {
        companion object {
            fun hasItemCount(count: Int): ViewAssertion {
                return RecyclerViewItemCountAssertion(count)
            }
        }

        private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The asserted view is not RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter is assigned to RecyclerView")
                }

                ViewMatchers.assertThat("RecyclerView item count", view.adapter?.itemCount, CoreMatchers.equalTo(count))
            }
        }
    }
}