import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class OneGame extends Game {
	Integer maxNumber;
	Integer guessNumber = 0;
	Integer randNumber;
	int guesses;
	String clue;
	boolean correct;
	
	public OneGame( int userMax ) {
		this.maxNumber = userMax;
		
		// Random Number Generation
		Random randInt = new Random();
		this.randNumber = randInt.nextInt(this.maxNumber) + 1;
	}
	
	public Integer getInput() {
		
		/**
		 * Requests input from the keyboard, validates that its numeric, and returns an Int
		 * @return Numeric keyboard input.
		 */
		
		// Variable initialization
		String userInput = null;
		Integer userInputInt = 0;
		
		// Pulled from https://stackoverflow.com/questions/17538182/getting-keyboard-input
		System.out.print("Please enter a numeric guess: ");
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		// Pulls keyboard input
		try {
			userInput = keyboard.readLine();
		} catch (IOException e) {
			System.out.println("Unable to get input from keyboard");
		}
		
		// Check for non-numeric characters. Loops until numeric is entered
		Boolean repeat = true;
		while ( repeat ) {
			try {  // If .parseInt works, repeat is set to false and loop ends
				userInputInt = Integer.parseInt(userInput);
				repeat = false;
			} catch ( NumberFormatException e ) {  // Catches non numeric. Prints error message, and pulls new keyboard input 
				System.out.print("Make sure you enter a number. Try again: ");
				try {
					userInput = keyboard.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				repeat = true;
			}
		}
		return userInputInt;
	}
	
	public Integer OneGameGuessingGame () {
		
		/**
		 * Loops user through a command line version of the game, checking for valid input
		 * @return Integer representing how many guesses the user took
		 */
		
		Boolean correct = false;
		System.out.println("Welcome to the CLI for the guessing game ");  // Game header
		
		// Loop begins
		while (!correct) {
			this.guessNumber += 1;
			Integer guess = this.getInput();
			
			if ( ( guess > this.maxNumber )  || ( guess < 0 ) ) {  // Checks out of bounds
				clue = ("Your guess fell outside of the predefined range!");
			} else if ( guess == this.randNumber ) {  // Checks correct
//				clue = ("Correct! The number was " + guess.toString() + ". You win!" );
				correct = true;
			} else if ( guess < this.randNumber ) {  // Checks Lower bound
				clue = ("<");
			} else if (guess > this.randNumber ){  // Checks higher bound
				clue = ( ">" );
			}
		}
		return this.guessNumber;
	}
	
	
	public String checkValue(String guess) {
		
		Integer Guess = 0;
		
		Guess += Guess.parseInt(guess);
		
		this.guesses ++;
		
		if ( ( Guess > this.maxNumber )  || ( Guess < 0 ) ) {  // Checks out of bounds
			clue = ("Your guess fell outside of the predefined range!");
		} else if ( Guess == this.randNumber ) {  // Checks correct
			clue = ("Correct! The number was " + Guess.toString() + ". You win!" );
			correct = true;
		} else if ( Guess < this.randNumber ) {  // Checks Lower bound
			clue = (">");
		} else if (Guess > this.randNumber ){  // Checks higher bound
			clue = ( "<" );
		}
		return clue;
		
	}
	
	public boolean gameOver() {
		
		if(correct)
			return true;
		
		return false;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getGuesses() {
		// TODO Auto-generated method stub
		return this.guesses;
	}

	@Override
	public int getMax() {
		// TODO Auto-generated method stub
		return this.maxNumber;
	}

	@Override
	public String getClue() {
		// TODO Auto-generated method stub
		return this.clue;
	}

	@Override
	public int getRand() {
		// TODO Auto-generated method stub
		return this.randNumber;
	}
		
	
}
