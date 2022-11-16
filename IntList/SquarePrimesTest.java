package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);

        lst = IntList.of(1, 2, 3, 4, 5);
        changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 9 -> 4 -> 25", lst.toString());
        assertTrue(changed);

        lst = IntList.of(6, 7, 8, 9, 10);
        changed = IntListExercises.squarePrimes(lst);
        assertEquals("6 -> 49 -> 8 -> 9 -> 10", lst.toString());
        assertTrue(changed);


    }
    @Test
    public void testSquarePrimesForFalse(){
        IntList lst = IntList.of(14, 15, 16, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 18", lst.toString());
        assertFalse(changed);
    }





}
