import java.util.Random;

public class Main {
	
	public static String playerChoice;
	public static String computerChoice;
	public static String winner;
	
	private static int randomNumber() 
	{
		Random rand = new Random();
		int randomNumber = rand.nextInt(3) + 1; //starts from 0, so add 1
		return randomNumber;
	}
	
	public static void computerChoice()
	{
		if (randomNumber() == 1)
		{
			computerChoice = "Rock";
		}
		else if (randomNumber() == 2)
		{
			computerChoice = "Paper";
		}
		else
		{
			computerChoice = "Scissors";
		}
	}
	
	public static void winner()
	{
		if (playerChoice == "Rock" && computerChoice == "Paper" || playerChoice == "Paper" && computerChoice == "Scissors" || (playerChoice == "Scissors" && computerChoice == "Rock"))
		{
			winner = "Computer wins!";
		}
		else if (playerChoice == "Rock" && computerChoice == "Scissors" || (playerChoice == "Paper" && computerChoice == "Rock") || (playerChoice == "Scissors" && computerChoice == "Paper"))
		{
			winner = "You win!";
		}
		else
		{
			winner = "It's a draw!";
		}
	}
}