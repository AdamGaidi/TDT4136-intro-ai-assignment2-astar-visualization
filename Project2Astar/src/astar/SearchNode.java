package astar;

import java.util.ArrayList;
import java.util.List;

public class SearchNode {
	
	private int xCoordinate;
	private int yCoordinate;
	
	private boolean isGoal;

	private int gCost;
	private int hCost;
	private int fCost;
	
	private boolean isOpen;
	
	private int weight;
	
	private SearchNode bestParent;
	private List<SearchNode> children = new ArrayList<>();
	
	public SearchNode(int x, int y, int weight) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.weight = weight;
	}
	
	public void markAsGoal() {
		this.isGoal = true;
	}
	
	public int getfCost() {
		return this.fCost;
	}
	
	public void setfCost(int newCost) {
		this.fCost = newCost;
	}
}
