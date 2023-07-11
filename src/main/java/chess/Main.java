package chess;

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

    private static void init() {
        sc = new Scanner(System.in);
        Board board = new Board();
        chessGame = new ChessGame(board);
        chessView = new ChessView(board);
        isStarted = false;
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

    public static String getUserInput() {
        System.out.print("userInput: ");
        return sc.nextLine();
    }

    private static void start() {
        System.out.println("게임을 시작합니다.");
        chessGame.initializeBoard();
        printBoard();
        isStarted = true;
    }

    private static void move(String userInput) {
        if (isStarted) {
            String[] parts = userInput.split(" ");
            if (parts.length == 3) {
                chessGame.move(parts[1], parts[2]);
                printBoard();
            }
        } else {
            System.out.println("게임이 시작되지 않았습니다 : start");
        }
    }

    private static void end() {
        System.out.println("게임을 종료합니다.");
    }

    private static void printBoard() {
        System.out.println("\n[현재 체스판]");
        System.out.println(chessView.showBoard());
    }
}
