public interface DS1Interface {

    /**
     *
     * @param input The list to be sorted using insertion sort.
     * @return A sorted list containing the same elements as 'input'.
     */
    int[] insertionSort(int[] input);


    /**
     *
     * @param input The list to be sorted using merge sort.
     * @return A sorted list containing the same elements as 'input'.
     */
    int[] mergeSort(int[] input);

    /**
     *
     * @param input The list to be sorted using heap sort.
     * @return A sorted list containing the same elements as 'input'.
     */
    int[] heapSort(int[] input);
}
