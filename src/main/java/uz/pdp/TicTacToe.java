package uz.pdp;

import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private static char currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to Tic Tac Toe Game 🤗");
            System.out.println("1. Play with a friend \uD83D\uDC65");
            System.out.println("2. Play with computer 🖥️");
            System.out.println("3. Exit🚪 ");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> playWithFriend();

                case 2 -> playWithComputer();

                case 3 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid input. Please enter 1, 2, or 3.");
            }
        } while (choice != 3);
    }

    private static void playWithFriend() {
        cleanBoard();
        currentPlayer = 'X';
        int winner;

        do {
            printBoard();
            playTurn();
            winner = checkWinner();
            switchPlayer();
        } while (winner == 0);

        printBoard();
        printResult(winner);
    }

    private static void playWithComputer() {
        cleanBoard();
        currentPlayer = 'X';
        int winner;

        do {
            printBoard();

            if (currentPlayer == 'X') {
                playTurn();
            } else {
                playComputerTurn();
            }

            winner = checkWinner();
            switchPlayer();
        } while (winner == 0);

        printBoard();
        printResult(winner);
    }

    private static void cleanBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playTurn() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ');

        board[row][col] = currentPlayer;
    }

    private static void playComputerTurn() {
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (board[row][col] != ' ');

        /* Bo'sh katak topilmaguncha sikl tasodifiy indekslarni yaratishda davom etadi
         bu bilan kompyuterni harakatni qayta yozish yoki noto'g'ri harakat qilishdan saqlaydi*/

        System.out.println("Player O chooses row " + (row + 1) + " and column " + (col + 1));
        board[row][col] = 'O';
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static int checkWinner() {
        // Check for win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return (board[i][0] == 'X') ? 1 : 2;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return (board[0][i] == 'X') ? 1 : 2;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return (board[0][0] == 'X') ? 1 : 2;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return (board[0][2] == 'X') ? 1 : 2;
        }

        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    draw = false;
                    break;
                }
            }
            if (!draw) {
                break;
            }
        }

        return (draw) ? -1 : 0;
    }

    private static void printResult(int winner) {
        if (winner == 1) {
            System.out.println("Player X wins! 👏");
        } else if (winner == 2) {
            System.out.println("Player O wins! 👏");
        } else {
            System.out.println("It's a draw! 👐");
        }
    }
}
