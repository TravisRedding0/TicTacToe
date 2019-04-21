package dev.travis.tictactoe.graphics;

import java.util.Scanner;

import dev.travis.tictactoe.players.Player;
import dev.travis.tictactoe.util.Cord;

public class Menu {
	private static Scanner sc = new Scanner(System.in);

    public static void greeting() {
        String str = "";
        str += "Welcome to Tic-Tac-Toe! \n";
        str += "Instructions: \n";
        str += "1. Enter a valid row. \n";
        str += "2. Enter a valid column. \n";
        print(str);
    }

    public static Cord input(int maxR, int maxC) {
        Cord cord;
        String in;
        int row, col;

        print("Enter a row: ");
        in = sc.nextLine();
        if (in == "end" || in == "END") {
            return new Cord(-1, -1);
        }
        try {
            row = Integer.parseInt(in.trim()) - 1;
        } catch(Exception e) {
            Menu.println("Invalid Entry! Try Again!");
            return input(maxR, maxC);
        }
        if (row > maxR || row < 0) {
            Menu.println("Invalid Entry! Try Again!");
            return input(maxR, maxC);
        }

        print("Enter a column: ");
        in = sc.nextLine();
        if (in == "end" || in == "END") {
            return new Cord(-1, -1);
        }
        try {
            col = Integer.parseInt(in.trim()) - 1;
        } catch(Exception e) {
            Menu.println("Invalid Entry! Try Again!");
            return input(maxR, maxC);
        }
        if (col > maxR || col < 0) {
            Menu.println("Invalid Entry! Try Again!");
            return input(maxR, maxC);
        }

        println();

        cord = new Cord(row, col);

        return cord;
    }

    public static void draw() {
        println("It's a Draw!");
    }

    public static void winner(Player ply) {
        println("The Winner is Player " + ply.getSymbol());
    }

    public static void end() {
        println("Thank You For Playing!");
    }

    public static boolean restart() {
        println();
        print("Would you like to restart (Y/N): ");
        String in = sc.nextLine();
        in = in.toUpperCase();

        println();

        if (!in.equals("Y") && !in.equals("N")) {
            Menu.println("Invalid Entry! Try Again!");
            return restart();
        }

        return (in.equals("Y"));
    }

    public static boolean multiplayer() {
        print("Multiplayer? (Y/N): ");
        String in = sc.nextLine();
        in = in.toUpperCase();

        println();

        if (!in.equals("Y") && !in.equals("N")) {
            Menu.println("Invalid Entry! Try Again!");
            return multiplayer();
        }

        return (in.equals("Y"));
    }

    public static void print(String str) {
        System.out.print(str);
    }
    public static void println(String str) {
        System.out.println(str);
    }
    public static void println() {
        System.out.println();
    }
}
