package com.example.datesandduties;

import android.content.Context;
import android.widget.EditText;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    private Context mockTest = null;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void setMock()
    {
        mockTest = Mockito.mock(Context.class);
        Assert.assertNotNull("not null", mockTest);
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