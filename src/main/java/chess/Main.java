package chess;

import chess.board.Board;
import chess.board.Position;

import java.util.Scanner;

public class Main {

    private static ChessView chessView;
    private static ChessGame chessGame;
    private static Scanner sc;
    private static boolean isStarted;

    public static void main(String[] args) {
        init();
        run();
    }

    public static void run() {
        while (true) {
            String userInput = getUserInput();

            if (userInput.equals("start")) {
                start();
            } else if (userInput.startsWith("move")) {
                move(userInput);
            } else if (userInput.equals("end")) {
                end();
                break;
            }
        }
    }

    private static void init() {
        sc = new Scanner(System.in);
        Board board = new Board();
        chessGame = new ChessGame(board);
        chessView = new ChessView(board);
        isStarted = false;
    }

    private static String getUserInput() {
        System.out.print("userInput: ");
        return sc.nextLine().trim();
    }

    private static void start() {
        System.out.println("게임을 시작합니다.");
        chessGame.initializeBoard();
        printBoard();
        isStarted = true;
    }

    private static void move(String userInput) {
        if (isStarted) {
            try {
                String[] arguments = userInput.split(" ");
                if (verifyMoveArguments(arguments)) {
                    chessGame.move(new Position(arguments[1]), new Position(arguments[2]));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                printBoard();
            }
        } else {
            System.out.println("게임이 시작되지 않았습니다 : start");
        }
    }

    private static boolean verifyMoveArguments(String[] arguments) {
        return checkArgumentsLength(arguments) && checkDistinctSquares(arguments);
    }

    private static boolean checkArgumentsLength(String[] arguments) {
        return arguments.length == 3 || arguments[1].length() == 2 || arguments[2].length() == 2;
    }

    private static boolean checkDistinctSquares(String[] arguments) {
        return !arguments[1].equals(arguments[2]);
    }

    private static void end() {
        System.out.println("게임을 종료합니다.");
    }

    private static void printBoard() {
        System.out.println("\n[현재 체스판]");
        chessView.showBoard();
    }
}
