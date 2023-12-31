package chess;

import chess.board.Board;
import utils.StringUtils;

import java.util.Scanner;

import static java.lang.System.exit;

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
            showPrompt();
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

    private static void showGameResult() {
        String winner = chessGame.getWinner();
        double whitePoint = chessGame.getWhitePoint();
        double blackPoint = chessGame.getBlackPoint();
        chessView.showEndingScreen(winner, whitePoint, blackPoint);
    }

    private static void showPrompt() {
        if(gameStart) {
            chessView.showMessage(chessGame.getTurnPresentation() + ": ");
            return;
        }
        chessView.showMessage("Enter 'start' to play game : ");
    }

    private static void start() {
        chessView.showMessage(StringUtils.appendNewLine("게임을 시작합니다"));
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
            chessView.showBoard();

            if(chessGame.isKingDead()) {
                showGameResult();
                exit(0);
            }
        } catch (RuntimeException exception) {
            chessView.showWarningMessage(exception.getMessage());
        }
    }

    private static void verifyMoveArguments(String[] arguments) {
        if(arguments.length != 3 || arguments[1].length() != 2 || arguments[2].length() != 2) {
            throw new RuntimeException("move 명령 형식 오류.");
        }
    }

    private static void verifyGameStarted() {
        if(!gameStart) {
            throw new RuntimeException("게임이 시작되지 않았습니다");
        }
    }
}
