package nicolorsillo;

import nicolorsillo.entities.*;
import nicolorsillo.exceptions.GameNotFoundException;
import nicolorsillo.exceptions.IdAlreadyExistException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static nicolorsillo.entities.VideoGame.Genre.*;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GameCollection collection = new GameCollection();
        boolean running = true;


        collection.addGame(new VideoGame("VG3000001", "Fifa", 2016, 49.99, "PC", 6, SPORT));
        collection.addGame(new VideoGame("VG2000001", "GTA", 2013, 74.99, "PS", 7, SHOOTER));
        collection.addGame(new VideoGame("VG5000001", "Gear Of War", 2007, 74.99, "XBOX", 3, HORROR));
        collection.addGame(new VideoGame("VG4000001", "Mario Kart", 2005, 29.99, "Nintendo", 4, RACING));
        collection.addGame(new VideoGame("VG6000001", "Cut the Rope", 2010, 0.00, "Smartphone", 9, PUZZLE));
        collection.addGame(new BoardGame("BG0000001", "Monopoly", 1935, 49.99, 2, 8, 120));
        collection.addGame(new BoardGame("BG0000002", "Carcassone", 2000, 29.99, 2, 5, 40));
        collection.addGame(new BoardGame("BG0000003", "Hitster", 2022, 19.99, 1, 12, 30));


        /*System.out.println(collection.searchByID("VG3000001"));
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

        collection.printStats();*/

        System.out.println("\n-------Game Shop------\n");


        while (running) {
            System.out.println("\n Scegli un opzione\n");
            System.out.println("1. Aggiungi un nuovo Gioco");
            System.out.println("2. Cerca un gioco tramite ID");
            System.out.println("3. Cerca giochi per Prezzo Massimo");
            System.out.println("4. Cerca giochi per Numero Giocatori");
            System.out.println("5. Cancella un gioco per ID");
            System.out.println("6. Aggiorna un gioco esistente");
            System.out.println("7. Mostra statistiche della collezione");
            System.out.println("0. Esci dall'applicazione");
            System.out.println();

            int scelta = 0;

            try {
                scelta = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido");
                scanner.nextLine();
            }

            switch (scelta) {
                case 1:
                    addGame(collection, scanner);
                    break;

                case 2:
                    System.out.print("Inserisci l'ID da cercare: ");
                    String idSearch = scanner.nextLine();
                    Optional<Game> gameTrovato = collection.searchByID(idSearch);
                    if (gameTrovato.isPresent()) {
                        System.out.println("Gioco trovato: " + gameTrovato.get());
                    } else {
                        System.out.println("Nessun gioco trovato con l'ID: " + idSearch);
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Inserisci il prezzo massimo: ");
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine();
                        List<Game> maxPriceList = collection.searchByMaxPrice(maxPrice);
                        if (maxPriceList.isEmpty()) {
                            System.out.println("Nessun gioco trovato sotto i " + maxPrice + "€.");
                        } else {
                            maxPriceList.forEach(System.out::println);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Errore: Inserisci un valore numerico per il prezzo.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Inserisci il numero di giocatori: ");
                        int numPlayers = scanner.nextInt();
                        scanner.nextLine();
                        List<Game> gameForPlayerList = collection.searchByNumOfPlayers(numPlayers);
                        if (gameForPlayerList.isEmpty()) {
                            System.out.println("Nessun gioco da tavolo supporta " + numPlayers + " giocatori.");
                        } else {
                            gameForPlayerList.forEach(System.out::println);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Errore: Inserisci un numero intero.");
                        scanner.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Inserisci l'ID del gioco da eliminare: ");
                    String idGameRemove = scanner.nextLine();
                    try {
                        collection.removeByID(idGameRemove);
                        System.out.println("Gioco rimosso con successo!");
                    } catch (GameNotFoundException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                    break;

                case 6:
                    updateGame(collection, scanner);
                    break;

                case 7:
                    collection.printStats();
                    break;

                case 0:
                    System.out.println("Chiusura del programma in corso... Arrivederci!");
                    running = false;
                    break;

                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
        scanner.close();
    }

    private static void addGame(GameCollection collection, Scanner scanner) {
        try {
            System.out.println("\n--- AGGIUNGI NUOVO GIOCO ---");
            System.out.print("Scegli il tipo (1 = Videogioco, 2 = Gioco da Tavolo, 0 = Torna al menu principale): ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            if (tipo == 0) {
                return;
            }

            while (tipo != 1 && tipo != 2) {
                System.out.println("Scelta non valida.");
                System.out.print("Inserisci nuovamente:");
                tipo = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Titolo: ");
            String title = scanner.nextLine();
            System.out.print("Anno di uscita: ");
            int year = scanner.nextInt();
            System.out.print("Prezzo: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            while (price < 0) {
                System.out.println("Errore: Il prezzo non può essere negativo.");
                System.out.print("Inserisci nuovamente il prezzo: ");
                price = scanner.nextDouble();
                scanner.nextLine();
            }

            if (tipo == 1) {
                System.out.print("Piattaforma: ");
                String platform = scanner.nextLine();
                System.out.print("Ore di gioco: ");
                int hours = scanner.nextInt();
                scanner.nextLine();

                while (hours < 0) {
                    System.out.println("Errore: la durata non può essere negativa.");
                    System.out.print("Inserisci nuovamente la durata: ");
                    hours = scanner.nextInt();
                    scanner.nextLine();
                }

                System.out.println("Generi disponibili: Action, Shooter, Sport, Racing, Horror, Puzzle");
                System.out.print("Inserisci Genere: ");
                String genreInput = scanner.nextLine().toUpperCase();
                VideoGame.Genre genre = valueOf(genreInput);

                VideoGame vg = new VideoGame(id, title, year, price, platform, hours, genre);
                collection.addGame(vg);
            } else {
                System.out.print("Numero minimo giocatori: ");
                int min = scanner.nextInt();
                while (min < 1) {
                    System.out.println("Errore: Ci deve essere almeno 1 giocatore.");
                    System.out.print("Inserisci nuovamente il numero minimo: ");
                    min = scanner.nextInt();
                }
                System.out.print("Numero massimo giocatori: ");
                int max = scanner.nextInt();
                while (max < min) {
                    System.out.println("Errore: Il numero massimo non può essere inferiore al minimo (" + min + ").");
                    System.out.print("Inserisci nuovamente il numero massimo: ");
                    max = scanner.nextInt();
                }
                System.out.print("Durata media partita: ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                while (duration <= 0) {
                    System.out.println("Errore: La durata deve essere maggiore di zero.");
                    System.out.print("Inserisci nuovamente la durata: ");
                    duration = scanner.nextInt();
                    scanner.nextLine();
                }

                BoardGame bg = new BoardGame(id, title, year, price, min, max, duration);
                collection.addGame(bg);
            }
            System.out.println("Gioco inserito con successo!");

        } catch (InputMismatchException e) {
            System.out.println("Errore: Dati inseriti non validi. Operazione annullata.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: Genere non esistente o dati negativi. " + e.getMessage());
        } catch (IdAlreadyExistException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    private static void updateGame(GameCollection collection, Scanner scanner) {
        System.out.println("\n--- AGGIORNA GIOCO ESISTENTE ---");
        System.out.print("Inserisci l'ID del gioco da modificare: ");
        String idGameToUpdate = scanner.nextLine();

        Optional<Game> check = collection.searchByID(idGameToUpdate);
        if (check.isEmpty()) {
            System.out.println("Errore: Non esiste nessun gioco con l'ID " + idGameToUpdate);
            return;
        }

        Game gameToUpdate = check.get();
        System.out.println("Gioco selezionato: " + gameToUpdate.getGameTitle());

        try {
            System.out.print("Nuovo Titolo: ");
            String title = scanner.nextLine();
            System.out.print("Nuovo Anno di uscita: ");
            int year = scanner.nextInt();
            System.out.print("Nuovo Prezzo: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Game gameUpated;
            if (gameToUpdate instanceof VideoGame) {
                System.out.print("Nuova Piattaforma: ");
                String platform = scanner.nextLine();
                System.out.print("Nuove Ore di gioco: ");
                int hours = scanner.nextInt();
                scanner.nextLine();
                while (hours < 0) {
                    System.out.println("Errore: le ore di gioco non possono essere negative.");
                    System.out.print("Inserisci nuovamente le ore: ");
                    hours = scanner.nextInt();
                    scanner.nextLine();
                }

                System.out.println("Generi disponibili: Action, Shooter, Sport, Racing, Horror, Puzzle");
                System.out.print("Nuovo Genere: ");
                String genreInput = scanner.nextLine().toUpperCase();
                VideoGame.Genre genre = valueOf(genreInput);

                gameUpated = new VideoGame(idGameToUpdate, title, year, price, platform, hours, genre);
            } else {
                System.out.print("Nuovo Numero minimo giocatori: ");
                int min = scanner.nextInt();
                System.out.print("Nuovo Numero massimo giocatori: ");
                int max = scanner.nextInt();
                System.out.print("Nuova Durata media: ");
                int duration = scanner.nextInt();
                scanner.nextLine();

                gameUpated = new BoardGame(idGameToUpdate, title, year, price, min, max, duration);
            }

            collection.updateGame(idGameToUpdate, gameUpated);
            System.out.println("Gioco aggiornato con successo!");

        } catch (InputMismatchException e) {
            System.out.println("Errore: Input non numerico. Aggiornamento annullato.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nei dati di aggiornamento: " + e.getMessage());
        } catch (GameNotFoundException e) {
            System.out.println("Errore: " + e.getMessage());
        }


    }

}
