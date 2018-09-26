# TDT4136-intro-ai-assignment2-astar-visualization
The second project of the course TDT4136 at NTNU: https://www.ntnu.no/studier/emner/TDT4136 

# Running the code

* Navigate to src\visualization\PathVisualization.java
* On line 32 edit the filepath to match the board you want to run the algorithm on
* Run the PathiVisualization-class

The result of the algorithm will pop up as a new javafx-window. 

# The boards

The boards are split between part 1 and part 2 of the assignment, as the names indicate: "board-[part]-[boardNumber].txt. 

Boards from part 1 only distinguish between four types of cells: Start-cell, Goal-cell, Obstacles and no obstacles. These are visualized as "A", "B", "#" and "." respectively. 
Color codes for cells:
* No Obstacle: white
* Obstacle: Grey
* Solution: Light blue

Boards from part 2 distinguish between multiple types of cells, where each type has a different cost for traversal. 
Color codes: 
* Road: brown
* Forest: Dark green
* Grass: Light green
* Water: Dark blue
* Solution: Light blue

The costs of cells can be seen in the BestFirstSearch-class. 

# Class structure

The main class in the best first search is "BestFirstSearch.java" -naturally. It utilizes the SearchNode-class to represent nodes on the board, and the CustomMinHeap-class as a priority queue for the "open-nodes" list. A* is located in "BestFirstSearch.java". 

The "MinHeap"-class in package: helperClasses is simply a genereic minheap-implementation using regular integers. 

PathVisualization runs the BFS-class to visualize the boards with the solution-paths. 
