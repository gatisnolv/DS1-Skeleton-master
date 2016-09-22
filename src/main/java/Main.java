import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements DS1Interface  {


    /* Implement these methods */

    @Override
    public int[] insertionSort(int[] input) {
        for(int j=1;j<input.length;j++){
        	int key=input[j];//initializations happen in constant time
        	int i=j-1;
        	while(i>=0 && input[i]>key){
        		input[i+1]=input[i];
        		i=i-1;
        	}
        	input[i+1]=key;
        }
    	return input;
    }


    @Override
    public int[] mergeSort(int[] input) {
    	if(input.length>1){
        	int q=input.length/2;
        	int[] left=mergeSort(copySubArray(input,0,q));
        	int[] right=mergeSort(copySubArray(input,q,input.length));
        	input=merge(left, right);
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
    		if(l<left.length && r<right.length && left[l]<=right[r] || r==right.length){
    			output[i]=left[l];
    			l+=1;
    		}else{
    			output[i]=right[r];
    			r+=1;
    		}
    	}
    	return output;
    }

	void buildMaxHeap(int[] heap){
    	for(int i=heap.length/2-1;i>=0;i--){
    		maxHeapify(heap, i, heap.length);
    	}
    }
	    
    void maxHeapify(int[] heap, int i, int heapSize){
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
    		int temp=heap[i];
    		heap[i]=heap[largest];
    		heap[largest]=temp;
    		maxHeapify(heap, largest, heapSize);
    	}
    }

//	    @Override
    public int[] heapSort(int[] input){
    	buildMaxHeap(input);
    	int heapSize=input.length;
    	for(int i=input.length;i>1;i--){
    		int temp=input[0];
    		input[0]=input[i-1];
    		input[i-1]=temp;
    		heapSize=heapSize-1;
    		maxHeapify(input, 0, heapSize);
    	}
        return input;
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
