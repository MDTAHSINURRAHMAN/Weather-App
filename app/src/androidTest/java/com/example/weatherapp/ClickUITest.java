package com.example.weatherapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.weatherapp.Activities.Activities.MainActivity;
import com.example.weatherapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClickUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class) ;

    @Test
    public void testClick() {
        Intents.init();
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.nextBtn)).perform(click());

        onView(withId(R.id.imageView3)).check(matches(isDisplayed())) ;
        Intents.release();
    }

    @Test
    public void testClick2() {
        Intents.init();
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.back_btn)).check(matches(isDisplayed())) ;
        onView(withId(R.id.back_btn)).perform(click()) ;
        onView(withId(R.id.nextBtn)).check(matches(isDisplayed())) ;
        Intents.release();
    }

}
