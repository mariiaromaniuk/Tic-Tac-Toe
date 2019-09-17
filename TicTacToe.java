package weekfour;

import java.util.Scanner;

public class TicTacToe1 {

	//class static variables so it can be used by any static methods in the class
	static char[][] board = new char[3][3]; //3x3 2D array to represent tictactoe board
	static int move = 0; //move counter to keep track move number
	static Scanner scan = new Scanner(System.in); //scanner object
	static char player1 = '0';

	public static void main(String[] args) {
		String play = ""; //variable to hold if user wants to play the game
		do { //loop to continue unless user doesn't want to play the game
			//asks user if they want to play
			System.out.println("Do you want to play a game of Tic Tac Toe? (y/n)");
			play = scan.next(); //holds user answer
			if (play.equals("y")) { //if they do want to play
				playGame(); //start the game
				continue; //continue the loop to ask user if they want to play
			}
			else if (!play.equals("n")) //if user didn't choose y or no, print invalid choice
				System.out.println("Invalid choice. Please choose \"y\" or \"n\"\n");
		} while (!play.equals("n")); //break loop if user enters n
		scan.close(); //close scanner object

	}
	
	//determines if player1 is X or O
	public static void determinePlayer1() {
		do { //loop to continue asking user who player is if X or O is not entered
			System.out.println("Who is player1? \"X\" or \"O\""); //asks user for player 1
			player1 = scan.next().toUpperCase().charAt(0); //converts input to uppercase and get the first character
			if (!(player1 == 'X' || player1 == 'O')) //if it isn't X or O
				System.out.println("Invalid choice."); //let user know it's invalid
		} while (!(player1 == 'X' || player1 == 'O')); //only stop loop when X or O is entered
		System.out.println(player1 + " will start first."); //lets players know if X or O goes first
	}
	
	public static void playGame() {
		determinePlayer1(); //calls the method to determine player1
		clearBoard(); //removes all X's and O's from the board
		//loop to continue until 9 moves are made since only 9 slots are available
		while (move < 9) {
			//prints whos turn it is and asks user to choose a box
			System.out.println("It is player's " + checkXorO() + "'s turn.");
			System.out.println("Choose box number to enter your symbol.");
			checkIfInteger(); //checks if user is entering an integer
			//loop that has a condition to check if chosen spot is already filled
			//the body of the loop will only execute if the spot is not empty
			//if it's not empty, markBoard will fill in the spot
			while (!markBoard(scan.nextInt())) 
				System.out.println("That spot is chosen. Please choose another spot.");
			if (move > 3) //starts to check for winners after player 1 has made 3 moves
				//checks for horizontal, vertical, and diagonal wins
				//states which player won and in how many moves
				if (checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin()) {
					System.out.println("Player " + checkXorO() + " won in " + (move/2 + 1) + " moves");
					break; //break the loop since game is over
				}
			move++; //increment move to keep track of move number
			printBoard(); //prints the updated board
		}
		if (move == 9) //if 9 moves are over
			System.out.println("The game is a tie."); //user is notified game is a tie
	}
	
	//clears the contents of the board
	public static void clearBoard() {
		//nested loop to clear board and enter the position number as the element
		for (int i = 0, num = 1; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				board[i][j] = (char)(num + '0');
				num++;
			}
		printBoard(); //prints updated board
	}
	
