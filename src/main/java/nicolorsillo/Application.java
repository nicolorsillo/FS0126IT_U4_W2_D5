package nicolorsillo;

import nicolorsillo.entities.*;

import java.util.Scanner;

import static nicolorsillo.entities.VideoGame.Genre.*;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameCollection collection = new GameCollection();


        collection.addGame(new VideoGame("VG3000001", "Fifa", 2016, 49.99, "PC", 6, SPORT));
        collection.addGame(new VideoGame("VG2000001", "GTA", 2013, 74.99, "PS", 7, SHOOTER));
        collection.addGame(new VideoGame("VG5000001", "Gear Of War", 2007, 74.99, "XBOX", 3, HORROR));
        collection.addGame(new VideoGame("VG4000001", "Mario Kart", 2005, 29.99, "Nintendo", 4, RACING));
        collection.addGame(new VideoGame("VG6000001", "Cut the Rope", 2010, 0.00, "Smartphone", 9, PUZZLE));
        collection.addGame(new BoardGame("BG0000001", "Monopoly", 1935, 49.99, 2, 8, 120));
        collection.addGame(new BoardGame("BG0000002", "Carcassone", 2000, 29.99, 2, 5, 40));
        collection.addGame(new BoardGame("BG0000003", "Hitster", 2022, 19.99, 1, 12, 30));


        System.out.println(collection.searchByID("VG3000001"));
        System.out.println(collection.searchByID("BG0000001"));
        System.out.println(collection.searchByID("BG0000eeeee"));

        System.out.println(collection.searchByMaxPrice(0));

        System.out.println(collection.searchByNumOfPlayers(1));

        collection.removeByID("BG0000003");

        collection.getGamesList().forEach(System.out::println);

        collection.addGame(new BoardGame("BG0000003", "Hitster", 2022, 19.99, 1, 12, 30));

        collection.getGamesList().forEach(System.out::println);

        collection.updateGame("BG0000003", new BoardGame("BG0000004", "Hitster", 2022, 19.99, 1, 12, 30));

        collection.getGamesList().forEach(System.out::println);

        collection.printStats();

    }
}
