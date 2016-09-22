import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;


public class SortTest {

    DS1Interface instance;

    @Before
    public void setUp() {
        instance  = new Main();
    }

    private void testInsertion(int[] input) {
        int[] sorted = new int[input.length];
        System.arraycopy(input, 0, sorted, 0, input.length);
        Arrays.sort(sorted);

        int[] output = instance.insertionSort(input);
        assertArrayEquals(sorted, output);
    }

    private void testMerge(int[] input) {
        int[] sorted = new int[input.length];
        System.arraycopy(input, 0, sorted, 0, input.length);
        Arrays.sort(sorted);

        int[] output = instance.mergeSort(input);
        assertArrayEquals(sorted, output);

    }

    private void testHeap(int[] input) {
        int[] sorted = new int[input.length];
        System.arraycopy(input, 0, sorted, 0, input.length);
        Arrays.sort(sorted);

        int[] output = instance.heapSort(input);
        assertArrayEquals(sorted, output);

    }

    /* INSERTION SORT */

    @Test(timeout=100)
    public void testSimpleInsertionSort() {
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        testInsertion(input);


        int[] input2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testInsertion(input2);
    }


    /* MERGE SORT */

    @Test(timeout=100)
    public void testSimpleMergeSort() {
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        testMerge(input);


        int[] input2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testMerge(input2);
    }




    /* HEAP SORT */

    @Test(timeout=100)
    public void testSimpleHeapSort() {
        int[] input = {1,2,3,4,5,6,7,8,9,10};
        testHeap(input);


        int[] input2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        testHeap(input2);
    }

}
