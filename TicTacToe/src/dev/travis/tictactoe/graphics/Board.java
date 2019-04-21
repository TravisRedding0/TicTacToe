package dev.travis.tictactoe.graphics;

import dev.travis.tictactoe.players.Player;
import dev.travis.tictactoe.util.Settings;

public class Board {
	private Player[][] board;
    private Player winner;
    private boolean isDraw;

    public Board() {
        board = new Player[Settings.BOARD_SIZE][Settings.BOARD_SIZE];
        isDraw = false;
    }

    public Player getPlayerAt(int r, int c) {
        return board[r][c];
    }

    public void input(Player ply, int r, int c) {
        board[r][c] = ply;
    }

    public void checkWin() {
        winner = null;
        boolean match = false;

        //Check Horizontal
        for (int r = 0; r < Settings.BOARD_SIZE; r++) {
            match = true;
            for (int c = 1; c < Settings.BOARD_SIZE; c++) {
                if (board[r][c] == null || board[r][c - 1] == null) {
                    match = false;
                    break;
                }
                else if (!board[r][c].equals(board[r][c - 1])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                winner = board[r][0];
                return;
            }
        }

        //Check Vertical
        match = false;
        for (int c = 0; c < Settings.BOARD_SIZE; c++) {
            match = true;
            for (int r = 1; r < Settings.BOARD_SIZE; r++) {
                if (board[r][c] == null || board[r - 1][c] == null) {
                    match = false;
                    break;
                }
                else if (!board[r][c].equals(board[r - 1][c])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                winner = board[0][c];
                return;
            }
        }

        //Check Diagnol Top-Left to Bottom-Right
        match = false;
        for (int x = 1; x < Settings.BOARD_SIZE; x++) {
            match = true;
            if (board[x][x] == null || board[x - 1][x - 1] == null) {
                match = false;
                break;
            }
            else if (!board[x][x].equals(board[x - 1][x - 1])) {
                match = false;
                break;
            }
        }
        if (match) {
            winner = board[0][0];
            return;
        }

        //Check Diagnol Top-Right to Bottom-Left
        match = false;
        int rCount = Settings.BOARD_SIZE - 2;
        for (int c = Settings.BOARD_SIZE - 2; c >= 0; c--) {
            match = true;
            if (board[rCount][c] == null || board[rCount - 1][c + 1] == null) {
                match = false;
                break;
            }
            else if (!board[rCount][c].equals(board[rCount - 1][c + 1])) {
                match = false;
                break;
            }
            rCount++;
        }
        if (match) {
            winner = board[0][board[0].length - 1];
            return;
        }

        //Check for Draw
        isDraw = true;
        for (int r = 0; r < Settings.BOARD_SIZE; r++) {
            for (int c = 0; c < Settings.BOARD_SIZE; c++) {
                if (board[r][c] == null) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) {
                break;
            }
        }
    }

    public int[][] getPossibleMoves() {
        int[][] moves = new int[Settings.BOARD_SIZE][Settings.BOARD_SIZE];

        for (int r = 0; r < Settings.BOARD_SIZE; r++) {
            for (int c = 0; c < Settings.BOARD_SIZE; c++) {
                moves[r][c] = 0;
                if (board[r][c] == null) {
                    moves[r][c] = 1;
                }
            }
        }

        return moves;
    }

    public void resetSpot(int r, int c) {
        board[r][c] = null;
    }

    public boolean canTake(int r, int c) {
        return (board[r][c] == null && winner == null && isDraw == false);
    }

    public boolean hasWinner() {
        return (winner != null);
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void draw() {
        for (int c = 0; c < Settings.BOARD_SIZE; c++) {
            Menu.print("   " + (c + 1));
        }
        Menu.println();

        for (int r = 0; r < Settings.BOARD_SIZE; r++) {
            String str = r + 1 + " ";

            if (board[r][0] != null) {
                str += " " + board[r][0].getSymbol();
            }
            else {
                str += "  ";
            }

            for (int c = 1; c < Settings.BOARD_SIZE; c++) {
                str += " | ";
                if (board[r][c] != null) {
                    str += board[r][c].getSymbol();
                }
                else {
                    str += " ";
                }
            }
            Menu.println(str);

            if (r != Settings.BOARD_SIZE - 1) {
                str = "   ";
                for (int x = 1; x <= Settings.BOARD_SIZE; x++) {
                    str += "---";
                }
                Menu.println(str);
            }
        }
    }
}
