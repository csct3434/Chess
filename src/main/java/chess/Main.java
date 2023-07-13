package chess;

import chess.board.Board;

import java.util.Scanner;

public class Main {

    private static ChessView chessView;
    private static ChessGame chessGame;
    private static Scanner scanner;
    private static boolean gameStart;

    public static void main(String[] args) {
        init();
        run();
    }

    public static void run() {
        while (true) {
            String userInput = getUserInput();

            if (userInput.equals("start")) {
                start();
            } else if (userInput.equals("move")) {
                move(userInput);
            } else if (userInput.equals("end")) {
                end();
                break;
            }
        }
    }

    private static void init() {
        Board board = new Board();
        scanner = new Scanner(System.in);
        chessGame = new ChessGame(board);
        chessView = new ChessView(board);
        gameStart = false;
    }

    private static String getUserInput() {
        System.out.print("userInput: ");
        return scanner.nextLine().trim();
    }

    private static void start() {
        System.out.println("게임을 시작합니다.");
        chessGame.initializeBoard();
        printBoard();
        gameStart = true;
    }

    private static void move(String userInput) {
        try {
            verifyGameStarted();

            String[] arguments = userInput.split(" ");
            verifyMoveArguments(arguments);

            String sourceSquare = arguments[1];
            String targetSquare = arguments[2];
            chessGame.move(sourceSquare, targetSquare);
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        } finally {
            printBoard();
        }
    }

    private static void verifyMoveArguments(String[] arguments) throws IllegalArgumentException {
        if(arguments.length != 3 || arguments[1].length() != 2 || arguments[2].length() != 2) {
            throw new IllegalArgumentException("에러 : 잘못된 move 입력");
        }
    }

    private static void verifyGameStarted() {
        if(!gameStart) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다 : start 입력");
        }
    }

    private static void end() {
        System.out.println("게임을 종료합니다.");
    }

    private static void printBoard() {
        System.out.println("\n[현재 체스판]");
        chessView.showBoard();
    }
}
