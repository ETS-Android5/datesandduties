package com.example.datesandduties;

import android.widget.EditText;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testAddEvent()
    {
        addEvent ev = Mockito.mock(addEvent.class);
        EditText title = Mockito.mock(EditText.class);
        title.setText("Testing");

        ev.inputTitle = title;
        EditText description = Mockito.mock(EditText.class);
        description.setText("This is for the Mockito test");

        ev.inputDesc = description;

        Assert.assertEquals(title, ev.inputTitle);
        Assert.assertEquals(description, ev.inputDesc);

    }
}