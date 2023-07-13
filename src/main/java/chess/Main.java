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

    private static void init() {
        Board board = new Board();
        scanner = new Scanner(System.in);
        chessGame = new ChessGame(board);
        chessView = new ChessView(board);
        gameStart = false;
    }

    public static void run() {
        while (true) {
            chessView.showPrompt();
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("start")) {
                start();
            } else if (userInput.startsWith("move")) {
                move(userInput);
            } else if (userInput.equals("end")) {
                chessView.showMessage("게임을 종료합니다");
                break;
            }
        }
    }

    private static void start() {
        chessView.showMessage("게임을 시작합니다.");
        chessGame.initializeBoard();
        chessView.showBoard();
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
            chessView.showMessage(exception.getMessage());
        } finally {
            chessView.showBoard();
        }
    }

    private static void verifyMoveArguments(String[] arguments) {
        if(arguments.length != 3 || arguments[1].length() != 2 || arguments[2].length() != 2) {
            throw new IllegalArgumentException("에러 : 잘못된 move 입력");
        }
    }

    private static void verifyGameStarted() {
        if(!gameStart) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다 : start 입력");
        }
    }
}
