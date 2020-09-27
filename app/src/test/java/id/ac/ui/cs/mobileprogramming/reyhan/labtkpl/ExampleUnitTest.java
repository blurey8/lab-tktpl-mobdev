package id.ac.ui.cs.mobileprogramming.reyhan.labtkpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void counter_startsWithZero() {
        Counter counter = new Counter();
        assertEquals(counter.getCount(), 0);
    }

    @Test
    public void counter_addCountByOne() {
        Counter counter = new Counter();
        int countBefore = counter.getCount();
        counter.addCount();
        int countAfter = counter.getCount();
        assertEquals(countAfter, countBefore+1);
    }

    @Test
    public void counter_addCountByThree() {
        Counter counter = new Counter();
        int countBefore = counter.getCount();
        counter.addCount();
        counter.addCount();
        counter.addCount();
        int countAfter = counter.getCount();
        assertEquals(countAfter, countBefore+3);
    }
}
