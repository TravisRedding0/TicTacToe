package dev.travis.tictactoe.game;

import dev.travis.tictactoe.graphics.Board;
import dev.travis.tictactoe.graphics.Menu;
import dev.travis.tictactoe.players.AI;
import dev.travis.tictactoe.players.Player;
import dev.travis.tictactoe.util.Cord;
import dev.travis.tictactoe.util.Settings;

public class Game implements Runnable {
    private Board board;
    private Player player1;
    private Player player2;
    private AI playerAI;
    private int curPlayer;
    private boolean isPlaying;
    private boolean multiplayer;
    private Player winner;
    Thread thread;

    public Game() {
        board = new Board();
        player1 = new Player(Settings.player1Symbol);
        player2 = new Player(Settings.player2Symbol);
        playerAI = new AI(Settings.player2Symbol);
        curPlayer = 1;
        isPlaying = false;
    }
    
    public void run() {
    	try {
    		begin();
    	} catch (Exception e) {
    		
    	}
    }

    public void begin() {
        isPlaying = true;
        Menu.greeting();
        multiplayer = !Menu.multiplayer();
        Menu.println();

        while (isPlaying) {
            Menu.println("\n");
            board.draw();
            Menu.println("\n");

            if (board.isDraw()) {
                Menu.draw();
                end();
                break;
            }

            if (board.hasWinner()) {
                winner = board.getWinner();
                Menu.winner(winner);
                end();
                break;
            }


            Player ply = new Player('A');
            if (curPlayer == 1) {
                ply = player1;
            }
            else if (curPlayer == 2) {
                ply = player2;
            }

            boolean validInput = false;
            if (ply != null) {
                Menu.println("Player is " + ply.getSymbol());
            }
            else {
                Menu.println("Player is " + playerAI.getSymbol());
            }

            if (curPlayer == 3) {
                Cord cord = playerAI.getMove(player1, board);
                board.input(playerAI, cord.getX(), cord.getY());
            }
            else {
                Cord cord;
                cord = Menu.input(Settings.BOARD_SIZE, Settings.BOARD_SIZE);
                if (cord.getX() == -1) {
                    end();
                    break;
                }
                else if (board.canTake(cord.getX(), cord.getY())) {
                    board.input(ply, cord.getX(), cord.getY());
                    validInput = true;
                }
                while (!validInput) {
                    Menu.println("Invalid Entry! Try Again!");
                    cord = Menu.input(Settings.BOARD_SIZE, Settings.BOARD_SIZE);
                    if (cord.getX() == -1) {
                        end();
                        break;
                    }
                    else if (board.canTake(cord.getX(), cord.getY())) {
                        board.input(ply, cord.getX(), cord.getY());
                        validInput = true;
                    }
                }
            }

            board.checkWin();

            switchPlayer();
        }

    }

    public void end() {
        isPlaying = false;
        Menu.end();
        Menu.println();
        boolean restart = Menu.restart();
        if (restart) {
            refresh();
            begin();
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setGameState(boolean play) {
        isPlaying = play;
    }

    public void switchPlayer() {
        if (!multiplayer) {
            if (curPlayer == 1) {
                curPlayer = 2;
            }
            else {
                curPlayer = 1;
            }
        }
        else {
            if (curPlayer == 1) {
                curPlayer = 3;
            }
            else {
                curPlayer = 1;
            }
        }
    }

    public void refresh() {
        board = new Board();
        player1 = new Player(Settings.player1Symbol);
        player2 = new Player(Settings.player2Symbol);
        playerAI = new AI(Settings.player2Symbol);
        curPlayer = 1;
        isPlaying = false;
    }
}
