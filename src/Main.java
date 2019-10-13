import java.util.Random;

public class Main {
	
	static String playerChoice;
	static String computerChoice;
	static String winner;
	
	private static int randomNumber() 
	{
		Random rand = new Random();
		return rand.nextInt(3) + 1;
	}
	
	static void computerChoice()
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
	
	static void winner()
	{
		if (playerChoice.equals("Rock") && computerChoice.equals("Paper") || playerChoice.equals("Paper") && computerChoice.equals("Scissors") || (playerChoice.equals("Scissors") && computerChoice.equals("Rock")))
		{
			winner = "Computer wins!";
		}
		else if (playerChoice.equals("Rock") && computerChoice.equals("Scissors") || (playerChoice.equals("Paper") && computerChoice.equals("Rock")) || (playerChoice.equals("Scissors") && computerChoice.equals("Paper")))
		{
			winner = "You win!";
		}
		else
		{
			winner = "It's a draw!";
		}
	}
}