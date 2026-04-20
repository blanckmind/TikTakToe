import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class TicTacToe {

    public static void main(String[] args) {
        // Module 1: Game State
        char[] board = new char[9];
        initializeBoard(board);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Tic-Tac-Toe! You are 'X'.");
        printBoard(board);
        
        // Module 5: The Main Game Loop
        while (true) {
            // Human Turn
            humanMove(board, scanner);
            printBoard(board);
            if (checkWinner(board, 'X')) {
                System.out.println("Congratulations! You win!");
                break;
            }
            if (isDraw(board)) {
                System.out.println("It's a draw!");
                break;
            }
            
            // Computer Turn
            computerMove(board);
            printBoard(board);
            if (checkWinner(board, 'O')) {
                System.out.println("Computer wins! Better luck next time.");
                break;
            }
            if (isDraw(board)) {
                System.out.println("It's a draw!");
                break;
            }
        }
        // Always close your scanner to prevent resource leaks
        scanner.close(); 
    }

    // --- Module 1: The Game Board ---
    
    private static void initializeBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    private static void printBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
    }

    // --- Module 2: Human Player Input ---
    
    private static void humanMove(char[] board, Scanner scanner) {
        boolean validMove = false;
        while (!validMove) {
            System.out.print("Enter your move (1-9): ");
            
            // Check if the user actually entered an integer
            if (scanner.hasNextInt()) {
                int move = scanner.nextInt() - 1; // Subtract 1 for 0-indexed array
                
                // Validate range and check if spot is empty
                if (move >= 0 && move < 9 && board[move] == ' ') {
                    board[move] = 'X';
                    validMove = true;
                } else {
                    System.out.println("Invalid move. Spot is taken or out of range.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }
    }

    // --- Module 3: Computer Opponent ---
    
    private static void computerMove(char[] board) {
        System.out.println("Computer's turn...");
        Random rand = new Random();
        ArrayList<Integer> emptySpots = new ArrayList<>();
        
        // Find all available indexes
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                emptySpots.add(i);
            }
        }
        
        // Pick a random index from the available spots
        if (!emptySpots.isEmpty()) {
            int move = emptySpots.get(rand.nextInt(emptySpots.size()));
            board[move] = 'O';
        }
    }

    // --- Module 4: Win and Draw Logic ---
    
    private static boolean checkWinner(char[] board, char player) {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
        };
        
        for (int[] condition : winConditions) {
            if (board[condition[0]] == player && 
                board[condition[1]] == player && 
                board[condition[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDraw(char[] board) {
        for (char spot : board) {
            if (spot == ' ') {
                return false; // Found an empty spot, not a draw yet
            }
        }
        return true; // No empty spots left
    }
}