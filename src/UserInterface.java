import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UserInterface extends Application implements EventHandler<ActionEvent>{
	
	private Stage window = new Stage();
	private Scene mainScene, resultScene;
	
	final private Button rock = new Button("Rock"); 
	final private Button paper = new Button("Paper");
	final private Button scissors = new Button("Scissors");
	final private Label winnerLabel = new Label("\n"+"\n"+"\n");
	
	int winCount;
	int lossCount;
	int drawCount;
	final private Button playAgain = new Button("Play Again");
	
	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Rock, Paper, Scissors");
		
		BorderPane layout = new BorderPane(); //Main layout used to determine positions of buttons etc.
		HBox buttons = new HBox(); //Will be used to contain the three buttons horizontally
		VBox nodes = new VBox(); //Will be used to contain the HBox and the label below
		
		Image logo = new Image(getClass().getResourceAsStream("logo.png"));
		ImageView logoImage = new ImageView();
		logoImage.setImage(logo);
		
		Label instructionLabel = new Label();
		instructionLabel.setText("Rock beats Scissors, Paper beats Rock and Scissors beats Paper. \n"
								+ "You're playing against the Computer, whose choice is randomly selected. \n" 
								+ "Choose an option:");
		instructionLabel.setTextAlignment(TextAlignment.CENTER);
		instructionLabel.setLineSpacing(5);
		instructionLabel.setFont(new Font(15)); //Adjusts font size
		
		Image rockImage = new Image(getClass().getResourceAsStream("rock.png"));
		Image paperImage = new Image(getClass().getResourceAsStream("paper.png"));
		Image scissorsImage = new Image(getClass().getResourceAsStream("scissors.png"));
		rock.setGraphic(new ImageView(rockImage));
		paper.setGraphic(new ImageView(paperImage));
		scissors.setGraphic(new ImageView(scissorsImage));
		
		rock.setOnAction(this);  //This class will handle the button events
		paper.setOnAction(this);
		scissors.setOnAction(this);
		
		buttons.getChildren().addAll(rock, paper, scissors); //Add button nodes to the HBox
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10, 10, 10, 10));
		
		nodes.getChildren().addAll(logoImage, instructionLabel, buttons); //Add the button HBox and labels to the VBox
		nodes.setAlignment(Pos.CENTER);
		nodes.setSpacing(5);
		instructionLabel.setPadding(new Insets(0, 30, 0, 0));
		
		layout.setCenter(nodes); //adds the VBox to the BorderPane
		
		mainScene = new Scene(layout, 800, 800); //Add the BorderPane layout to the window
		
		window.setScene(mainScene); //Set the window scene to mainScene
		window.show();
	}

	@Override
	public void handle(ActionEvent event) {
		
		if (event.getSource() == rock) //If button is "Rock"
		{
			Main.playerChoice = "Rock";
		}
		else if (event.getSource() == paper)
		{
			Main.playerChoice = "Paper";
		}
		else if (event.getSource() == scissors)
		{
			Main.playerChoice = "Scissors";
		}
		
		Main.computerChoice(); //Determine computer's move
		Main.winner(); //Determine winner
		
		results();
		
		if (event.getSource() == playAgain)
		{
			window.setScene(mainScene); //return to the start
		}
	}
	
	public void results () {
		
		BorderPane resultLayout = new BorderPane(); //Secondary layout used to determine positions of results and chart
		VBox yourResult = new VBox(); //Used to vertically align the label and your RPS choice
		VBox computerResult = new VBox();
		
		Label youChoseLabel = new Label("You chose:");
		youChoseLabel.setFont(new Font(15)); //Adjusts font size
		Label computerChoseLabel = new Label("Computer chose:");
		computerChoseLabel.setFont(new Font(15)); //Adjusts font size
		
		resultScene = new Scene (resultLayout, 800, 800);
		
		switch (Main.winner)
		{
			case ("You win!"):
				winCount++;
				break;
			case ("Computer wins!"):
				lossCount++;
				break;
			case ("It's a draw!"):
				drawCount++;
				break;
			default:
				break;
		}
		
		Image playerImage = null;
		ImageView selectedPlayerImage = new ImageView();
		switch (Main.playerChoice)
		{
			case ("Rock"):
				playerImage = new Image(getClass().getResourceAsStream("rock.png"));
				break;
			case ("Paper"):
				playerImage = new Image(getClass().getResourceAsStream("paper.png"));
				break;
			case ("Scissors"):
				playerImage = new Image(getClass().getResourceAsStream("scissors.png"));
				break;
			default:
				break;
		}
		selectedPlayerImage.setImage(playerImage);
		yourResult.getChildren().addAll(youChoseLabel, selectedPlayerImage); //add nodes to the yourResult VBox
		yourResult.setAlignment(Pos.CENTER); //align the VBox
		yourResult.setSpacing(20);
		
		Image computerImage = null;
		ImageView selectedComputerImage = new ImageView();  
		switch (Main.computerChoice)
		{
			case ("Rock"):
				computerImage = new Image(getClass().getResourceAsStream("rock.png"));
				break;
			case ("Paper"):
				computerImage = new Image(getClass().getResourceAsStream("paper.png"));
				break;
			case ("Scissors"):
				computerImage = new Image(getClass().getResourceAsStream("scissors.png"));
				break;
			default:
				break;
		}
		selectedComputerImage.setImage(computerImage);
		computerResult.getChildren().addAll(computerChoseLabel, selectedComputerImage); //add nodes to the computerResult VBox
		computerResult.setAlignment(Pos.CENTER); //align the VBox
		computerResult.setSpacing(20);
		
		winnerLabel.setText(Main.winner);
		winnerLabel.setFont(new Font(15)); //Adjusts font size
		HBox resultsBox = new HBox(); //Used to horizontally align the results boxes
		resultsBox.getChildren().addAll(yourResult, winnerLabel, computerResult);
		resultsBox.setAlignment(Pos.CENTER);
		resultsBox.setSpacing(80);
		
		VBox winnerBox = new VBox(); //Used to vertically align the above box with the Pie Chart and playAgain button
		winnerBox.getChildren().addAll(resultsBox, playAgain);
		PieChart(winCount, drawCount, lossCount, winnerBox);
		winnerBox.setAlignment(Pos.CENTER);
		
		resultLayout.setCenter(winnerBox);
		window.setScene(resultScene); //Set the window scene to resultScene
		playAgain.setOnAction(this);
	}
	
	static void PieChart (int winCount, int drawCount, int lossCount, VBox winnerBox){
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				   new PieChart.Data("Win", winCount), 
				   new PieChart.Data("Draw", drawCount), 
				   new PieChart.Data("Lost", lossCount));
		
		PieChart pieChart = new PieChart(pieChartData);
		pieChart.setPrefHeight(310);
		pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), ": ", (int)data.getPieValue())));
		
		winnerBox.getChildren().add(pieChart);
	}
}