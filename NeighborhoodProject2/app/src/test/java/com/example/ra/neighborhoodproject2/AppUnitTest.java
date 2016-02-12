package com.example.ra.neighborhoodproject2;

import android.content.res.AssetFileDescriptor;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ra on 2/12/16.
 */
public class AppUnitTest {

    //TODO: having a hard time remembering how to test for my swith case.

    @Test
    public void getDrawableImageTest(){



        int expectedValue= R.drawable.brooklynbridge;
        int actualValue= NeighborhoodSQLiteHelper.getDrawableValue("Brooklyn Bridge");

        Assert.assertEquals(expectedValue, actualValue);




    }
}
