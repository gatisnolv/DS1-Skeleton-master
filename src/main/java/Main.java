import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements DS1Interface  {


	/* General comments that apply to all methods:
 	 * 	1) Variable initialization takes constant time.
	 * 	2) We tried to keep the algorithms as close to the provided (book and lectures) pseudo-code as possible,
	 *	so some of the for-loops have a counter that is decreasing, which is not typical of prior programming 
	 *	assignments, but nevertheless works correctly
	 *	3) The pseudo-code of the algorithms assumes that the first element of an array has index '1', whereas 
	 *	in Java arrays start with the index '0', so where relevant we initialized counters in loops that 
	 *	are 1 less than those used in the pseudo-code (loops that have an increasing counter), also the 
	 *	computation of child nodes in heapSort is slightly different because of this
	 *	4) Some of the pseudo-code algorithms call for 'flooring' of a non-integer result of a division, in 
	 *	Java this is easy because the result of dividing two int's dismisses the remainder of the division
	 */

	
    @Override
    public int[] insertionSort(int[] input) {
        for(int j=1;j<input.length;j++){//test of for-loop happens n times
        	int key=input[j];
        	int i=j-1;
        	while(i>=0 && input[i]>key){//worst case: input[i]>key always succeeds, which makes insertionSort take time, which is quadratic in input size
        		input[i+1]=input[i];
        		i=i-1;
        	}
        	input[i+1]=key;
        }
    	return input;
    }//because insertionSort takes time, which is quadratic in input size, its worst case time complexity is in O(n^2)


    /* 	General comments about our mergeSort implementation:
     * Because the signature of the mergeSort method had a different signature from the pseudo-code,
     * we adapted our implementation to account for that (the required implementation only takes an 
     * integer array as input, as opposed to the pseudo-code method which takes the initial array, 
     * and pointers about which region to sort).
     * This meant that we had to pass in the recursive calls arrays that are copies of the regions, to be sorted.
     * 	For the purpose of making copies of the relevant regions we created a helper method copySubArray, 
     * which returns an array which is a copy of the specified region.
     * 	Because of the difference in mergeSort implementation, we also needed to adapt the 'merge' method. It 
     * differs from the pseudo-code in that it does not take the initial array along with pointers to the 
     * regions to be merged, but instead takes two sorted arrays which are copies of the regions and returns 
     * an array which contains all of the elements of the passed arrays and is sorted instead of returning the 
     * original array with a sorted region.
     * 	The use of the copySubArray method does not introduce differences to the time complexity or memory use 
     * of the implementation, because similarly, the same operation of copying the regions to temporary arrays 
     * happens within the 'merge' method in the pseudo-code.
     * 	Our implementation does not use sentinels in the 'merge' method, this is circumvented by using additional 
     * checks in the if-statement when deciding from which array to copy
     * 
     * 	Because mergeSort calls itself recursively on the halves of the input array, the height of the recursion 
     * tree is ~log(n) (for n=2^1 log(n)+1).
     * 	Work per level is in the Theta(n).
     *  This results in the time complexity of mergeSort being in Theta(n*log(n))
     */

    @Override
    public int[] mergeSort(int[] input) {
    	if(input.length>1){
        	int q=input.length/2;//indicates the middle (floor of length/2)
        	int[] left=mergeSort(copySubArray(input,0,q));//mergeSort 'half' of the array from index 0 to q (excluded)
        	int[] right=mergeSort(copySubArray(input,q,input.length));//mergeSort other 'half' of the array from q to the end of array
        	input=merge(left, right);//merges the two 'halves'
        }
    	return input;
    }
    
    int[] copySubArray(int[] source, int start, int end){
    	int[] result=new int[end-start];
    	for(int i=0;i<end-start;i++){
    		result[i]=source[start+i];
    	}
    	return result;
    }
    
    int[] merge(int[] left, int[] right){
    	int[] output=new int[left.length+right.length];
    	int l=0;
    	int r=0;
    	for(int i=0;i<output.length;i++){
    		if(l<left.length && r<right.length && left[l]<=right[r] || r==right.length){//the additional checks for the counters allow us to avoid using sentinels
    			output[i]=left[l];
    			l+=1;
    		}else{
    			output[i]=right[r];
    			r+=1;
    		}
    	}
    	return output;
    }

    /* 	General comments about our heapSort implementation:
     * We implemented heapSort very close to the pseudo-code of the algorithm, hence there are no space or 
     * time complexity differences. 
     * The only difference is that our maxHeapify implementation in addition to the parameters in the 
     * pseudo-code, also takes heapSize as a parameter, where as the pseudo-code assumes this to be a 
     * property of the input array.
     * 
     * Becase of the for-loop and the time complexity of maxHeapify, the time complexity of heapSort is in O(n*log(n)),
     * (details below)
     */
    
    @Override
    public int[] heapSort(int[] input) {
    	buildMaxHeap(input);//worst case running time in O(n), same as the pseudo-code
    	int heapSize=input.length;
    	for(int i=input.length;i>1;i--){//n-1 calls of maxHeapify
    		int temp=input[0];//the following 3 lines swap the first element of a max-heap with the last
    		input[0]=input[i-1];
    		input[i-1]=temp;
    		heapSize=heapSize-1;
    		maxHeapify(input, 0, heapSize);
    	}
        return input;
    }
    
    void buildMaxHeap(int[] heap){
    	for(int i=heap.length/2-1;i>=0;i--){
    		maxHeapify(heap, i, heap.length);
    	}
    }
	    
    void maxHeapify(int[] heap, int i, int heapSize){//time complexity determined by height of heap, thus O(log(n))
    	int l=2*(i+1)-1;
    	int r=2*(i+1);
    	int largest;
    	if(l<heapSize && heap[l]>heap[i]){
    		largest=l;
    	}else{
    		largest=i;
    	}
    	if(r<heapSize && heap[r]>heap[largest]){
    		largest=r;
    	}
    	if(largest!=i){
    		int temp=heap[i];//the following 3 lines swap the i element with the largest
    		heap[i]=heap[largest];
    		heap[largest]=temp;
    		maxHeapify(heap, largest, heapSize);
    	}
    }

    /* BEGIN UTIL FUNCTION. DO NOT TOUCH */

    int[] readInput(String file) throws FileNotFoundException {

        InputStream inputStream;
        if (file == null) {
            inputStream = System.in;
        } else {
            inputStream = new FileInputStream(file);
        }
        Scanner in = new Scanner(inputStream);

        List<Integer> list = new ArrayList<Integer>();
        while (in.hasNext()) {
            int e = in.nextInt();
            list.add(e);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    void start(String algorithm, String file) throws FileNotFoundException {
        int[] toBeSorted = readInput(file);
        int[] sortedResult = null;
        switch (algorithm) {
            case "insertion":
                sortedResult = insertionSort(toBeSorted);
                break;
            case "merge":
                sortedResult = mergeSort(toBeSorted);
                break;
            case "heap":
                sortedResult = heapSort(toBeSorted);
                break;
            default:
                System.out.printf("Invalid algorithm provided: %s\n", algorithm);
                printHelp(null);
                System.exit(1);
        }

        printArray(sortedResult);
    }

    static void printArray(int[] array) {
        for (int e: array) {
            System.out.printf("%d ", e);
        }
    }

    static void printHelp(String[] argv) {
        System.out.printf("Usage: java -jar DS1.jar <algorithm> [<input_file>]\n");
        System.out.printf("\t<algorithm>: insertion, merge, heap\n");
        System.out.printf("E.g.: java -jar DS1.jar insertion example.txt\n");
    }

    public static void main(String argv[]) {
        if (argv.length == 0) {
            printHelp(argv);
        }
        try {
            (new Main()).start(argv[0], argv.length < 2 ? null : argv[1]);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getMessage());
        }

    }

}
