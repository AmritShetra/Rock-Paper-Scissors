import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UserInterface extends Application implements EventHandler<ActionEvent>{
	
	private Stage window = new Stage();
	
	final private Button rock = new Button("Rock"); 
	final private Button paper = new Button("Paper");
	final private Button scissors = new Button("Scissors");
	final private Label winnerLabel = new Label("\n"+"\n"+"\n");
	
	public static void main (String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("Rock, Paper, Scissors");
		BorderPane layout = new BorderPane(); //Main layout used to determine positions of buttons etc.
		HBox buttons = new HBox(); //Will be used to contain the three buttons horizontally
		VBox nodes = new VBox(); //Will be used to contain the HBox and the two labels
		
		Label instructionLabel = new Label();
		instructionLabel.setText("Rock beats Scissors, Paper beats Rock and Scissors beats Paper. \n" + "You're playing against the Computer, whose choice is randomly selected. \n" + "Choose an option:");
		instructionLabel.setTextAlignment(TextAlignment.CENTER);
		
		Image rockImage = new Image(getClass().getResourceAsStream("rock.png"));
		Image paperImage = new Image(getClass().getResourceAsStream("paper.png"));
		Image scissorsImage = new Image(getClass().getResourceAsStream("scissors.png"));
		rock.setGraphic(new ImageView(rockImage));
		paper.setGraphic(new ImageView(paperImage));
		scissors.setGraphic(new ImageView(scissorsImage));
		
		rock.setOnAction(this);  //This class will handle the button events
		paper.setOnAction(this);
		scissors.setOnAction(this);
		
		buttons.getChildren().addAll(rock, paper, scissors); //Add nodes to the HBox
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10, 10, 10, 10));
		
		nodes.getChildren().addAll(instructionLabel, buttons, winnerLabel); //Add nodes to the VBox
		nodes.setAlignment(Pos.CENTER);
		instructionLabel.setPadding(new Insets(0, 30, 0, 0));
		
		layout.setCenter(nodes);
		layout.setBottom(new Label("Created by Amrit Shetra, 2018"));
		
		Scene scene = new Scene(layout, 800, 800);
		
		window.setScene(scene);
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
		winnerLabel.setText("You chose: " + Main.playerChoice + "\n" + "Computer chose: " + Main.computerChoice + "\n" + Main.winner);
	}
}