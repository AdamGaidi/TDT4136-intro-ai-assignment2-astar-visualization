package astar;

import java.util.ArrayList;
import java.util.List;

public class BestFirstSearch {

	private SearchNode start;
	private List<SearchNode> allNodes = new ArrayList<>();
	
	private CustomMinHeap openNodes = new CustomMinHeap();
	private List<SearchNode> closedNodes = new ArrayList<>();
	
	public BestFirstSearch(String board) {
		int xCoordinate = 0;
		int yCoordinate = 0;
		
		for (char character : board.toCharArray()) {
			switch (character) {
				case '.':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 1));
					xCoordinate++;
					break;
				case '#':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 500));
					xCoordinate++;
					break;
				case 'A':
					this.start = new SearchNode(xCoordinate, yCoordinate, 0);
					this.allNodes.add(this.start);
					xCoordinate++;
					break;
				case 'B':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 1));
					this.allNodes.get((xCoordinate + (10 * yCoordinate)) - 1).markAsGoal();
					xCoordinate++;
					break;
				case '\n':
					xCoordinate = 0;
					yCoordinate++;
					break;
			}
		}
		//Insert start-node into openNodes and start the algorithm
	}
	
	public void calculateExpectedDistanceToGoal() {
		
	}
	
	public void start() {
		
	}
}
