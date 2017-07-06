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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeleteUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void deleteUITest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.submitButton), isDisplayed()));
        button.perform(click());


        ViewInteraction editText = onView(
                allOf(withId(R.id.businessNumber), isDisplayed()));
        editText.perform(replaceText("123456789"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.name), isDisplayed()));
        editText2.perform(replaceText("deleteMe"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.primaryBusiness), isDisplayed()));
        editText3.perform(replaceText("fisher"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.province), isDisplayed()));
        editText4.perform(replaceText("BC"), closeSoftKeyboard());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.submitButton), isDisplayed()));
        button2.perform(click());


        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("deleteMe"),
                        childAtPosition(
                                withId(R.id.listView),
                                3),
                        isDisplayed()));
        textView.perform(click());


        ViewInteraction button3 = onView(
                allOf(withId(R.id.deleteButton), isDisplayed()));
        button3.perform(click());


        ViewInteraction button4 = onView(
                allOf(withId(R.id.submitButton), isDisplayed()));
        button4.perform(click());


        ViewInteraction editText5 = onView(
                allOf(withId(R.id.name), isDisplayed()));
        editText5.perform(replaceText("deleted"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.businessNumber), isDisplayed()));
        editText6.perform(replaceText("000000000"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.primaryBusiness), isDisplayed()));
        editText7.perform(replaceText("fisher"), closeSoftKeyboard());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.submitButton), isDisplayed()));
        button5.perform(click());


        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("deleted"),
                        childAtPosition(
                                allOf(withId(R.id.listView),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                3),
                        isDisplayed()));
        textView2.check(matches(withText("deleted")));

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("deleted"),
                        childAtPosition(
                                withId(R.id.listView),
                                3),
                        isDisplayed()));
        textView3.perform(click());


        ViewInteraction button6 = onView(
                allOf(withId(R.id.deleteButton), isDisplayed()));
        button6.perform(click());

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
