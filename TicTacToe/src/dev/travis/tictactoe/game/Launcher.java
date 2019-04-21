package dev.travis.tictactoe.game;

public class Launcher {
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
}