	//checks to see if spot chosen by user is empty
	//if it's empty, mark the spot and return true. otherwise return false
	public static boolean markBoard(int n) {
		if (n == 1) { //checks for spot 1
			if (board[0][0] == 'X' || board[0][0] == 'O') return false;
			else {
				board[0][0] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 2) { //checks for spot 2
			if (board[0][1] == 'X' || board[0][1] == 'O') return false;
			else {
				board[0][1] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 3) { //checks for spot 3
			if (board[0][2] == 'X' || board[0][2] == 'O') return false;
			else {
				board[0][2] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 4) { //checks for spot 4
			if (board[1][0] == 'X' || board[1][0] == 'O') return false;
			else {
				board[1][0] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 5) { //checks for spot 5
			if (board[1][1] == 'X' || board[1][1] == 'O') return false;
			else {
				board[1][1] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 6) { //checks for spot 6
			if (board[1][2] == 'X' || board[1][2] == 'O') return false;
			else {
				board[1][2] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 7) { //checks for spot 7
			if (board[2][0] == 'X' || board[2][0] == 'O') return false;
			else {
				board[2][0] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}		
		}
		else if (n == 8) { //checks for spot 8
			if (board[2][1] == 'X' || board[2][1] == 'O') return false;
			else {
				board[2][1] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else if (n == 9) { //checks for spot 9
			if (board[2][2] == 'X' || board[2][2] == 'O') return false;
			else {
				board[2][2] = checkXorO(); //marks either X or O depending on player's turn
				return true;
			}
		}
		else return false; //returns false if none of the conditions are met
	}
	
	//finds out which player's turn it is and which character to enter into the board
	public static char checkXorO() {
		switch (player1) { //switch case to see if player1 is X or O
			case 'X': //if player1 is X
				if (move % 2 == 0) //if move value is even then it is X
					return 'X';
				else //if move value is odd then it is O
					return 'O';
			case 'O': //if player1 is O
				if (move % 2 == 0) //if move value is even then it is O
					return 'O';
				else //if move value is odd then it is X
					return 'X';
			default:
				return '0';
		}
	}
	
	//checks to see if the value entered by user is an integer
	public static void checkIfInteger() {
		//loop that repeats unless an integer is entered
		while (!scan.hasNextInt()) {
			System.out.println("That's not a valid input. Please enter an integer (whole number).");
			scan.next(); //clears the buffer
		}	
	}

	//checks for horizontal wins
	public static boolean checkHorizontalWin() {
		char symbol = checkXorO(); //holds the character X or O
		//loop to check all the rows match the current player's character
		for (int i = 0; i < 3; i++)
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				printHorizontalWin(i); //if they match, print which row the user won
				return true; //return true stating player won
			}
		return false; //otherwise return false so the game can continue
	}

	//checks for horizontal wins
	public static boolean checkVerticalWin() {
		char symbol = checkXorO(); //holds the character X or O
		//loop to check all the columns match the current player's character
		for (int i = 0; i < 3; i++)
			if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
				printVerticalWin(i); //if they match, print which column the user won
				return true; //return true stating player won
			}
		return false; //otherwise return false so the game can continue
	}
	
	public static boolean checkDiagonalWin() {
		char symbol = checkXorO(); //holds the character X or O
		//checks if player won in a diagonal from top left to bottom right
		if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
			printDiagonalWin(0); //print top left to bottom right win
			return true; //return true stating player won
		}
		//checks if player won in a diagonal from top right to bottom left		
		else if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
			printDiagonalWin(1); //print top right to bottom left win
			return true; //return true stating player won
		}
		return false; //otherwise return false so the game can continue
	}

	//prints the board
	public static void printBoard() {
		printRow1(); //prints row 1
		System.out.println("------------------------------------------------");
		printRow2(); //prints row 2
		System.out.println("------------------------------------------------");
		printRow3(); //prints row 3
	}
	
	//output for row 1
	public static void printRow1() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t\t|\t\t|\t\t");
	}

	//output for row 2
	public static void printRow2() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t\t|\t\t|\t\t");
	}
	
	//output for row 3
	public static void printRow3() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("\t\t|\t\t|\t\t");
	}
	
	//prints the board with the winning row that takes the row number as a parameter
	public static void printHorizontalWin(int i) {
		switch (i) { //switch case to determine which winning row to print
			case 0: //if winning row is 1
				printWinRow1(); //print winning row 1
				System.out.println("------------------------------------------------");
				printRow2();	//print for row 2
				System.out.println("------------------------------------------------");
				printRow3();	//print for row 3
				break;
				
			case 1:  //if winning row is 2
				printRow1();	//print for row 1
				System.out.println("------------------------------------------------");
				printWinRow2();	//print for winning row 2
				System.out.println("------------------------------------------------");
				printRow3();	//print for row 3
				break;

			case 2: //if winning row is 3
				printRow1();	//print for row 1
				System.out.println("------------------------------------------------");
				printRow2();	//print for row 2
				System.out.println("------------------------------------------------");
				printWinRow3();	//print for winning row 3
				break;
		}
	}

	//prints board with winning column that takes the column number as a parameter
	public static void printVerticalWin(int i) {
		switch (i) { //switch case to determine which winning column to print
			case 0:	//if winning column is 1
				printVerticalWin1(); //print board with column 1 as winning
				break;
			case 1:	//if winning column is 2
				printVerticalWin2(); //print board with column 2 as winning
				break;
			case 2:	//if winning column is 3
				printVerticalWin3(); //print board with column 3 as winning
				break;
		}
	}
	
	//prints board with winning column that takes the diagonal 1 or 2 as a parameter
	public static void printDiagonalWin(int i) {
		switch (i) { //switch case to determine which winning diagonal to print
			case 0: //if winning diagonal is 1
				printDiagonalWin1(); //print board with diagonal top left to bottom right as winning
				break;
			case 1: //if winning diagonal is 2
				printDiagonalWin2(); //print board with diagonal top right to bottom left as winning
				break;			
		}
	}
	
	//output for winning row 1
	public static void printWinRow1() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("--------" + board[0][0] + "-------|-------" + board[0][1] + 
				"-------|-------" + board[0][2] + "-------");
		System.out.println("\t\t|\t\t|\t\t");	
	}

