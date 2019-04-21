package dev.travis.tictactoe.players;

import dev.travis.tictactoe.graphics.Board;
import dev.travis.tictactoe.util.Cord;
import dev.travis.tictactoe.util.Settings;

public class AI extends Player {
	public AI (char symbol) {
        super(symbol);
    }

    public Cord getMove(Player opponent, Board board) {
        int n = Settings.BOARD_SIZE;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (board.getPlayerAt(r, c) == null) {
                    board.input(this, r, c);
                    board.checkWin();
                    if (board.hasWinner()) {
                        return new Cord(r, c);
                    }
                    else {
                        board.resetSpot(r, c);
                        board.checkWin();
                    }
                    board.input(opponent, r, c);
                    board.checkWin();
                    if (board.hasWinner()) {
                        return new Cord(r, c);
                    }
                    else {
                        board.resetSpot(r, c);
                        board.checkWin();
                    }

                }
            }
        }

        int rSpot, cSpot;
        do {
            rSpot = (int) (Math.random() * n);
            cSpot = (int) (Math.random() * n);
        } while (!board.canTake(rSpot, cSpot));
        return new Cord(rSpot, cSpot);
    }
}
