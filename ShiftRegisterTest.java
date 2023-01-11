import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest", expected[i], r.generate(3));
        }
    }

    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };

        r.setSeed(seed);
        // ERROR THROWN


        // Proper response: An error should be thrown when calling r.setSeed(seed)
        // where the code should check whether the length of seed == the size parameter provided in getRegister.

        // A proper test for this should include what happens when the supplied seed is both shorter and longer
        // than the size parameter.
    }

    /**
     * Tests with more erroneous seed.
     */
    @Test
    public void testError2() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 1 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }

    @Test
    public void testSizeNegative() {
        ILFShiftRegister r = getRegister(-1, 1);
    }
    @Test
    public void testTapNegative() {
        ILFShiftRegister r = getRegister(4, -1);
    }
    @Test
    public void testTapTooBig() {
        ILFShiftRegister r = getRegister(4, 8);
    }

}
