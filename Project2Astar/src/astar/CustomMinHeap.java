package astar;

import java.util.ArrayList;
import java.util.List;

public class CustomMinHeap {
	/* Min-heap customized for the 'best first search'-A* algorithm */
	
	
	
	private List<SearchNode> minHeap = new ArrayList<SearchNode>();
	
	
	
	
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
	private void minHeapify(List<SearchNode> incompleteHeap, int index) {
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
		
		if (leftChildIndex < incompleteHeap.size() && incompleteHeap.get(leftChildIndex).getfCost() < incompleteHeap.get(index).getfCost()) {
			indexOfSmallest = leftChildIndex;
		} else {
			indexOfSmallest = index;
		}
		
		if (rightChildIndex < incompleteHeap.size() && incompleteHeap.get(rightChildIndex).getfCost() < incompleteHeap.get(indexOfSmallest).getfCost()) {
				indexOfSmallest = rightChildIndex;
		}
		if (indexOfSmallest != index) {
			this.swap(indexOfSmallest, index, incompleteHeap);
			this.minHeapify(incompleteHeap, indexOfSmallest);
		}
		
	}
	
	
	/*Extract the smallest element from the heap. Re-heapify the remaining elements*/
	public SearchNode heapExtractMin() {
		if (this.minHeap.size() < 1) {
			throw new IllegalArgumentException("Heap underflow");
		} 
		SearchNode minElement = this.minHeap.get(0);
		this.minHeap.set(0, this.minHeap.get(this.minHeap.size() - 1));
		this.minHeapify(this.minHeap.subList(0, this.minHeap.size() - 1), 0);
		this.minHeap.remove(this.minHeap.size() - 1);
		return minElement;
	}
	
	public void heapDecreaseKey(int keyIndex, int newfCost) {
		SearchNode decreasingNode = this.minHeap.get(keyIndex);
		if (newfCost < decreasingNode.getfCost()) {
			decreasingNode.setfCost(newfCost);
			while (keyIndex > 0 && this.minHeap.get(keyIndex).getfCost() < this.minHeap.get(this.calculateParentIndex(keyIndex)).getfCost()) {
				this.swap(keyIndex, this.calculateParentIndex(keyIndex), this.minHeap);
				keyIndex = this.calculateParentIndex(keyIndex);
			}
		}
	}
	
	/* Insert new element in the heap and put it in the correct location in the heap */
	public void insertElement(SearchNode element) {
		element.setfCost(element.getfCost() + 1);
		this.minHeap.add(element); //+1 is only to satisfy the heapDecreaseKey-algorithm
		this.heapDecreaseKey(this.minHeap.size() - 1, element.getfCost() - 1);
	}
	
	public boolean isEmpty() {
		return this.minHeap.isEmpty();
	}
	
	/* Swap positions of child and parent in the heap */
	private void swap(int indexChild, int indexParent, List<SearchNode> heap) {
		SearchNode valHolder = heap.get(indexChild);
		heap.set(indexChild, heap.get(indexParent));
		heap.set(indexParent, valHolder);
		this.minHeapify(heap, indexChild);
	}
	
	/* Find the parent */
	private int calculateParentIndex(int childIndex) {
		int parentIndex;
		if (childIndex % 2 == 0) {
			parentIndex = (childIndex / 2) - 1;
		} else {
			parentIndex = (childIndex - 1) / 2;
		}
		return parentIndex;
	}
	
	public boolean contains(SearchNode node) {
		return this.minHeap.contains(node);
	}
	
	
	
	
	
	
	
	
	public String toString() {
		String returnString = "";
		for (SearchNode node : this.minHeap) {
			returnString += node.getfCost();
		}
		
		return returnString;
	}
	
	/* Quick testing of the minheap with SearchNodes to demonstrate how it works:
	public static void main(String[] args) {
		CustomMinHeap minheap = new CustomMinHeap();
		
		SearchNode node1 = new SearchNode(0, 3, 7);
		SearchNode node2 = new SearchNode(1, 4, 3);
		SearchNode node3 = new SearchNode(2, 2, 4);
		SearchNode node4 = new SearchNode(3, 1, 3);
		SearchNode node5 = new SearchNode(4, 0, 2);
		
		node1.setfCost(6);
		node2.setfCost(2);
		node3.setfCost(4);
		node4.setfCost(7);
		node5.setfCost(1);
		
		minheap.insertElement(node1);
		minheap.insertElement(node2);
		minheap.insertElement(node3);
		minheap.insertElement(node4);
		minheap.insertElement(node5);
		
		System.out.println("Initial minheap: " + minheap);
		minheap.heapDecreaseKey(3, 0);
		System.out.println("Heap after decreasing key=7 to key=0: " + minheap);
		System.out.println("Extracted min element: " + minheap.heapExtractMin().getfCost());
		System.out.println("Minheap after min element was extracted: " + minheap);
		System.out.println("Extracted min element: " + minheap.heapExtractMin().getfCost());
		System.out.println("Extracted min element: " + minheap.heapExtractMin().getfCost());
		System.out.println("Extracted min element: " + minheap.heapExtractMin().getfCost());
		System.out.println("Minheap " + minheap);
		
		minheap.insertElement(node1);
		minheap.insertElement(node2);
		minheap.insertElement(node3);
		
		System.out.println("Minheap " + minheap);
		
	} */
}
