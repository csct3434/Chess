package chess;

import java.util.Scanner;

public class ChessGame {

    private static Board board;
    private static Scanner sc;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        init();

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
        board = new Board();
    }

    public static String getUserInput() {
        System.out.print("userInput: ");
        return sc.nextLine();
    }

    private static void start() {
        System.out.println("게임을 시작합니다.\n");
        board.initialize();
        printBoard();
    }

    private static void move(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 3) {
            board.move(parts[1], parts[2]);
            printBoard();
        }
    }

    private static void end() {
        System.out.println("게임을 종료합니다.");
    }

    private static void printBoard() {
        System.out.println("[현재 체스판 상태]");
        System.out.println(board.showBoard());
    }
}
