package testapp.com.youpod;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.test.InstrumentationTestCase;

import testapp.com.youpod.YoutubeListManager;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    // create  a signal to let us know when our task is done.
    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void addition_isCorrect() throws Exception
    {
        //YoutubeListManager youtubeList = new YoutubeListManager();
    }
}