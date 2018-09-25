package astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BestFirstSearch {

	private SearchNode start;
	private SearchNode goal;
	
	private CustomMinHeap openNodes = new CustomMinHeap();
	private List<SearchNode> allNodes = new ArrayList<>();
	private List<SearchNode> closedNodes = new ArrayList<>();
	
	//Board info
	private int boardWidth = 0;
	private int boardHeight = 0;
	
	/* Scan the whole board, and register all nodes */
	public BestFirstSearch(String board) {
		int xCoordinate = 0;
		int yCoordinate = 0;
		boolean boardWidthFound = false;
		
		for (char character : board.toCharArray()) {
			switch (character) {
				case '.':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 1, '.'));
					xCoordinate++;
					break;
				case '#':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 500, '#'));
					xCoordinate++;
					break;
				case 'A':
					this.start = new SearchNode(xCoordinate, yCoordinate, 0, 'A');
					this.allNodes.add(this.start);
					xCoordinate++;
					break;
				case 'B':
					this.allNodes.add(new SearchNode(xCoordinate, yCoordinate, 1, 'B'));
					this.goal = this.allNodes.get((xCoordinate + (this.boardWidth * yCoordinate)));
					xCoordinate++;
					break;
				case '\n': //New row, y increments downward, x points to first row node
					xCoordinate = 0;
					yCoordinate++;
					break;
			}
			//Log board-info
			if (character == '\n') {
				this.boardHeight += 1;
				boardWidthFound = true;
			} else if (!boardWidthFound){
				this.boardWidth += 1;								
			}
		}
		
		this.openNodes.insertElement(this.start);
		this.calculateManhattanDistance(this.start);
	}
	
	public List<SearchNode> agenda() {
		SearchNode currentNode = this.start;
		while (currentNode != this.goal) {
			if (this.openNodes.isEmpty()) {
				System.out.println("Could not find a path");
				return null;
			}
			currentNode = this.openNodes.heapExtractMin();
			this.closedNodes.add(currentNode);
			
			if (currentNode == this.goal) {
				return success();
			}
			this.discoverSuccessors(currentNode);
			
			for (SearchNode child : currentNode.getChildren()) {
				if (!this.openNodes.contains(child) && !this.closedNodes.contains(child)) {
					this.attachAndEval(child, currentNode);
					this.openNodes.insertElement(child);
				} else if (currentNode.getgCost() + child.getWeight() < child.getgCost()) {
					this.attachAndEval(child, currentNode);
					if (this.closedNodes.contains(child)) {
						this.propagatePathImprovements(child);
					} else if (this.openNodes.contains(child)) {
						this.openNodes.buildMinHeap();
					}
				}
			}
		}
		return null;
	}
	
	public List<SearchNode> getAllNodes() {
		return this.allNodes;
	}
	
	private void propagatePathImprovements(SearchNode node) {
		for (SearchNode child : node.getChildren()) {
			if (node.getgCost() + child.getWeight() < child.getgCost()) {
				child.setParent(node);
				child.setgCost(node.getgCost() + child.getWeight());
				this.propagatePathImprovements(child);
			}
		}
	}
	
	private void attachAndEval(SearchNode child, SearchNode parent) {
		child.setParent(parent);
		child.setgCost(parent.getgCost() + child.getWeight());
		this.calculateManhattanDistance(child);
	}
	
	private void discoverSuccessors(SearchNode parentNode) {
		/* The only possible location of a child is adjacent to the current node
		 *  - In any of the four cardinal directions 
		 */
		
		//Y coordinate is the same
		int westChildXIndex = parentNode.getX() - 1; 
		int EastChildXIndex = parentNode.getX() + 1;
		
		//X coordinate is the same
		int NorthChildYIndex = parentNode.getY() - 1;
		int SouthChildYIndex = parentNode.getY() + 1;
		
		//WestChild:
		if (westChildXIndex >= 0 && westChildXIndex <= this.allNodes.size() - 1) {
			if (parentNode.getParent() != this.allNodes.get(westChildXIndex + (parentNode.getY() * this.boardWidth))) {
				parentNode.addChild(this.allNodes.get(westChildXIndex + (parentNode.getY() * this.boardWidth)));				
			}
		}
		if (EastChildXIndex >= 0 && EastChildXIndex <= this.allNodes.size() - 1) {
			if (parentNode.getParent() != this.allNodes.get(EastChildXIndex + (parentNode.getY() * this.boardWidth))) {
				parentNode.addChild(this.allNodes.get(EastChildXIndex + (parentNode.getY() * this.boardWidth)));				
			}
		}
		if (NorthChildYIndex >= 0 && NorthChildYIndex <= this.boardHeight ) {
			if (parentNode.getParent() != this.allNodes.get(parentNode.getX() + (NorthChildYIndex * this.boardWidth))) {
				parentNode.addChild(this.allNodes.get(parentNode.getX() + (NorthChildYIndex * this.boardWidth)));				
			}
		}
		if (SouthChildYIndex >= 0 && SouthChildYIndex <= this.boardHeight ) {
			if (parentNode.getParent() != this.allNodes.get(parentNode.getX() + (SouthChildYIndex * this.boardWidth))) {
				parentNode.addChild(this.allNodes.get(parentNode.getX() + (SouthChildYIndex * this.boardWidth)));				
			}
		}
	}
	
	/* To solve this problem I will apply the manhattan distance as the heuristic */
	private void calculateManhattanDistance(SearchNode node) {
		int deltaX = Math.abs(this.goal.getX() - node.getX());
		int deltaY = Math.abs(this.goal.getY() - node.getY());
		
		node.sethCost(deltaY + deltaX);
	}
	
	private List<SearchNode> success() {
		SearchNode currentNode = this.goal;
		List<SearchNode> solutionPath = new ArrayList<>();
		
		while (currentNode != this.start) {
			solutionPath.add(currentNode);
			currentNode = currentNode.getParent();
		}
		return solutionPath;
	}
}
