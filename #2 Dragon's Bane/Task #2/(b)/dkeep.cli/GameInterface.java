package dkeep.cli;
import dkeep.logic.*;
import java.util.Scanner; 

public class GameInterface {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nWelcome to The Maze and The Dragon Game!\n\nW - move up\nS - move down\nA - move left\nD - move right");
		System.out.println("\nTo win this game, move the Hero (H) to get the Key (K) and then Exit the maze (E)!");
		System.out.println("PS: Be careful, I heard the Dragon (D) is not so frendly...");
		Game game = new Game();
		char c = 'x';
		
			while(game.getState() == 0) {
				do {
					System.out.print("Your move: ");
					c = input.next().charAt(0);
					game.moveHero(c);
				}
				while (!verifyInput(c));
			}//end of the game cycle
			
			if(game.getState() == 1) {
				System.out.println("\nCONGRATS! You Won!");
			}
			else if (game.getState() == -1){
				System.out.println("\nGAME OVER!");
			}
			
			input.close();
	}
	public static boolean verifyInput(char move) {
		if(move == 'w' || move == 's' || move == 'a' || move == 'd') {
			return true;
		}
		else {
			System.out.println("Invalid Input! Please try again.");
			return false;
		}
	}//end of verifyInput()

}
