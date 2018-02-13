/**
 *
 * @author Enrique Bascur
 * @version 1.0
 * @since -DATE FINISHED-
 * Hangman.java
 *
 * This program allows a user to play Hangman with the computer.
 * RULES:
 *The User has to attempt to guess the randomly generated movie title from the list
 *in as few attempts as possible. 
 *The User can have a maximum of seven wrong attempts before the User loses.
 * 
 */
import java.util.*;
import java.lang.*;


public class Hangman 
{
	//This is an array of 15 Strings
	static String movieList[] = {"Star Wars: The Last Jedi","The Matrix","Avatar", "Fantasia", "Fight Club", "Alien", "Moulin Rouge!", "The Godfather", "La La Land", "Taxi Driver", "Thor Ragnarok", "Toy Story 3", "Guardians of the Galaxy", "The Lion King", "Casablanca"};
	static int wrongGuessCounter = 0;
	/**
	 * Entry point of the program
	 * 
	 * @param args input arguments
 	 * 
  	 */
	public static void main(String[] args)
	{
		// This code snippet just shows you how to use Stringbuilder. 
		// Random number generator to pick the movie
		Random generator = new Random(); 
		int moviePick = generator.nextInt(15); // picks a random movie title from the movie list array
		Scanner scan = new Scanner(System.in); //gets used for the user input

		StringBuilder currentGuess = new StringBuilder(movieList[moviePick].toLowerCase()); //actual movie string
		StringBuilder lettersUsed = new StringBuilder(" "); //stores already guessed letters
		StringBuilder adivina = new StringBuilder();//stores the users current input
		//FOR TESTER: UNCOMMENT NEXT LINE IF YOU WANT TO SEE THE MOVIE TITLE AND ASTERISKS GUESSES AT THE SAME TIME
		//System.out.println(movieList[moviePick].toLowerCase());
		
		
		String movie = movieList[moviePick].toLowerCase();
//Converts the movie title, except special characters and numbers, to "*"
		for (int i = 0; i < currentGuess.length(); i++)
		{
			char c = currentGuess.charAt(i);
			if (Character.isLetter(c)) //prints out a letter as an asterisk
			{
				currentGuess.replace(i,i+1,"*");
			}
			else
			{
				String used = Character.toString(c);
				currentGuess.replace(i,i+1,used); //prints out non-letter as itself.
			}
		}
/*-------------------------------------------------------------------------------------------*/
/**************This while loop has the whole game, exits if the user wins or loses otherwise *
*keeps going***********************************************************************************/
/*-------------------------------------------------------------------------------------------*/
		while (wrongGuessCounter < 7)
		{
/*-------------------------------------------------------------------------------------------*/
/**************This 'user input' while loop makes sure the user only enters a single letter  *
* and no repeats******************************************************************************/
/*-------------------------------------------------------------------------------------------*/
			System.out.println("Guess the Movie: " + currentGuess);
			System.out.println("Please enter a single letter (a to z): ");//message to user
			int choice = 0;//set to 0 when done, acts as sentinel value
			while (choice < 1)
			{
				String userGuess = scan.next().toLowerCase(); //user input in lowercase
				//gets the user input as a char so that we can compare it to 
				char guess = userGuess.charAt(0);  
				//makes sure the user only input one value and that the value is a letter.
				if (userGuess.length() == 1 && Character.isLetter(guess))
				{
					adivina.replace(0,1,userGuess); //stores user current input, is replaced every new guess
					choice = 1; //exits while loop, since the input is acceptable
				}
				else
					System.out.println("Enter a SINGLE letter from a to z: ");
/*------------------------------------------------------------------------------------------------------*/
/**************This "for" statement makes sure that the user input letter is not a repeat****************/
/*-----------------------------------------------------------------------------------------------------*/			
				for (int z = 0; z < lettersUsed.length(); z++) //cycles through already used letters
				{
					//this if statement checks for repeat letters stored in a stringbuilder string "lettersUsed"
					if (adivina.charAt(0) == lettersUsed.charAt(z))
					{
						choice = 2; //will let the next statement know if the letter is a repeat
					}
				}
/*------------------------------------------------------------------------------------------------------*/
/**************The following if else statment receives the for statements output and continues the code  *
* or re-initializes the user input while loop if the letter is a repeat**********************************/
/*-----------------------------------------------------------------------------------------------------*/				
				if (choice < 2) //if choice is less than 2 the letter is not a repeat, and will be stored in "lettersUsed"
				{
					lettersUsed.append(adivina+ " "); //adds the letter to a StringBuilder which stores the already guessed letters
				}
				else //choice is 2, therefore a repeat
				{
					System.out.println("you already used that letter!");
					choice = 0;//reinitializes the while loop to choose a different letter
				}
			}
/*------------------------------------------------------------------------------------------------------*/
/**************This "for" statement changes asterisks to letters if the user guessed correctly***********/
/*-----------------------------------------------------------------------------------------------------*/
			System.out.println("letters used: " + lettersUsed);	
			int replace = 0; // sentinel value that will tell us if the user guessed a letter
			for (int i = 0; i < movie.length(); i++)
			{
				char c = movie.charAt(i);
				if (Character.isLetter(c) && c == adivina.charAt(0)) //turns asterisk into letter
				{
					String used = Character.toString(c);
					currentGuess.replace(i,i+1,used);
					replace++; //there was an asterisk replacement, therefore the user guessed correctly
				}
			}
/*------------------------------------------------------------------------------------------------------*/
/**************This "if" statement only goes through if the user did not guess correctly*****************/
/*-----------------------------------------------------------------------------------------------------*/
			if (replace == 0) //no letters were replaced, the user guessed wrong
			{
				wrongGuessCounter++;//adds one to the wrong guess counter
				int left = 7 - wrongGuessCounter;
				System.out.println("You only have: " + left + " wrong guesses left.");//how many wrong guesses left
			}
/*------------------------------------------------------------------------------------------------------*/
/**************This else statement only goes through if the user guessed a letter correctly**************/
/*-----------------------------------------------------------------------------------------------------*/
			else
			{
				System.out.println("You guessed the letter:" + adivina);//let's the user know the correct letter
				System.out.println();
				replace = 0; 
			}
/*------------------------------------------------------------------------------------------------------*/
/******This for statement checks if the game has any asterisks left, to see if the user has won yet******/
/*-----------------------------------------------------------------------------------------------------*/
			int win = 0;//sentinel, only wins if it remains 0
			for (int q = 0; q < currentGuess.length(); q++) //checks the movie name for asterisks
			{
				char finalCheck = currentGuess.charAt(q); //
				String star ="*";
				char star2 = star.charAt(0);//didn't know how to write a "*" as a char...
				if (finalCheck == star2) //if there is an asterisks it adds 1 to win, meaning user has not won yet
				{
					win++;
				}
			}
			if(win == 0) //user has won, there are no asterisks in the movie name
			{
				System.out.println("You guessed the movie: " + currentGuess);
				System.out.println("You had " + wrongGuessCounter + " wrong guesses!");
				System.out.print("You win!!!");
				break;
			}
			else
			{
				win = 0; //resets win to zero for next attempt. 
			}
			
		}
	}
}