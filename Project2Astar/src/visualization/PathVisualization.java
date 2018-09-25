package visualization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import astar.BestFirstSearch;
import astar.SearchNode;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PathVisualization extends Application{

	private List<SearchNode> solution = new ArrayList<>();
	private List<SearchNode> allNodes = new ArrayList<>();
	
	GridPane grid = new GridPane();
	Scene scene = new Scene(grid, 400, 400);
	


	@Override
	public void start(Stage primaryStage) throws Exception {
		String board = new String(Files.readAllBytes(Paths.get("./boards/board-2-4.txt")));
		BestFirstSearch bfs = new BestFirstSearch(board);
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(8);
		grid.setHgap(10);
		
		
		this.allNodes = bfs.getAllNodes();
		this.solution = bfs.agenda();
		System.out.println(this.solution);
		
		for (SearchNode node : this.allNodes) {
			Label symbol = new Label("" + node.getSymbol());
			if (node.getSymbol() == '.') {
				symbol.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 8;");
			} else if (node.getSymbol() == '#') {
				symbol.setStyle("-fx-background-color: #808080; -fx-padding: 8;");
			} else if (node.getSymbol() == 'A') {
				symbol.setStyle("-fx-background-color: #8B0000; -fx-padding: 8;");
			} else if (node.getSymbol() == 'B') {
				symbol.setStyle("-fx-background-color: #7CFC00; -fx-padding: 8;");
			}
			/* Extended cell-types for part 2 */
			else if (node.getSymbol() == 'w') {
				symbol.setStyle("-fx-background-color: #191970; -fx-padding: 8;");
			} else if (node.getSymbol() == 'm') {
				symbol.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 8;");
			} else if (node.getSymbol() == 'f') {
				symbol.setStyle("-fx-background-color: #228B22; -fx-padding: 8;");
			} else if (node.getSymbol() == 'g') {
				symbol.setStyle("-fx-background-color: #7CFC00; -fx-padding: 8;");
			}  else if (node.getSymbol() == 'r') {
				symbol.setStyle("-fx-background-color: #DEB887; -fx-padding: 8;");
			}
			grid.add(symbol, node.getX(), node.getY());
		}
		
		for (SearchNode node : this.solution) {
			Label symbol = new Label("" + node.getSymbol());
			symbol.setStyle("-fx-background-color: #00BFFF; -fx-padding: 8;");
			grid.add(symbol, node.getX(), node.getY());
		}
		
		
			
	
		primaryStage.setScene(this.scene);
		primaryStage.show();
	}
	
	public void setAllNodes(List<SearchNode> allNodes) {
		this.allNodes = allNodes;
	}
	
	public void setSolution(List<SearchNode> solution) {
		this.solution = solution;
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
