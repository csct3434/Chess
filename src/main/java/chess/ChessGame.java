package chess;

import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        while(true) {
            System.out.print("input: ");
            String command = sc.nextLine();

            if(command.equals("start")) {
                System.out.println("게임을 시작합니다.\n");

                board.initialize();
                System.out.println("[현재 체스판 상태]");
                System.out.println(board.print());
            }
            else if(command.equals("end")){
                System.out.println("게임을 종료합니다.");
                break;
            }
        }
    }
}