	//output for winning row 2
	public static void printWinRow2() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("--------" + board[1][0] + "-------|-------" + board[1][1] + 
				"-------|-------" + board[1][2] + "-------");
		System.out.println("\t\t|\t\t|\t\t");
	}
	
	//output for winning row 3
	public static void printWinRow3() {
		System.out.println("\t\t|\t\t|\t\t");
		System.out.println("--------" + board[2][0] + "-------|-------" + board[2][1] + 
				"-------|-------" + board[2][2] + "-------");
		System.out.println("\t\t|\t\t|\t\t");
	}
	
	//prints board with winning column 1
	public static void printVerticalWin1() {
		System.out.println("\t|\t|\t\t|\t\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t|\t|\t\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t|\t|\t\t|\t\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t|\t|\t\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t|\t|\t\t|\t\t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("\t|\t|\t\t|\t\t");
	}
	
	//prints board with winning column 2
	public static void printVerticalWin2() {
		System.out.println("\t\t|\t|\t|\t\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t\t|\t|\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t|\t|\t\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t\t|\t|\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t|\t|\t\t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("\t\t|\t|\t|\t\t");
	}
	
	//prints board with winning column 3
	public static void printVerticalWin3() {
		System.out.println("\t\t|\t\t|\t|\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t\t|\t\t|\t|\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t\t|\t|\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t\t|\t\t|\t|\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t\t|\t|\t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("\t\t|\t\t|\t|\t");
	}
	
	//prints board with winning diagonal 1 (top left to bottom right)
	public static void printDiagonalWin1() {
		System.out.println("       \\     \t|\t\t|\t\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t \\\t|\t\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|      \\    \t|\t\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t\t|\t \\\t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t\t|      \\    \t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("\t\t|\t\t|\t \\\t");
	}
	
	//prints board with winning diagonal 1 (top right to bottom left)
	public static void printDiagonalWin2() {
		System.out.println("\t\t|\t\t|\t /\t");
		System.out.println("\t" + board[0][0] + "\t|\t" + board[0][1] + "\t|\t" + board[0][2]);
		System.out.println("\t\t|\t\t|      /    \t");
		System.out.println("------------------------------------------------");
		System.out.println("\t\t|\t /\t|\t\t");
		System.out.println("\t" + board[1][0] + "\t|\t" + board[1][1] + "\t|\t" + board[1][2]);
		System.out.println("\t\t|      /    \t|\t\t");
		System.out.println("------------------------------------------------");
		System.out.println("\t /\t|\t\t|\t\t");
		System.out.println("\t" + board[2][0] + "\t|\t" + board[2][1] + "\t|\t" + board[2][2]);
		System.out.println("       /     \t|\t\t|\t\t");
	}

}