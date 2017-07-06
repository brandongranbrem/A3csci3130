package com.acme.a3csci3130;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UpdateUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void updateUITest() {

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("test7"),
                        childAtPosition(
                                withId(R.id.listView),
                                0),
                        isDisplayed()));
        textView.perform(click());


        ViewInteraction editText = onView(
                allOf(withId(R.id.province), isDisplayed()));
        editText.perform(replaceText("NS"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.name), isDisplayed()));
        editText2.perform(replaceText("updated"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.updateButton), isDisplayed()));
        button.perform(click());


        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("updated"),
                        childAtPosition(
                                withId(R.id.listView),
                                0),
                        isDisplayed()));
        textView4.perform(click());


        ViewInteraction editText3 = onView(
                allOf(withId(R.id.province),
                        isDisplayed()));
        editText3.check(matches(withText("NS")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.province), isDisplayed()));
        editText4.perform(replaceText("NB"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.name), isDisplayed()));
        editText5.perform(replaceText("test7"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.updateButton), isDisplayed()));
        button2.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
