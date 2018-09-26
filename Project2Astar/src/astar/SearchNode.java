package astar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Object for holding the state of each node on the board */
public class SearchNode {
	
	private int xCoordinate;
	private int yCoordinate;
	

	private int gCost = 0;
	private int hCost = 0;
	private int fCost = 0;
	
	
	private int weight;
	
	
	private Character symbol; //Used for visualization
	
	private SearchNode bestParent;
	private Set<SearchNode> children = new HashSet<>();
	
	public SearchNode(int x, int y, int weight, char symbol) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.weight = weight;
		this.symbol = symbol;
	}
	
	/* Getters */
	public SearchNode getParent() {
		return this.bestParent;
	}
	
	public int getfCost() {
		return this.fCost;
	}
	
	public int getgCost() {
		return this.gCost;
	}
	
	public int gethCost() {
		return this.hCost;
	}
	
	public int getX() {
		return this.xCoordinate;
	}
	
	public int getY() {
		return this.yCoordinate;
	}
	
	public Set<SearchNode> getChildren() {
		return this.children;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	/* Setters */
	public void setfCost(int newCost) {
		this.fCost = newCost;
	}
	
	/* f-cost is the sum of g and h, f needs to be updated whenever h and g are. */
	public void setgCost(int newCost) {
		this.gCost = newCost;
		this.setfCost(this.gCost + this.hCost);
	}
	
	public void sethCost(int newCost) { //Will only be set one time
		this.hCost = newCost;
		this.setfCost(this.hCost + this.gCost);
	}
	
	public void addChild(SearchNode child) {
		this.children.add(child);
	}
	
	public void setParent(SearchNode parentCandidate) {
		this.bestParent = parentCandidate;
	}
	
	public String toString() {
		return "   x: " + this.xCoordinate + " y: " + this.yCoordinate;
	}
}
