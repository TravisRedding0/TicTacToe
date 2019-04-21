package dev.travis.tictactoe.players;

public class Player {
	private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean equals(Player ply) {
        return (ply.getSymbol() == symbol);
    }

    public boolean isAI() {
        return false;
    }
}
