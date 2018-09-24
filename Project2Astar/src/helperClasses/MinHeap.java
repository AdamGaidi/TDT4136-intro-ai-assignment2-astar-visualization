package helperClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeap {
	/* min-heap data structure to be used as the priority queue in the A*-best first search algorithm */
	
	private List<Integer> minHeap = new ArrayList<Integer>();
	
	public MinHeap(List<Integer> minHeap) {
		this.minHeap = minHeap;
	}
	
	
	public void buildMinHeap() {
		for (int i = (this.minHeap.size() / 2); i >= 0; i--) {
			this.minHeapify(this.minHeap, i);
		}
	}
	/*
	 * Min-heapify algorithm: 
	 * This implementation is only concerned with getting the smallest element to index: 0
	 *  Its not a true implementation of min-heapify 
	 *  The error: The algorithm as it is written here assumes 1-indexing, while java uses 0-indexing
	 */
	private void minHeapify(List<Integer> incompleteHeap, int index) {
		int leftChildIndex;
		int rightChildIndex;
		
		if (index == 0) {
			leftChildIndex = 1;
			rightChildIndex = 2;
		} else {
			leftChildIndex = index * 2;
			rightChildIndex = (index * 2) + 1;			
		}
		
		int indexOfSmallest;
		
		if (leftChildIndex < incompleteHeap.size() && incompleteHeap.get(leftChildIndex) < incompleteHeap.get(index)) {
			indexOfSmallest = leftChildIndex;
		} else {
			indexOfSmallest = index;
		}
		
		if (rightChildIndex < incompleteHeap.size() && incompleteHeap.get(rightChildIndex) < indexOfSmallest) {
				indexOfSmallest = rightChildIndex;
		}
		if (indexOfSmallest != index) {
			int valHolder = incompleteHeap.get(indexOfSmallest);
			incompleteHeap.set(indexOfSmallest, incompleteHeap.get(index));
			incompleteHeap.set(index, valHolder);
			this.minHeapify(incompleteHeap, indexOfSmallest);
		}
		
	}
	
	public int getMin() {
		return this.minHeap.get(0);
	}
	
	/*Extract the smallest element from the heap. Re-heapify the remaining elements*/
	public int heapExtractMin() {
		if (this.minHeap.size() < 1) {
			throw new IllegalArgumentException("Heap underflow");
		} 
		int minElement = this.minHeap.get(0);
		this.minHeap.set(0, this.minHeap.get(this.minHeap.size() - 1));
		this.minHeapify(this.minHeap.subList(0, this.minHeap.size() - 1), 0);
		this.minHeap.remove(this.minHeap.size() - 1);
		return minElement;
	}
	
	public void heapDecreaseKey(int keyIndex, int newValue) {
		//TODO Implement when modifying this class to work with the SearchNode and BestFirstSearch classes
	}
	
	/* Insert new element in the heap and put it in the correct location in the heap */
	public void insertElement(int element) {
		this.minHeap.add(element + 1); //+1 is only to satisfy the heapDecreaseKey-algorithm
		this.heapDecreaseKey(this.minHeap.size() - 1, element);
	}
	
	/* Used in testing */
	public String toString() {
		String returnString = "";
		for (Integer number : this.minHeap) {
			returnString += number;
		}
		return returnString;
	}
	
	
	
	
	/* Just a generic implementation of min-heap so testing is only done with the main-method */ 
	public static void main(String[] args) {
		
		/* Heap with odd number of elements */
		List<Integer> array = Arrays.asList(5,3,1,7,2,4,9);
		List<Integer> heap = new ArrayList<>();
		for (int i = 0; i <= array.size() - 1; i++) {
			heap.add(array.get(i));
		}
		MinHeap minHeap = new MinHeap(heap);
		minHeap.buildMinHeap();
		System.out.println(minHeap);
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap);
		
		/* Test small odd heaps */
		List<Integer> array2 = Arrays.asList(3,1,2);
		List<Integer> heap2 = new ArrayList<>();
		for (int i = 0; i <= array2.size() - 1; i++) {
			heap2.add(array2.get(i));
		}
		minHeap = new MinHeap(heap2);
		minHeap.buildMinHeap();
		System.out.println(minHeap);
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap);
		
		
		/* Heap with even number of elements */
		List<Integer> array3 = Arrays.asList(3,16,5,7,2,1);
		List<Integer> heap3 = new ArrayList<>();
		for (int i = 0; i <= array3.size() - 1; i++) {
			heap3.add(array3.get(i));
		}
		minHeap = new MinHeap(heap3);
		minHeap.buildMinHeap();
		System.out.println(minHeap);
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap);
	
		
		/* Test minimal heap */
		List<Integer> heap4 = new ArrayList<>();
		heap4.add(1);
		minHeap = new MinHeap(heap4);
		minHeap.buildMinHeap();
		System.out.println("\n" + minHeap);
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap);
		
		/* Test small even heaps */
		List<Integer> heap5 = new ArrayList<>();
		heap5.add(5);
		heap5.add(2);
		minHeap = new MinHeap(heap5);
		minHeap.buildMinHeap();
		System.out.println(minHeap);
		System.out.println(minHeap.heapExtractMin());
		System.out.println(minHeap);
		System.out.println(minHeap.heapExtractMin());
		
	}
}
